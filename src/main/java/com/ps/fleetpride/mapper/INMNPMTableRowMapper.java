package com.ps.fleetpride.mapper;

import com.ps.fleetpride.dto.INMNPMDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class INMNPMTableRowMapper implements RowMapper<INMNPMDto> {
    @Override
    public INMNPMDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        INMNPMDto inmnpmDto = new INMNPMDto();
        inmnpmDto.setId(rs.getLong("ID"));
        inmnpmDto.setNpcompy(rs.getBigDecimal("NPCOMPY"));
        inmnpmDto.setNppool(rs.getBigDecimal("NPPOOL"));
        inmnpmDto.setNppartno(rs.getString("NPPARTNO"));
        inmnpmDto.setNpdsppart(rs.getString("NPDSPPART"));
        inmnpmDto.setNpitype(rs.getString("NPITYPE"));
        inmnpmDto.setNpsortno(rs.getString("NPSORTNO"));
        inmnpmDto.setNpvendcode(rs.getString("NPVENDCODE"));
        inmnpmDto.setNpsubvc(rs.getString("NPSUBVC"));
        inmnpmDto.setNpinvclass(rs.getString("NPINVCLASS"));
        inmnpmDto.setNpcat(rs.getString("NPCAT"));
        inmnpmDto.setNptrackcat(rs.getString("NPTRACKCAT"));
        inmnpmDto.setNpidesc(rs.getString("NPIDESC"));
        inmnpmDto.setNpdesclck(rs.getString("NPDESCLCK"));
        inmnpmDto.setNpilist(rs.getBigDecimal("NPILIST"));
        inmnpmDto.setNpicost(rs.getBigDecimal("NPICOST"));
        inmnpmDto.setNpwhseum(rs.getString("NPWHSEUM"));
        inmnpmDto.setNpwhsepack(rs.getBigDecimal("NPWHSEPACK"));
        inmnpmDto.setNpxferum(rs.getString("NPXFERUM"));
        inmnpmDto.setNpxferpack(rs.getBigDecimal("NPXFERPACK"));
        inmnpmDto.setNpslsum(rs.getString("NPSLSUM"));
        inmnpmDto.setNpslspack(rs.getBigDecimal("NPSLSPACK"));
        inmnpmDto.setNpvendor(rs.getBigDecimal("NPPVENDOR"));
        inmnpmDto.setNpweight(rs.getBigDecimal("NPWEIGHT"));
        inmnpmDto.setNpvendret(rs.getString("NPVENDRET"));
        inmnpmDto.setNpcustret(rs.getString("NPCUSTRET"));
        inmnpmDto.setNpcorepool(rs.getBigDecimal("NPCOREPOOL"));
        inmnpmDto.setNpcorepart(rs.getString("NPCOREPART"));
        inmnpmDto.setNpiprice5(rs.getBigDecimal("NPIPRICE5"));
        inmnpmDto.setNpprc5chg(rs.getDate("NPPRC5CHG"));
        inmnpmDto.setNppopinx(rs.getBigDecimal("NPPOPINX"));
        inmnpmDto.setNpprodln(rs.getBigDecimal("NPPRODLN"));
        inmnpmDto.setNplppart(rs.getString("NPLPPART"));
        inmnpmDto.setNpglinfo(rs.getString("NPGLINFO"));
        inmnpmDto.setNppkgcode(rs.getString("NPPKGCODE"));
        inmnpmDto.setNpvtprodc(rs.getString("NPVTPRODCD"));
        inmnpmDto.setNpcostcode(rs.getString("NPCOSTCODE"));
        inmnpmDto.setNpserlflag(rs.getString("NPSERLFLAG"));
        inmnpmDto.setNpsupppart(rs.getString("NPSUPPPART"));
        inmnpmDto.setNpjobprice(rs.getBigDecimal("NPJOBPRICE"));
        inmnpmDto.setNpprodesc(rs.getString("NPPRODESC"));
        inmnpmDto.setNpcprodl(rs.getBigDecimal("NPCPRODLN"));
        inmnpmDto.setNpifrtattr(rs.getString("NPIFRTATTR"));
        inmnpmDto.setNpnewpart(rs.getString("NPNEWPART"));
        inmnpmDto.setNpvprdgrp(rs.getString("NPVPRDGRP"));
        inmnpmDto.setNpsource(rs.getString("NPSOURCE"));
        inmnpmDto.setNppvtbrand(rs.getString("NPPVTBRAND"));
        inmnpmDto.setNpcountry(rs.getString("NPCOUNTRY"));
        inmnpmDto.setNphazmat(rs.getString("NPHAZMAT"));
        inmnpmDto.setNpunspsc(rs.getString("NPUNSPSC"));
        inmnpmDto.setNpvmrs(rs.getString("NPVMRS"));
        inmnpmDto.setNpjobqty(rs.getBigDecimal("NPJOBQTY"));
        inmnpmDto.setNpprcgrp(rs.getString("NPPRCGRP"));
        inmnpmDto.setNpsharimag(rs.getString("NPSHARIMAG"));
        inmnpmDto.setNpsalenote(rs.getString("NPSALENOTE"));
        inmnpmDto.setNppartidx(rs.getBigDecimal("NPPARTIDX"));
        inmnpmDto.setNplength(rs.getBigDecimal("NPLENGTH"));
        inmnpmDto.setNpwidth(rs.getBigDecimal("NPWIDTH"));
        inmnpmDto.setNpdepth(rs.getBigDecimal("NPDEPTH"));
        inmnpmDto.setNpslva(rs.getBigDecimal("NPSLVLA"));
        inmnpmDto.setNpslvb(rs.getBigDecimal("NPSLVLB"));
        inmnpmDto.setNpslvc(rs.getBigDecimal("NPSLVLC"));
        inmnpmDto.setNpslvdd(rs.getBigDecimal("NPSLVLD"));
        inmnpmDto.setNpcleandt(rs.getDate("NPCLEANDT"));
        inmnpmDto.setNpupccode(rs.getString("NPUPCCODE"));
        inmnpmDto.setNpgtin(rs.getString("NPGTIN"));
        inmnpmDto.setNpurchfct(rs.getBigDecimal("NPPURCHFCT"));
        inmnpmDto.setNpshippack(rs.getBigDecimal("NPSHIPPACK"));
        inmnpmDto.setNpiprice1(rs.getBigDecimal("NPIPRICE1"));
        inmnpmDto.setNpiprice2(rs.getBigDecimal("NPIPRICE2"));
        inmnpmDto.setNpiprice3(rs.getBigDecimal("NPIPRICE3"));
        inmnpmDto.setNpiprice4(rs.getBigDecimal("NPIPRICE4"));
        inmnpmDto.setNpmtrx(rs.getString("NPMATRIX"));
        inmnpmDto.setNpdftscc(rs.getString("NPDFTSRCCD"));
        inmnpmDto.setNprevwid(rs.getString("NPREVIEWID"));
        inmnpmDto.setNprevwdt(rs.getDate("NPREVIEWDT"));
        inmnpmDto.setNplstchgs(rs.getString("NPLSTCHGUS"));
        inmnpmDto.setNplstchgd(rs.getDate("NPLSTCHGDT"));
        inmnpmDto.setNpbrandcd(rs.getString("NPBRANDCD"));

        return inmnpmDto;
    }
}
