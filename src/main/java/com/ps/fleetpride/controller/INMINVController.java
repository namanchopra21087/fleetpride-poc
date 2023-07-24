package com.ps.fleetpride.controller;

import com.ps.fleetpride.dto.INMINVDto;
import com.ps.fleetpride.dto.INMNPMDto;
import com.ps.fleetpride.service.INMINVDBService;
import com.ps.fleetpride.service.INMINVRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequestMapping("/api/v1/inminv")
@RestController
@RequiredArgsConstructor
@Slf4j
public class INMINVController {

    private final INMINVRedisService inminvRedisService;
    private final INMINVDBService inminvdbService;

    @GetMapping("/{id}")
    public INMINVDto fetchById(@PathVariable("id") Long id) {
        var start = Instant.now();
        log.info("fetch INMINV by ID {} started", id);
        var result = inminvRedisService.getOneById(id);
        log.info("fetch INMINV by ID {} end", id);
        var end = Instant.now();
        log.info("time taken by fetch INMINV by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return result;
    }

    @GetMapping("/from-db/{id}")
    public INMINVDto fetchByIdFromDb(@PathVariable("id") Long id) {
        var start = Instant.now();
        log.info("fetch INMINV from db by ID {} started", id);
        var result = inminvdbService.findById(id);
        log.info("fetch INMINV from db by ID {} end", id);
        var end = Instant.now();
        log.info("time taken by fetch INMINV from db by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return result;
    }

    @GetMapping("/all")
    public List<INMINVDto> fetchAll() {
        log.info("fetch all INMINV started");
        var results = inminvRedisService.getAll().values().stream().toList();
        log.info("fetch all INMINV end");
        return results;
    }

    @PostMapping("/update")
    public INMINVDto UpdateINMINV(@RequestBody INMINVDto inmnpmDto) {
        var start = Instant.now();
        log.info("update INMINV started for id {} ",inmnpmDto.getId());
        inminvdbService.update(inmnpmDto);
        inminvRedisService.update(inmnpmDto);
        log.info("update INMINV end for id {} ",inmnpmDto.getId());
        var end = Instant.now();
        log.info("time taken by update INMINV is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return inmnpmDto;
    }

}
