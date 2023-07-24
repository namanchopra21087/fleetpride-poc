package com.ps.fleetpride.component;

import com.ps.fleetpride.constant.Tables;
import com.ps.fleetpride.dto.INMINVDto;
import com.ps.fleetpride.mapper.INMINVTableRowMapper;
import com.ps.fleetpride.service.DataCopyProgressService;
import com.ps.fleetpride.service.INMINVRedisService;
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
public class CopySqlTableINMINVToRedisComponent {
    private final JdbcTemplate jdbcTemplate;
    private final DataCopyProgressService dataCopyProgressService;
    private final INMINVRedisService inminvRedisService;
    private String INMINV = Tables.INMINV.name();

    @Scheduled(fixedDelay = 3,timeUnit = TimeUnit.SECONDS) // fixed rate used to make sure the previous execution is completed
    public void copyINMINVDataToRedis() {
        try {
            var start = Instant.now();
            log.info("Scheduler running for table {} ", INMINV);
            long lastCount = dataCopyProgressService.fetchRecordsProcessed(INMINV);
            log.info("Total {} records copied to redis are {}", INMINV, lastCount);
            StringBuilder sb = new StringBuilder("SELECT ID, LOCATE ,POOL ,PARTNO ,DSPPART ,SORTNO ,ITYPE ,PARTSTATUS ,COMPY ,VENDCODE ,SUBVC ,INVCLASS ,LOCCLASS ,CAT ,TRACKCAT ,PRICECAT ,ICODE2 ,ICODE3 ,VELPRCCD ,ICODE5 ,IDESC ,IQTYOH ,IQTYOR ,IQTYBO ,ITRIN ,ITROUT ,CUSTALLOC ,ISND ,IMIN ,ITROBO ,ILIST ,ICOST ,ILPRC ,AVGCOST ,WHSEUM ,WHSEPACK ,SALESUM ,SALESPACK ,XFERUM ,XFERPACK ,WEIGHT ,PVENDOR ,VENDRET ,CUSTRET ,IDTLSL ,IDTLP ,ILTD ,COREPOOL ,COREPART ,ICNT ,ICNTDT ,IAIDT ,IPRICE1 ,IPRICE2 ,IPRICE3 ,IPRICE4 ,IPRICE5 ,IPRICEFOC ,MATRIX ,POPINX ,USEPOP ,USEPDB ,PRODLN ,LPPART ,GLINFO ,FOBRULE ,PRCGROUP ,PURCHNOTE ,SALESNOTE ,INVNOTE ,BINLOC ,ZONE ,AISLE ,SECTION ,SHELF ,BLDQTY ,PKGCODE ,UPCCODE ,VTPRODCD ,QTY1MO ,QTY3MO ,QTY6MO ,QTY12MO ,MINFRZDATE ,MINAPPROVL ,IFORECAST ,SAFETYFACT ,IMOOH ,ITURNS ,IGMROI ,IEOQ ,TYPECODE ,DEMANDCODE ,COSTCODE ,STOCKCODE ,SOURCECODE ,STOCKLOC ,PURCHMETH ,ORDERPOINT ,PURCHFREQ ,SERIALFLAG ,PURCHFACT ,SUPPPART ,BRKEVNCOST ,FRTFACTOR ,IDTLRCV ,IDTLSO ,ILASTUPDT ,LEADTIME ,PARTSTATDT ,SEASNLNGTH ,SEASNMNTH ,SELLUNIT ,STDSLSUM ,VLIST ,VMRS ,VPACK ,VUM ,VWEIGHT ,COSTLOCK ,CSTLCKDT ,JOBPRICE ,DSPCOST ,PRODESC ,PRLVLUPDDT ,FOCPRUPDDT ,JOBQTY ,SEASINX ,SAFEINX ,IQTYRET ,VNDPRDGRP ,CPRODLN ,ILPCPDATE ,IFRTATTR ,PKTNOTE ,MANCELL ,SHIPPACK ,DCSAFTYSTK ,DMD1MO ,DMD3MO ,DMD6MO ,DMD12MO ,IDTLDMD ,INFLDA2_A ,INFLDB2_A ,INFLDA10_A ,INFLDB10_A ,INFLDA6_0 ,INFLDB6_0 ,INFLDA13_2 ,INFLDB13_2 ,SUPERSEDFL ,SUPERSENTE ,IQTYHOLD ,IQTYWHSHLD ,IQTYWOHLD ,IQTYIBTPND ,IQTYROHOLD ,IQTCRTUSR ,IQTCRTTIM ,CHG_CURRUSER ,CHG_TIMESTAMP FROM DBO.INMINV_NEW ORDER BY ID OFFSET").append(" ").append(lastCount).append(" ").append("ROWS FETCH NEXT 1000 ROWS ONLY");
            var rows = jdbcTemplate.query(sb.toString(), new INMINVTableRowMapper());
            var end = Instant.now();
            log.info("Time taken to fetch 1000 records for from INMINV is {} milli seconds when last count is {} ", ChronoUnit.MILLIS.between(start, end), lastCount);
            var map = rows.stream().collect(Collectors.toMap(INMINVDto::getId, Function.identity()));
            try {
                inminvRedisService.saveAll(map);
                lastCount += rows.size();
            } catch (Exception e) {
                log.error("error while saving records {} for table {} into redis cause is {} ", map.size(), INMINV, e.getMessage(), e);
            }
            dataCopyProgressService.updateRowsProcessed(INMINV, lastCount);
            log.info("Scheduler completed for table {} ", INMINV);
        } catch (Exception e) {
            log.error("error while running for table {}", INMINV, e.getMessage(), e);
        }
    }
}
