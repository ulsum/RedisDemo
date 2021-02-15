package com.ulsum.redis.demo1;

import redis.clients.jedis.Jedis;

public class JedisTester {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.auth("qwerty");
            System.out.println("Redis连接成功");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

}
