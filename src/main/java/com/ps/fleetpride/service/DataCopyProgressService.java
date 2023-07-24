package com.ps.fleetpride.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataCopyProgressService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public Long fetchRecordsProcessed(String tableName){
        log.info("fetch rows processed count for table {} ",tableName);
        var query = "SELECT rows_copy_to_redis FROM dbo.data_copy_progress where table_name =:tableName";
        var params = Map.of("tableName", tableName);
        return jdbcTemplate.queryForObject(query, params, Long.class);
    }
    public void updateRowsProcessed(String tableName, Long lastCount){
        log.info("update rows processed count for table {} ",tableName);
        var lastExecutionTime = LocalDateTime.now();
        var query = "UPDATE dbo.data_copy_progress SET rows_copy_to_redis=:lastCount, last_execution_time=:lastExecutionTime where table_name =:tableName";
        var params = Map.of("lastCount",lastCount,"lastExecutionTime",lastExecutionTime,"tableName", tableName);
        jdbcTemplate.update(query, params);
    }
}
