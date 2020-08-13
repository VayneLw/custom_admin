package com.upc.lw.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 20:00
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testSave(){
        DemoEntity demoEntity = DemoEntity.builder()
                .description("test 11")
                .title("demo")
                .url("===========")
                .by("lw")
                .build();
        mongoTemplate.save(demoEntity);
    }

    @Test
    public void testCollection(){
        String collectionName = "demo_collection_1";
        DemoEntity demoEntity = DemoEntity.builder()
                .description("test 12")
                .title("demo")
                .url("===========")
                .by("lw_1")
                .build();
        mongoTemplate.insert(demoEntity, collectionName);
    }
}
