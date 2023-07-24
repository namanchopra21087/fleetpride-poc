package com.ps.fleetpride.component;

import com.ps.fleetpride.constant.Tables;
import com.ps.fleetpride.dto.INMNPMDto;
import com.ps.fleetpride.mapper.INMNPMTableRowMapper;
import com.ps.fleetpride.service.DataCopyProgressService;
import com.ps.fleetpride.service.INMNPMRedisService;
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
public class CopySqlTableINMNPMToRedisComponent {

    private final JdbcTemplate jdbcTemplate;
    private final DataCopyProgressService dataCopyProgressService;
    private final INMNPMRedisService inmnpmRedisService;
    private final String INMNPM = Tables.INMNPM.name();
    @Scheduled(fixedDelay = 3,timeUnit = TimeUnit.SECONDS)
    public void copyINMNPMDataToRedis() {
        try {
            var start = Instant.now();
            log.info("Scheduler running for table {} ", INMNPM);
            long lastCount = dataCopyProgressService.fetchRecordsProcessed(INMNPM);
            log.info("Total records copied to redis are {}",lastCount);
            StringBuilder sb = new StringBuilder("SELECT ID, NPCOMPY ,NPPOOL ,NPPARTNO ,NPDSPPART ,NPITYPE ,NPSORTNO ,NPVENDCODE ,NPSUBVC ,NPINVCLASS ,NPCAT ,NPTRACKCAT ,NPIDESC ,NPDESCLCK ,NPILIST ,NPICOST ,NPWHSEUM ,NPWHSEPACK ,NPXFERUM ,NPXFERPACK ,NPSLSUM ,NPSLSPACK ,NPPVENDOR ,NPWEIGHT ,NPVENDRET ,NPCUSTRET ,NPCOREPOOL ,NPCOREPART ,NPIPRICE5 ,NPPRC5CHG ,NPPOPINX ,NPPRODLN ,NPLPPART ,NPGLINFO ,NPPKGCODE ,NPVTPRODCD ,NPCOSTCODE ,NPSERLFLAG ,NPSUPPPART ,NPJOBPRICE ,NPPRODESC ,NPCPRODLN ,NPIFRTATTR ,NPNEWPART ,NPVPRDGRP ,NPSOURCE ,NPPVTBRAND ,NPCOUNTRY ,NPHAZMAT ,NPUNSPSC ,NPVMRS ,NPJOBQTY ,NPPRCGRP ,NPSHARIMAG ,NPSALENOTE ,NPPARTIDX ,NPLENGTH ,NPWIDTH ,NPDEPTH ,NPSLVLA ,NPSLVLB ,NPSLVLC ,NPSLVLD ,NPCLEANDT ,NPUPCCODE ,NPGTIN ,NPPURCHFCT ,NPSHIPPACK ,NPIPRICE1 ,NPIPRICE2 ,NPIPRICE3 ,NPIPRICE4 ,NPMATRIX ,NPDFTSRCCD ,NPREVIEWID ,NPREVIEWDT ,NPLSTCHGUS ,NPLSTCHGDT ,NPBRANDCD FROM dbo.INMNPM ORDER BY ID OFFSET").append(" ").append(lastCount).append(" ").append("ROWS FETCH NEXT 1000 ROWS ONLY");

            var rows = jdbcTemplate.query(sb.toString(),new INMNPMTableRowMapper());
            var end = Instant.now();
            log.info("Time taken to fetch 1000 records for from INMNPM is {} milli seconds when last count is {} ", ChronoUnit.MILLIS.between(start,end),lastCount);
            var map = rows.stream().collect(Collectors.toMap(INMNPMDto::getId, Function.identity()));
            try {
                inmnpmRedisService.saveAll(map);
                lastCount += rows.size();
            }catch (Exception e){
                log.error("error while saving records into redis {} cause is {} ",map.size(),e.getMessage(),e);
            }
            dataCopyProgressService.updateRowsProcessed(INMNPM,lastCount);
            log.info("Scheduler completed for table {} ", INMNPM);
        } catch (Exception e) {
            log.error("error while copying for table {}", INMNPM, e.getMessage(),e);
        }
    }
}
