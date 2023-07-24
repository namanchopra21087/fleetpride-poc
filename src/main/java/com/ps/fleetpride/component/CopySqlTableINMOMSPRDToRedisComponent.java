package com.ps.fleetpride.component;

import com.ps.fleetpride.constant.Tables;
import com.ps.fleetpride.dto.INMOMSPRDDto;
import com.ps.fleetpride.mapper.INMOMSPRDTableRowMapper;
import com.ps.fleetpride.service.DataCopyProgressService;
import com.ps.fleetpride.service.INMOMSPRDRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
//@Component
@Slf4j
@RequiredArgsConstructor
public class CopySqlTableINMOMSPRDToRedisComponent {

    private final JdbcTemplate jdbcTemplate;
    private final DataCopyProgressService dataCopyProgressService;
    private final INMOMSPRDRedisService inmomsprdRedisService;
    private final String INMOMSPRD = Tables.INMOMSPRD.name();
    @Scheduled(fixedDelay = 2,timeUnit = TimeUnit.SECONDS)
    public void copyDataToRedis() {
        try {
            var start = Instant.now();
            log.info("Scheduler running for table {} ", INMOMSPRD);
            long lastCount = dataCopyProgressService.fetchRecordsProcessed(INMOMSPRD);
            log.info("Total records copied to redis are {}",lastCount);
            StringBuilder sb = new StringBuilder("select ID, PRDPART from dbo.INMOMSPRD ORDER BY ID OFFSET").append(" ").append(lastCount).append(" ").append("ROWS FETCH NEXT 1000 ROWS ONLY");
            var rows = jdbcTemplate.query(sb.toString(),new INMOMSPRDTableRowMapper());
            var end = Instant.now();
            log.info("Time taken to fetch 1000 records for from INMOMSPRD is {} milli seconds when last count is {} ", ChronoUnit.MILLIS.between(start,end),lastCount);
            var map = rows.stream().collect(Collectors.toMap(INMOMSPRDDto::getId, Function.identity()));
            try {
                inmomsprdRedisService.saveAll(map);
                lastCount += rows.size();
            }catch (Exception e){
                log.error("error while saving records into redis {} cause is {} ",map.size(),e.getMessage(),e);
            }
            dataCopyProgressService.updateRowsProcessed(INMOMSPRD,lastCount);
            log.info("Scheduler completed for table {} ", INMOMSPRD);
        } catch (Exception e) {
            log.error("error while copying for table {}", INMOMSPRD, e.getMessage(),e);
        }
    }
}
