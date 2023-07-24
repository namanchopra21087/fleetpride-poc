package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMOMSPRDDto;
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
public class INMOMSPRDRedisService {
    private final String hashReference= "INMOMSPRD_KEYS";
    private final RedisTemplate redisTemplate;
    public void save(INMOMSPRDDto inmnpm) {
        log.info("save INMOMSPRD for id {} into redis" , inmnpm.getId());
        redisTemplate.opsForHash().putIfAbsent(hashReference, inmnpm.getId(), inmnpm);
        log.info("save INMOMSPRD successful");
    }
    public void saveAll(Map<Long, INMOMSPRDDto> map) {
        log.info("save {} INMOMSPRD redis" , map.size());
        redisTemplate.opsForHash().putAll(hashReference, map);
        log.info("save all INMOMSPRDsuccessful");
    }
    public INMOMSPRDDto getOneById(Long id) {
        log.info("get INMOMSPRD from redis for id {} " , id);
        return (INMOMSPRDDto) redisTemplate.opsForHash().get(hashReference, id);
    }
    public void update(INMOMSPRDDto inmnpm) {
        var start = Instant.now();
        log.info("update INMOMSPRD into redis for id {} " , inmnpm.getId());
        redisTemplate.opsForHash().put(hashReference, inmnpm.getId(), inmnpm);
        var end = Instant.now();
        log.info("time taken to update INMOMSPRD into redis for id {} is {} milli seconds ",inmnpm.getId(), ChronoUnit.MILLIS.between(start,end));
    }
    public Map<Long, INMOMSPRDDto> getAll() {
        return redisTemplate.opsForHash().entries(hashReference);
    }
    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete(hashReference, id);
    }
}
