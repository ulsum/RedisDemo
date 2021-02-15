package com.ulsum.redis.demo5;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CacheSample {

    public CacheSample() {
        Jedis jedis = new Jedis("localhost", 6379);
        List<Goods> goodsList = new ArrayList<Goods>();
        goodsList.add(new Goods(8818, "苹果", "", 3.5f));
        goodsList.add(new Goods(8819, "香蕉", "", 8.6f));
        goodsList.add(new Goods(8820, "西瓜", "", 13.8f));
        try {
            jedis.auth("qwerty");
            jedis.select(0);
            for (Goods goods : goodsList) {
                String json = JSON.toJSONString(goods);
                System.out.println(json);
                String key = "goods:" + goods.getGoodsId();
                jedis.set(key, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        new CacheSample();
        System.out.print("请输如要查询的商品编号：");
        String goodsId = new Scanner(System.in).nextLine();
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.auth("qwerty");
            jedis.select(0);
            String key = "goods:" + goodsId;
            if (jedis.exists(key)) {
                String json = jedis.get(key);
                System.out.println(json);
                Goods goods = JSON.parseObject(json, Goods.class);
                System.out.println(goods.getGoodsName());
            } else {
                System.out.println("输入的商品编号不存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

}
