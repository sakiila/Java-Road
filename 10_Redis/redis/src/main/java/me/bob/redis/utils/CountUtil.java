package me.bob.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class CountUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    private static CountUtil countUtil;

    @PostConstruct
    public void init() {
        countUtil = this;
    }

    public static boolean decrease(String key) {
        Long count = countUtil.redisTemplate.opsForValue().decrement(key, 1);
        if (0 == count) {
            return false;
        }
        return true;
    }

    public static boolean increase(String key) {
        Long count = countUtil.redisTemplate.opsForValue().increment(key, 1);
        if (1 == count) {
            countUtil.redisTemplate.expire(key, 30, TimeUnit.SECONDS);
        }
        return true;
    }
}
