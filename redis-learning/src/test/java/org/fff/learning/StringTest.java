package org.fff.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@SpringBootTest
public class StringTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /* test for String Type*/
    @Test
    public void testOpsForValueSet() {
        String key = "name";
        String value = "value";
        redisTemplate.opsForValue().set(key, value);
        System.out.println("******** Set Done!");
    }
    @Test
    public void testOpsForValueGet() {
        Object value = redisTemplate.opsForValue().get("name");
        System.out.println("********* Get Done! " + value);
    }

    /*Test Key options*/
    @Test
    public void testHasKey() {
        Boolean hasKey = redisTemplate.hasKey("name");
        System.out.println("********* has key: " + hasKey);
    }
    @Test
    public void testSetKeyExpireTime() {
        redisTemplate.expire("name", 60, TimeUnit.SECONDS);
        System.out.println("********** set key expire done");
    }
    @Test
    public void testGetKeyExpire() {
        //        获取key的剩余有效时间
        Long expire = redisTemplate.getExpire("name");
        System.out.println("********* get key expire time: " + expire);
    }

    /*Test for Hash Type*/
    @Test
    public void testOpsForHashPut() {
//        hash type 存放一个
        redisTemplate.opsForHash().put("key", "hash-key-1", "Tome");
        System.out.println("******* put hash done");
    }
    @Test
    public void testOpsForHashPutAll() {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("hash-key-2", "Leo");
        mapResult.put("hash-key-3", "Eugene");
//        hash type 存放多个值
        redisTemplate.opsForHash().putAll("key", mapResult);
        System.out.println("******* put all done");
    }
    @Test
    public void testOpsForHashEntries() {
//        取出指定hash的所有key-value
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("key");
        System.out.println("******* entries: " + entries);
    }
    @Test
    public void testOpsForHashGet() {
//        获取指定hash内，指定key的value
        Object result = redisTemplate.opsForHash().get("key", "hash-key-1");
        System.out.println("****** value: " + result);
    }
    @Test
    public void testOpsForHashDelete() {
//        删除指定Hash内，指定key的内容
        redisTemplate.opsForHash().delete("key", "hash-key-1");
        System.out.println("********** delete done!");
    }
    @Test
    public void testOpsForHashHasKey() {
//         指定Hash内，是否存在指定hash-key
        Boolean result = redisTemplate.opsForHash().hasKey("key", "hash-key-1");
        System.out.println("********* has key: " + result);
    }

    /*Test for List*/
    @Test
    public void testOpsForListRightPush() {
        redisTemplate.opsForList().rightPush("city", "ShenZen");
        System.out.println("*************** done!");
    }
    @Test
    public void testOpsForListLeftPush() {
        redisTemplate.opsForList().leftPush("city", "USA");
        System.out.println("*************** done!");
    }
    @Test
    public void testOpsForListRightPop() {
//        Pop() 方法，默认会移除一个value，若移除后该key的list为空，则该key也消失了
        Object cities = redisTemplate.opsForList().rightPop("city", 2);
        System.out.println("********* cities " + cities);
    }
    @Test
    public void testOpsForListLeftPop() {
        Object cities = redisTemplate.opsForList().leftPop("city");
        System.out.println("********* cities " + cities);
    }
    @Test
    public void testOpsForListRightPushAll() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("KFC");
        list.add("M");
        list.add("Good");
        redisTemplate.opsForList().rightPushAll("city", list);
        System.out.println("*************** done!");
    }
    @Test
    public void testOpsForListSet() {
//        在指定List中，修改指定下标的value，不可用于插入
        redisTemplate.opsForList().set("city", 0l, "XiAn");
        System.out.println("*************** done!");
    }
    @Test
    public void testOpsForListIndex() {
//        在指定列表中，获取指定索引的值
        Object city = redisTemplate.opsForList().index("city", 0l);
        System.out.println("********* city " + city);
    }

    /*Test type for Set*/
    @Test
    public void testOpsForSetAdd() {
        redisTemplate.opsForSet().add("member", "student", "teacher");
        System.out.println("*************** done!");
    }
    @Test
    public void testOpsForSetMembers() {
        Set<Object> members = redisTemplate.opsForSet().members("member");
        System.out.println("*************** members: " + members);
    }
    @Test
    public void testOpsForSetIsMember() {
        Boolean isMember = redisTemplate.opsForSet().isMember("member", "student");
        System.out.println("********* isMember " + isMember);
    }
}
