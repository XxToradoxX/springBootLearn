package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

class DemoApplicationTests {

    @Test
    void contextLoads() {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.printf(jedis.ping());
    }

}
