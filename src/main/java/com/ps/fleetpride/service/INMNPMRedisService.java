package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMNPMDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
@Service
@Slf4j
@RequiredArgsConstructor
public class INMNPMRedisService {
    private final String hashReference= "INMNPM_KEYS";
    private final RedisTemplate redisTemplate;
    public void save(INMNPMDto inmnpm) {
        log.info("save inmnpm for id {} into redis" , inmnpm.getId());
        redisTemplate.opsForHash().putIfAbsent(hashReference, inmnpm.getId(), inmnpm);
        log.info("save INMNPM successful");
    }
    public void saveAll(Map<Long, INMNPMDto> map) {
        log.info("save {} INMNPM redis" , map.size());
        redisTemplate.opsForHash().putAll(hashReference, map);
        log.info("save all INMNPM successful");
    }
    public INMNPMDto getOneById(Long id) {
        log.info("get INMNPM from redis for id {} " , id);
        return (INMNPMDto) redisTemplate.opsForHash().get(hashReference, id);
    }
    public void update(INMNPMDto inmnpm) {
        var start = Instant.now();
        log.info("update INMNPM into redis for id {} " , inmnpm.getId());
        redisTemplate.opsForHash().put(hashReference, inmnpm.getId(), inmnpm);
        var end = Instant.now();
        log.info("time taken to update INMNPM into redis for id {} is {} milli seconds ",inmnpm.getId(), ChronoUnit.MILLIS.between(start,end));
    }
    public Map<Long, INMNPMDto> getAll() {
        return redisTemplate.opsForHash().entries(hashReference);
    }
    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete(hashReference, id);
    }
}
