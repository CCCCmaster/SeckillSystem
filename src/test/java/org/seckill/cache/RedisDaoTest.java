package org.seckill.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class RedisDaoTest {
    private long id = 1000;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void getSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if(seckill == null){
            seckill = seckillDao.queryById(id);
            if(seckill != null){
                String res = redisDao.putSeckill(seckill);
                System.out.println(res);
                redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }

    @Test
    public void putSeckill() throws Exception {
    }

}