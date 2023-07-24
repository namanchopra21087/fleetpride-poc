package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMNPMDto;
import com.ps.fleetpride.mapper.INMNPMTableRowMapper;
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
public class INMNPMDBService {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public INMNPMDto update(INMNPMDto inmnpmDto){
        var start = Instant.now();
        log.info("Going to update INMNPM into database for id {}",inmnpmDto.getId());
        String sql = "UPDATE dbo.INMNPM SET " +
                "NPCOMPY = ?, NPPOOL = ?, NPPARTNO = ?, NPDSPPART = ?, NPITYPE = ?, NPSORTNO = ?, NPVENDCODE = ?, NPSUBVC = ?, " +
                "NPINVCLASS = ?, NPCAT = ?, NPTRACKCAT = ?, NPIDESC = ?, NPDESCLCK = ?, NPILIST = ?, NPICOST = ?, " +
                "NPWHSEUM = ?, NPWHSEPACK = ?, NPXFERUM = ?, NPXFERPACK = ?, NPSLSUM = ?, NPSLSPACK = ?, NPPVENDOR = ?, " +
                "NPWEIGHT = ?, NPVENDRET = ?, NPCUSTRET = ?, NPCOREPOOL = ?, NPCOREPART = ?, NPIPRICE5 = ?, NPPRC5CHG = ?, " +
                "NPPOPINX = ?, NPPRODLN = ?, NPLPPART = ?, NPGLINFO = ?, NPPKGCODE = ?, NPVTPRODCD = ?, NPCOSTCODE = ?, " +
                "NPSERLFLAG = ?, NPSUPPPART = ?, NPJOBPRICE = ?, NPPRODESC = ?, NPCPRODLN = ?, NPIFRTATTR = ?, NPNEWPART = ?, " +
                "NPVPRDGRP = ?, NPSOURCE = ?, NPPVTBRAND = ?, NPCOUNTRY = ?, NPHAZMAT = ?, NPUNSPSC = ?, NPVMRS = ?, " +
                "NPJOBQTY = ?, NPPRCGRP = ?, NPSHARIMAG = ?, NPSALENOTE = ?, NPPARTIDX = ?, NPLENGTH = ?, NPWIDTH = ?, " +
                "NPDEPTH = ?, NPSLVLA = ?, NPSLVLB = ?, NPSLVLC = ?, NPSLVLD = ?, NPCLEANDT = ?, NPUPCCODE = ?, NPGTIN = ?, " +
                "NPPURCHFCT = ?, NPSHIPPACK = ?, NPIPRICE1 = ?, NPIPRICE2 = ?, NPIPRICE3 = ?, NPIPRICE4 = ?, NPMATRIX = ?, " +
                "NPDFTSRCCD = ?, NPREVIEWID = ?, NPREVIEWDT = ?, NPLSTCHGUS = ?, NPLSTCHGDT = ?, NPBRANDCD = ? " +
                "WHERE ID = ?";

        jdbcTemplate.update(sql,
                inmnpmDto.getNpcompy(), inmnpmDto.getNppool(), inmnpmDto.getNppartno(), inmnpmDto.getNpdsppart(),
                inmnpmDto.getNpitype(), inmnpmDto.getNpsortno(), inmnpmDto.getNpvendcode(), inmnpmDto.getNpsubvc(),
                inmnpmDto.getNpinvclass(), inmnpmDto.getNpcat(), inmnpmDto.getNptrackcat(), inmnpmDto.getNpidesc(),
                inmnpmDto.getNpdesclck(), inmnpmDto.getNpilist(), inmnpmDto.getNpicost(), inmnpmDto.getNpwhseum(),
                inmnpmDto.getNpwhsepack(), inmnpmDto.getNpxferum(), inmnpmDto.getNpxferpack(), inmnpmDto.getNpslsum(),
                inmnpmDto.getNpslspack(), inmnpmDto.getNpvendor(), inmnpmDto.getNpweight(), inmnpmDto.getNpvendret(),
                inmnpmDto.getNpcustret(), inmnpmDto.getNpcorepool(), inmnpmDto.getNpcorepart(), inmnpmDto.getNpiprice5(),
                inmnpmDto.getNpprc5chg(), inmnpmDto.getNppopinx(), inmnpmDto.getNpprodln(), inmnpmDto.getNplppart(),
                inmnpmDto.getNpglinfo(), inmnpmDto.getNppkgcode(), inmnpmDto.getNpvtprodc(), inmnpmDto.getNpcostcode(),
                inmnpmDto.getNpserlflag(), inmnpmDto.getNpsupppart(), inmnpmDto.getNpjobprice(), inmnpmDto.getNpprodesc(),
                inmnpmDto.getNpcprodl(), inmnpmDto.getNpifrtattr(), inmnpmDto.getNpnewpart(), inmnpmDto.getNpvprdgrp(),
                inmnpmDto.getNpsource(), inmnpmDto.getNppvtbrand(), inmnpmDto.getNpcountry(), inmnpmDto.getNphazmat(),
                inmnpmDto.getNpunspsc(), inmnpmDto.getNpvmrs(), inmnpmDto.getNpjobqty(), inmnpmDto.getNpprcgrp(),
                inmnpmDto.getNpsharimag(), inmnpmDto.getNpsalenote(), inmnpmDto.getNppartidx(), inmnpmDto.getNplength(),
                inmnpmDto.getNpwidth(), inmnpmDto.getNpdepth(), inmnpmDto.getNpslva(), inmnpmDto.getNpslvb(),
                inmnpmDto.getNpslvc(), inmnpmDto.getNpslvdd(), inmnpmDto.getNpcleandt(), inmnpmDto.getNpupccode(),
                inmnpmDto.getNpgtin(), inmnpmDto.getNpurchfct(), inmnpmDto.getNpshippack(), inmnpmDto.getNpiprice1(),
                inmnpmDto.getNpiprice2(), inmnpmDto.getNpiprice3(), inmnpmDto.getNpiprice4(), inmnpmDto.getNpmtrx(),
                inmnpmDto.getNpdftscc(), inmnpmDto.getNprevwid(), inmnpmDto.getNprevwdt(), inmnpmDto.getNplstchgs(),
                inmnpmDto.getNplstchgd(), inmnpmDto.getNpbrandcd(), inmnpmDto.getId());

            var end = Instant.now();
            log.info("Time taken to update INMNPM for id {} is {} milli seconds ",inmnpmDto.getId(), ChronoUnit.MILLIS.between(start,end));
        return inmnpmDto;
    }
    public INMNPMDto findById(long id){
        String query ="SELECT ID, NPCOMPY ,NPPOOL ,NPPARTNO ,NPDSPPART ,NPITYPE ,NPSORTNO ,NPVENDCODE ,NPSUBVC ,NPINVCLASS ,NPCAT ,NPTRACKCAT ,NPIDESC ,NPDESCLCK ,NPILIST ,NPICOST ,NPWHSEUM ,NPWHSEPACK ,NPXFERUM ,NPXFERPACK ,NPSLSUM ,NPSLSPACK ,NPPVENDOR ,NPWEIGHT ,NPVENDRET ,NPCUSTRET ,NPCOREPOOL ,NPCOREPART ,NPIPRICE5 ,NPPRC5CHG ,NPPOPINX ,NPPRODLN ,NPLPPART ,NPGLINFO ,NPPKGCODE ,NPVTPRODCD ,NPCOSTCODE ,NPSERLFLAG ,NPSUPPPART ,NPJOBPRICE ,NPPRODESC ,NPCPRODLN ,NPIFRTATTR ,NPNEWPART ,NPVPRDGRP ,NPSOURCE ,NPPVTBRAND ,NPCOUNTRY ,NPHAZMAT ,NPUNSPSC ,NPVMRS ,NPJOBQTY ,NPPRCGRP ,NPSHARIMAG ,NPSALENOTE ,NPPARTIDX ,NPLENGTH ,NPWIDTH ,NPDEPTH ,NPSLVLA ,NPSLVLB ,NPSLVLC ,NPSLVLD ,NPCLEANDT ,NPUPCCODE ,NPGTIN ,NPPURCHFCT ,NPSHIPPACK ,NPIPRICE1 ,NPIPRICE2 ,NPIPRICE3 ,NPIPRICE4 ,NPMATRIX ,NPDFTSRCCD ,NPREVIEWID ,NPREVIEWDT ,NPLSTCHGUS ,NPLSTCHGDT ,NPBRANDCD FROM dbo.INMNPM WHERE id =:id";
        var params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(query, params, new INMNPMTableRowMapper());
    }
}
