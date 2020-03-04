package com.redis.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

@SpringBootTest
class DemoApplicationTests {
    Jedis jedis=new Jedis("47.103.217.224",6379);

    @Test
    public void testRedis(){
        HashMap<String, String> order1 = new HashMap<String, String>();
        order1.put("orderId","1");
        order1.put("money","36.6");
        order1.put("time","2018-01-01");
        HashMap<String, String> order2 = new HashMap<String, String>();
        order2.put("orderId","2");
        order2.put("money","38.6");
        order2.put("time","2018-01-01");
        HashMap<String, String> order3 = new HashMap<String, String>();
        order3.put("orderId","3");
        order3.put("money","39.6");
        order3.put("time","2018-01-01");
        jedis.hmset("order1",order1);
        jedis.hmset("order2",order2);
        jedis.hmset("order3",order3);

        jedis.lpush("user1","order1","order2","order3");

        HashMap<String, String> order4 = new HashMap<String, String>();
        order4.put("orderId","4");
        order4.put("money","40.6");
        order4.put("time","2018-01-01");
        jedis.hmset("order4",order4);

        jedis.lpush("user1","order4");

        System.out.println(jedis.lrange("user1", 0, -1));
    }

}
