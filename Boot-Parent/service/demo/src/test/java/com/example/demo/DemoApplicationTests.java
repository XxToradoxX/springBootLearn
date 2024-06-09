package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

class DemoApplicationTests {

    @Test
    void contextLoads() {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.printf(jedis.ping());
    }

}
