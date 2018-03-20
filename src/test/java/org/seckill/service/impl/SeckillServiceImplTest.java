package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class SeckillServiceImplTest {

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> sl = seckillService.getSeckillList();
    }

    @Test
    public void getById() throws Exception {
        System.out.println(seckillService.getById(1000));
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        System.out.println(seckillService.exportSeckillUrl(1000));
    }

    @Test
    public void executeSeckill() throws Exception {
        System.out.println(seckillService.executeSeckill(1000, 132029L, "a6b324ce6bcc3d1fc7be489e2b747ecc"));
    }
    @Test
    public void executeSeckillProcedure(){
        long id = 1000;
        long phone = 13257091266L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        SeckillExecution execution = null;
        if(exposer.isExposed()){
            String md5p = exposer.getMd5();
            execution = seckillService.executeSeckillProcedure(id, phone, md5p);
            System.out.println(execution.getStateInfo());
        }

    }

}