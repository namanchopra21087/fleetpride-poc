package com.ps.fleetpride.controller;

import com.ps.fleetpride.dto.INMOMSPRDDto;
import com.ps.fleetpride.service.INMOMSPRDDBService;
import com.ps.fleetpride.service.INMOMSPRDRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequestMapping("/api/v1/inmomsprd")
@RestController
@RequiredArgsConstructor
@Slf4j
public class INMOMSPRDController {

  private final INMOMSPRDRedisService inmomsprdRedisService;
  private final INMOMSPRDDBService inmomsprddbService;

  @GetMapping("/{id}")
  public INMOMSPRDDto fetchById(@PathVariable("id") Long id) {
    var start = Instant.now();
    log.info("fetch INMOMSPRD by ID {} started", id);
    var result = inmomsprdRedisService.getOneById(id);
    log.info("fetch INMOMSPRD by ID {} end", id);
    var end = Instant.now();
    log.info("time taken by fetch INMOMSPRD by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
    return result;
  }
  @GetMapping("/from-db/{id}")
  public INMOMSPRDDto fetchByIdFromDb(@PathVariable("id") Long id) {
    var start = Instant.now();
    log.info("fetch INMOMSPRD from db by ID {} started", id);
    var result = inmomsprddbService.findById(id);
    log.info("fetch INMOMSPRD from db by ID {} end", id);
    var end = Instant.now();
    log.info("time taken by fetch INMOMSPRD from db by ID is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
    return result;
  }
  @GetMapping("/all")
  public List<INMOMSPRDDto> fetchAll() {
    log.info("fetch all INMOMSPRD started");
    var results = inmomsprdRedisService.getAll().values().stream().toList();
    log.info("fetch all INMOMSPRD end");
    return results;
  }

  @PostMapping("/update")
  public INMOMSPRDDto UpdateINMOMSPRDD(@RequestBody INMOMSPRDDto inmnpmDto) {
    var start = Instant.now();
    log.info("update INMOMSPRD started for id {} ",inmnpmDto.getId());
    inmomsprddbService.update(inmnpmDto);
    inmomsprdRedisService.update(inmnpmDto);
    log.info("update INMOMSPRD end for id {} ",inmnpmDto.getId());
    var end = Instant.now();
    log.info("time taken by update INMOMSPRD is {} milli seconds", ChronoUnit.MILLIS.between(start,end));
    return inmnpmDto;
  }

}
