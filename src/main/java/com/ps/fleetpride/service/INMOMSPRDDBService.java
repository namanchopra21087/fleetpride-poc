package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMNPMDto;
import com.ps.fleetpride.dto.INMOMSPRDDto;
import com.ps.fleetpride.mapper.INMNPMTableRowMapper;
import com.ps.fleetpride.mapper.INMOMSPRDTableRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class INMOMSPRDDBService {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public INMOMSPRDDto update(INMOMSPRDDto inmomsprdDto) {
        var start = Instant.now();
        log.info("Going to update INMOMSPRD into database for id {}", inmomsprdDto.getId());
        String sql = "UPDATE dbo.INMOMSPRD SET PRDPART = ? WHERE ID = ?";

        jdbcTemplate.update(sql, inmomsprdDto.getPrdPart(), inmomsprdDto.getId());

        var end = Instant.now();
        log.info("Time taken to update INMOMSPRD for id {} is {} milli seconds ", inmomsprdDto.getId(), ChronoUnit.MILLIS.between(start, end));
        return inmomsprdDto;
    }
    public INMOMSPRDDto findById(long id) {
        String query = "select ID, PRDPART from dbo.INMOMSPRD WHERE id =:id";
        var params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(query, params, new INMOMSPRDTableRowMapper());
    }
}
