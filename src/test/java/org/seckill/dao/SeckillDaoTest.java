package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 单元测试
 * SeckillDaoTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class SeckillDaoTest {
    /**
     * 注入SeckillDao
     */
    @Resource
    private SeckillDao seckillDao;

    /**
     * 测试减少库存
     * @throws Exception
     */
    @Test
    public void reduceNumber() throws Exception {
        System.out.println(seckillDao == null);
        int i = this.seckillDao.reduceNumber(1000,new Date());
        System.out.println(i);
    }

    /**
     * 测试根据id查询
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        Seckill s = this.seckillDao.queryById(1000);
        System.out.println(s);
    }

    /**
     * 测试批量查询
     * @throws Exception
     */
    @Test
    public void queryAll() throws Exception {
        System.out.println(seckillDao == null);
        List<Seckill> p = seckillDao.queryAll(1,2);
        for(Seckill s:p){
            System.out.println(s);
        }
    }

}