package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessSeckilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 单元测试
 * SuccessSeckilledDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class SuccessSeckilledDaoTest {
    @Autowired
    private SuccessSeckilledDao successSeckilledDao;

    /**
     * 插入秒杀记录
     * @throws Exception
     */
    @Test
    public void insertSuccessKilled() throws Exception {
        System.out.println(
                this.successSeckilledDao.insertSuccessKilled(1001, 13257097667L)
        );
    }

    /**
     * 根据id, 用户手机号码查询SuccessKilled并携带秒杀产品对象实体
     * @throws Exception
     */
    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessSeckilled s = this.successSeckilledDao.queryByIdWithSeckill(1000, 12345678L);
        System.out.println(s);
    }

}