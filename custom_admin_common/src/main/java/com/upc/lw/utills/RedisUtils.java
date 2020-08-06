package com.upc.lw.utills;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description redis工具类
 * @author: liwei
 * @date: 2020/8/5 14:54
 */
@Slf4j
@Component
@SuppressWarnings({"unchecked", "all"})
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations<String, Object> getValueOperations() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations;
    }

    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                getValueOperations().set(key, value, time, timeUnit);
            } else {
                getValueOperations().set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("set redis value key:{} error:", key, e);
        }
        return false;
    }

    public String get(String key) {
        try {
            String val = String.valueOf(getValueOperations().get(key));
            return val;
        } catch (Exception e) {
            log.error("get redis value key:{} error:", key, e);
        }
        return null;
    }

    public Boolean del(String key) {
        try {
            Boolean delete = redisTemplate.delete(key);
            return delete;
        } catch (Exception e) {
            log.error("del redis value key:{} error:", key, e);
        }
        return Boolean.FALSE;
    }
}
