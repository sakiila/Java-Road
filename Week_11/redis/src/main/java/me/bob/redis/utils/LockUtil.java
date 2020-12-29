package me.bob.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class LockUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static LockUtil lockUtil;

    @PostConstruct
    public void init() {
        lockUtil = this;
    }

    public static boolean lock(String key, String value, Long timeout, TimeUnit timeUnit, Runnable runnable) {
        Boolean exist = lockUtil.redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
        if (Boolean.FALSE.equals(exist)) {
            return false;
        }
        runnable.run();
        return true;
    }

    public static boolean lock(Runnable runnable) {
        String string = runnable.toString();
        return lock(string, string, 5L, TimeUnit.SECONDS, runnable);
    }
}
