package com.ulsum.redis.demo3;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class HashTester {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.auth("qwerty");
            jedis.select(0);
            System.out.println("Redis连接成功");

            // Hash
            jedis.hset("student:3312", "name", "Lusy");
            String name = jedis.hget("student:3312","name");
            System.out.println(name);
            Map<String, String> studentMap = new HashMap();
            studentMap.put("name", "李兰");
            studentMap.put("age", "18");
            jedis.hmset("student:3313", studentMap);
            Map<String, String> smap = jedis.hgetAll("student:3313");
            System.out.println(smap);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

}
