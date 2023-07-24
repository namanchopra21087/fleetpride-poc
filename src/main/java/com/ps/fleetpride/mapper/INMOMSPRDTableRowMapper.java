package com.ps.fleetpride.mapper;

import com.ps.fleetpride.dto.INMOMSPRDDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class INMOMSPRDTableRowMapper implements RowMapper<INMOMSPRDDto> {
    @Override
    public INMOMSPRDDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        INMOMSPRDDto inmnpmDto = new INMOMSPRDDto();
        inmnpmDto.setId(rs.getLong("ID"));
        inmnpmDto.setPrdPart(rs.getString("PRDPART"));

        return inmnpmDto;
    }
}
