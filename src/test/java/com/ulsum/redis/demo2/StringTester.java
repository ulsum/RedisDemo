package com.ulsum.redis.demo2;

import redis.clients.jedis.Jedis;

import java.util.List;

public class StringTester {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.auth("qwerty");
            jedis.select(0);
            System.out.println("Redis连接成功");

            // 字符串
            jedis.set("sn", "7781-9938");
            System.out.println(jedis.get("sn"));

            jedis.mset("title1", "婴幼儿奶粉", "num1", "20");
            List<String> list1 = jedis.mget("sn", "title1", "num1");
            System.out.println(list1);

            jedis.mset(new String[]{"title2", "婴幼儿奶粉", "num2", "20"});
            List<String> list2 = jedis.mget("sn", "title2", "num2");
            System.out.println(list2);

            Long num = jedis.incr("num");
            System.out.println(num);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

}
