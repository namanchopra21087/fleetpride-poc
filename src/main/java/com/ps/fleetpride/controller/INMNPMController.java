package com.ps.fleetpride.controller;

import com.ps.fleetpride.dto.INMNPMDto;
import com.ps.fleetpride.service.INMNPMRedisService;
import com.ps.fleetpride.service.INMNPMDBService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequestMapping("/api/v1/inmnpm")
@RestController
@RequiredArgsConstructor
@Slf4j
public class INMNPMController {

    private final INMNPMRedisService inmnpmRedisService;
    private final INMNPMDBService inmnpmdbService;

    @GetMapping("/{id}")
    public INMNPMDto fetchById(@PathVariable("id") Long id) {
        var start = Instant.now();
        log.info("fetch INMNPM by ID {} started", id);
        var result = inmnpmRedisService.getOneById(id);
        log.info("fetch INMNPM by ID {} end", id);
        var end = Instant.now();
        log.info("time taken by fetch INMNPM by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return result;
    }

    @GetMapping("/from-db/{id}")
    public INMNPMDto fetchByIdFromDb(@PathVariable("id") Long id) {
        var start = Instant.now();
        log.info("fetch INMNPM from db by ID {} started", id);
        var result = inmnpmdbService.findById(id);
        log.info("fetch INMNPM from db by ID {} end", id);
        var end = Instant.now();
        log.info("time taken by fetch INMNPM from db by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return result;
    }

    @GetMapping("/all")
    public List<INMNPMDto> fetchAll() {
        log.info("fetch all INMNPM started");
        var results = inmnpmRedisService.getAll().values().stream().toList();
        log.info("fetch all INMNPM end");
        return results;
    }

    @PostMapping("/update")
    public INMNPMDto UpdateINMNPM(@RequestBody INMNPMDto inmnpmDto) {
        var start = Instant.now();
        log.info("update INMNPM started for id {} ",inmnpmDto.getId());
        inmnpmdbService.update(inmnpmDto);
        inmnpmRedisService.update(inmnpmDto);
        log.info("update INMNPM end for id {} ",inmnpmDto.getId());
        var end = Instant.now();
        log.info("time taken by update INMNPM is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
        return inmnpmDto;
    }

}
