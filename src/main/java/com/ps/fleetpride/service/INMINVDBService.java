package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMINVDto;
import com.ps.fleetpride.mapper.INMINVTableRowMapper;
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
public class INMINVDBService {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public INMINVDto update(INMINVDto invDto){
        var start = Instant.now();
        log.info("Going to update INMINV into database for id {}",invDto.getId());
        String sql = "update dbo.INMINV SET " +
                "LOCATE = ?, POOL = ?, PARTNO = ?, DSPPART = ?, SORTNO = ?, ITYPE = ?, PARTSTATUS = ?, COMPY = ?, " +
                "VENDCODE = ?, SUBVC = ?, INVCLASS = ?, LOCCLASS = ?, CAT = ?, TRACKCAT = ?, PRICECAT = ?, ICODE2 = ?," +
                " ICODE3 = ?, VELPRCCD = ?, ICODE5 = ?, IDESC = ?, IQTYOH = ?, IQTYOR = ?, IQTYBO = ?, ITRIN = ?, " +
                "ITROUT = ?, CUSTALLOC = ?, ISND = ?, IMIN = ?, ITROBO = ?, ILIST = ?, ICOST = ?, ILPRC = ?, AVGCOST = ?," +
                " WHSEUM = ?, WHSEPACK = ?, SALESUM = ?, SALESPACK = ?, XFERUM = ?, XFERPACK = ?, WEIGHT = ?, PVENDOR = ?," +
                " VENDRET = ?, CUSTRET = ?, IDTLSL = ?, IDTLP = ?, ILTD = ?, COREPOOL = ?, COREPART = ?, ICNT = ?, " +
                "ICNTDT = ?, IAIDT = ?, IPRICE1 = ?, IPRICE2 = ?, IPRICE3 = ?, IPRICE4 = ?, IPRICE5 = ?, IPRICEFOC = ?, " +
                "MATRIX = ?, POPINX = ?, USEPOP = ?, USEPDB = ?, PRODLN = ?, LPPART = ?, GLINFO = ?, FOBRULE = ?," +
                " PRCGROUP = ?, PURCHNOTE = ?, SALESNOTE = ?, INVNOTE = ?, BINLOC = ?, ZONE = ?, AISLE = ?, SECTION = ?," +
                " SHELF = ?, BLDQTY = ?, PKGCODE = ?, UPCCODE = ?, VTPRODCD = ?, QTY1MO = ?, QTY3MO = ?, QTY6MO = ?," +
                " QTY12MO = ?, MINFRZDATE = ?, MINAPPROVL = ?, IFORECAST = ?, SAFETYFACT = ?, IMOOH = ?, ITURNS = ?," +
                " IGMROI = ?, IEOQ = ?, TYPECODE = ?, DEMANDCODE = ?, COSTCODE = ?, STOCKCODE = ?, SOURCECODE = ?," +
                " STOCKLOC = ?, PURCHMETH = ?, ORDERPOINT = ?, PURCHFREQ = ?, SERIALFLAG = ?, PURCHFACT = ?, SUPPPART = ?," +
                " BRKEVNCOST = ?, FRTFACTOR = ?, IDTLRCV = ?, IDTLSO = ?, ILASTUPDT = ?, LEADTIME = ?, PARTSTATDT = ?, " +
                "SEASNLNGTH = ?, SEASNMNTH = ?, SELLUNIT = ?, STDSLSUM = ?, VLIST = ?, VMRS = ?, VPACK = ?, VUM = ?, " +
                "VWEIGHT = ?, COSTLOCK = ?, CSTLCKDT = ?, JOBPRICE = ?, DSPCOST = ?, PRODESC = ?, PRLVLUPDDT = ?, " +
                "FOCPRUPDDT = ?, JOBQTY = ?, SEASINX = ?, SAFEINX = ?, IQTYRET = ?, VNDPRDGRP = ?, CPRODLN = ?," +
                " ILPCPDATE = ?, IFRTATTR = ?, PKTNOTE = ?, MANCELL = ?, SHIPPACK = ?, DCSAFTYSTK = ?, DMD1MO = ?, " +
                "DMD3MO = ?, DMD6MO = ?, DMD12MO = ?, IDTLDMD = ?, INFLDA2_A = ?, INFLDB2_A = ?, INFLDA10_A = ?," +
                " INFLDB10_A = ?, INFLDA6_0 = ?, INFLDB6_0 = ?, INFLDA13_2 = ?, INFLDB13_2 = ?, SUPERSEDFL = ?," +
                " SUPERSENTE = ?, IQTYHOLD = ?, IQTYWHSHLD = ?, IQTYWOHLD = ?, IQTYIBTPND = ?, IQTYROHOLD = ?," +
                " IQTCRTUSR = ?, IQTCRTTIM = ?, CHG_CURRUSER = ?, CHG_TIMESTAMP " +
                "where ID = ?";

        jdbcTemplate.update(sql,
                invDto.getLocate(), invDto.getPool(), invDto.getPartNo(), invDto.getDspPart(), invDto.getSortNo(), invDto.getIType(),
                invDto.getPartStatus(), invDto.getCompy(), invDto.getVendCode(), invDto.getSubvc(), invDto.getInvClass(), invDto.getLocClass(),
                invDto.getCat(), invDto.getTrackCat(), invDto.getPriceCat(), invDto.getICode2(), invDto.getICode3(), invDto.getVelPrcCd(),
                invDto.getICode5(), invDto.getIDesc(), invDto.getIQtyOh(), invDto.getIQtyOr(), invDto.getIQtyBo(), invDto.getITrIn(),
                invDto.getITrOut(), invDto.getCustAlloc(), invDto.getISnd(), invDto.getIMin(), invDto.getITrObo(), invDto.getIList(),
                invDto.getICost(), invDto.getILprc(), invDto.getAvgCost(), invDto.getWhseUm(), invDto.getWhsePack(), invDto.getSaleSum(),
                invDto.getSalesPack(), invDto.getXferUm(), invDto.getXferPack(), invDto.getWeight(), invDto.getPVendor(), invDto.getVendRet(),
                invDto.getCustRet(), invDto.getIdtLsl(), invDto.getIdtLp(), invDto.getILtd(), invDto.getCorePool(), invDto.getCorePart(), invDto.getICnt(),
                invDto.getICntDt(), invDto.getIaIdt(), invDto.getIPrice1(), invDto.getIPrice2(), invDto.getIPrice3(), invDto.getIPrice4(), invDto.getIPrice5(),
                invDto.getIPriceFoc(), invDto.getMatrix(), invDto.getPopInx(), invDto.getUsePop(), invDto.getUsePdb(), invDto.getProdLn(), invDto.getLpPart(),
                invDto.getGlInfo(), invDto.getFobRule(), invDto.getPrcGroup(), invDto.getPurchNote(), invDto.getSalesNote(), invDto.getInvNote(),
                invDto.getBinLoc(), invDto.getZone(), invDto.getAisle(), invDto.getSection(), invDto.getShelf(), invDto.getBldQty(),
                invDto.getPkgCode(), invDto.getUpcCode(), invDto.getVtProdCd(), invDto.getQty1Mo(), invDto.getQty3Mo(), invDto.getQty6Mo(),
                invDto.getQty12Mo(), invDto.getMinFrzDate(), invDto.getMinApprovl(), invDto.getIForecast(), invDto.getSafetyFact(),
                invDto.getIMooh(), invDto.getITurns(), invDto.getIGmroi(), invDto.getIEoq(), invDto.getTypeCode(), invDto.getDemandCode(),
                invDto.getCostCode(), invDto.getStockCode(), invDto.getSourceCode(), invDto.getStockLoc(), invDto.getPurchMeth(), invDto.getOrderPoint(),
                invDto.getPurchFreq(), invDto.getSerialFlag(), invDto.getPurchFact(), invDto.getSuppPart(), invDto.getBrkEvnCost(), invDto.getFrtFactor(),
                invDto.getIdtlRcv(), invDto.getIdtlSo(), invDto.getILastUpdt(), invDto.getLeadTime(), invDto.getPartStatDt(), invDto.getSeasnLngth(),
                invDto.getSeasnMnth(), invDto.getSellUnit(), invDto.getStdSlsum(), invDto.getVList(), invDto.getVmrs(), invDto.getVPack(),
                invDto.getVUm(), invDto.getVWeight(), invDto.getCostLock(), invDto.getCstLckDt(), invDto.getJobPrice(), invDto.getDspCost(),
                invDto.getProDesc(), invDto.getPrLvlUpdDt(), invDto.getFocPrUpdDt(), invDto.getJobQty(), invDto.getSeasInx(), invDto.getSafeInx(),
                invDto.getIQtyRet(), invDto.getVndPrdGrp(), invDto.getCProdLn(), invDto.getILpCpDate(), invDto.getIFrtAttr(), invDto.getPktNote(),
                invDto.getManCell(), invDto.getShipPack(), invDto.getDcsAfStyStk(), invDto.getDmd1Mo(), invDto.getDmd3Mo(), invDto.getDmd6Mo(),
                invDto.getDmd12Mo(), invDto.getIdtlDmd(), invDto.getInflDa2A(), invDto.getInflDb2A(), invDto.getInflDa10A(), invDto.getInflDb10A(),
                invDto.getInflDa6_0(), invDto.getInflDb6_0(), invDto.getInflDa13_2(), invDto.getInflDb13_2(), invDto.getSupersedeFl(), invDto.getSupersente(),
                invDto.getIQtyHold(), invDto.getIQtyWhShld(), invDto.getIQtyWoHld(), invDto.getIQtyIbtpnd(), invDto.getIQtyRoHold(), invDto.getIQtCrtUsr(),
                invDto.getIQtCrtTim(), invDto.getChgCurrUser(), invDto.getChgTimestamp(),

                invDto.getId());

            var end = Instant.now();
            log.info("Time taken to update INMINV for id {} is {} milli seconds ",invDto.getId(), ChronoUnit.MILLIS.between(start,end));
        return invDto;
    }
    public INMINVDto findById(long id){
        String query ="SELECT ID, LOCATE, POOL, PARTNO, DSPPART, SORTNO, ITYPE, PARTSTATUS, COMPY, VENDCODE, SUBVC, INVCLASS, LOCCLASS, CAT, TRACKCAT, PRICECAT, ICODE2, ICODE3, VELPRCCD, ICODE5, IDESC, IQTYOH, IQTYOR, IQTYBO, ITRIN, ITROUT, CUSTALLOC, ISND, IMIN, ITROBO, ILIST, ICOST, ILPRC, AVGCOST, WHSEUM, WHSEPACK, SALESUM, SALESPACK, XFERUM, XFERPACK, WEIGHT, PVENDOR, VENDRET, CUSTRET, IDTLSL, IDTLP, ILTD, COREPOOL, COREPART, ICNT, ICNTDT, IAIDT, IPRICE1, IPRICE2, IPRICE3, IPRICE4, IPRICE5, IPRICEFOC, MATRIX, POPINX, USEPOP, USEPDB, PRODLN, LPPART, GLINFO, FOBRULE, PRCGROUP, PURCHNOTE, SALESNOTE, INVNOTE, BINLOC, ZONE, AISLE, SECTION, SHELF, BLDQTY, PKGCODE, UPCCODE, VTPRODCD, QTY1MO, QTY3MO, QTY6MO, QTY12MO, MINFRZDATE, MINAPPROVL, IFORECAST, SAFETYFACT, IMOOH, ITURNS, IGMROI, IEOQ, TYPECODE, DEMANDCODE, COSTCODE, STOCKCODE, SOURCECODE, STOCKLOC, PURCHMETH, ORDERPOINT, PURCHFREQ, SERIALFLAG, PURCHFACT, SUPPPART, BRKEVNCOST, FRTFACTOR, IDTLRCV, IDTLSO, ILASTUPDT, LEADTIME, PARTSTATDT, SEASNLNGTH, SEASNMNTH, SELLUNIT, STDSLSUM, VLIST, VMRS, VPACK, VUM, VWEIGHT, COSTLOCK, CSTLCKDT, JOBPRICE, DSPCOST, PRODESC, PRLVLUPDDT, FOCPRUPDDT, JOBQTY, SEASINX, SAFEINX, IQTYRET, VNDPRDGRP, CPRODLN, ILPCPDATE, IFRTATTR, PKTNOTE, MANCELL, SHIPPACK, DCSAFTYSTK, DMD1MO, DMD3MO, DMD6MO, DMD12MO, IDTLDMD, INFLDA2_A, INFLDB2_A, INFLDA10_A, INFLDB10_A, INFLDA6_0, INFLDB6_0, INFLDA13_2, INFLDB13_2, SUPERSEDFL, SUPERSENTE, IQTYHOLD, IQTYWHSHLD, IQTYWOHLD, IQTYIBTPND, IQTYROHOLD, IQTCRTUSR, IQTCRTTIM, CHG_CURRUSER, CHG_TIMESTAMP from dbo.INMINV where id=:id";
        var params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(query, params, new INMINVTableRowMapper());
    }
}
