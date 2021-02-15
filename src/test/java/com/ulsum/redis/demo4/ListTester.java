package com.ulsum.redis.demo4;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTester {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.auth("qwerty");
            jedis.select(0);
            System.out.println("Redis连接成功");

            // List
            jedis.del("letter");
            jedis.rpush("letter", new String[]{"d","e","f"});
            jedis.lpush("letter", new String[]{"a","b","c"});
            List<String> list1 = jedis.lrange("letter", 0, -1);
            System.out.println(list1);
            jedis.lpop("letter");
            jedis.rpop("letter");
            List<String> list2 = jedis.lrange("letter", 0, -1);
            System.out.println(list2);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

}
