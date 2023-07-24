package com.ps.fleetpride.service;

import com.ps.fleetpride.dto.INMINVDto;
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
public class INMINVRedisService {
    private final String hashReference= "INMINV_KEYS";
    private final RedisTemplate redisTemplate;
    public void save(INMINVDto inminvDto) {
        log.info("save INMINV for id {} into redis" , inminvDto.getId());
        redisTemplate.opsForHash().putIfAbsent(hashReference, inminvDto.getId(), inminvDto);
        log.info("save INMINV successful");
    }
    public void saveAll(Map<Long, INMINVDto> map) {
        log.info("save {} INMINV redis" , map.size());
        redisTemplate.opsForHash().putAll(hashReference, map);
        log.info("save all INMINV successful");
    }
    public INMINVDto getOneById(Long id) {
        log.info("get INMINV from redis for id {} " , id);
        return (INMINVDto) redisTemplate.opsForHash().get(hashReference, id);
    }
    public void update(INMINVDto inminvDto) {
        var start = Instant.now();
        log.info("update INMINV into redis for id {} " , inminvDto.getId());
        redisTemplate.opsForHash().put(hashReference, inminvDto.getId(), inminvDto);
        var end = Instant.now();
        log.info("time taken to update INMINV into redis for id {} is {} milli seconds ",inminvDto.getId(), ChronoUnit.MILLIS.between(start,end));
    }
    public Map<Long, INMINVDto> getAll() {
        return redisTemplate.opsForHash().entries(hashReference);
    }
    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete(hashReference, id);
    }
}
