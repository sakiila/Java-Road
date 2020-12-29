package me.bob.redis;

import me.bob.redis.utils.CountUtil;
import me.bob.redis.utils.LockUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisConnection() {
        stringRedisTemplate.opsForValue().set("bb", "123");
        String bb = stringRedisTemplate.opsForValue().get("bb");
        System.out.println("bb = " + bb);
    }

    @Test
    void testLockUtil() {
        Runnable task = () -> {
            System.out.println("running: " + this.getClass().toString());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable = () -> LockUtil.lock("task1", "value", 1L, TimeUnit.SECONDS, task);

        for (int i = 0; i < 10; i++) {
            runnable.run();
        }
    }

    @Test
    void testCountUtil() {
        CountUtil.increase("inc");
        CountUtil.increase("inc");
        CountUtil.decrease("inc");
    }

}
