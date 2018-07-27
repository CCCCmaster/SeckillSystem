package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessSeckilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessSeckilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessSeckilledDao successSeckilledDao;
    //Salt
    private final String salt = "as5sa4654434654t565&*%&^%46saghj";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,3);
    }

    /**
     * 使用@Cacheable定义缓存策略
     * 当缓存中有值 则返回缓存数据 否则访问方法得到数据
     * 通过value引用缓存管理器 通过访问key定义键
     * @param seckillId
     * @return Seckill
     */
    @Override
    @Cacheable(value = "cacheManager", key = "'seckill'+ #seckillId")
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /**
     * 暴露秒杀接口
     * @param seckillId
     * @return
     */
    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = this.seckillDao.queryById(seckillId);
        // 无该秒杀活动
        if(seckill == null){
            System.out.println("seckill=null eturn new Exposer(false, seckillId);");
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStart_time();
        Date endTime = seckill.getEnd_time();
        Date nowTime = new Date();
        // 未到时间或者超过结束时间
        if(nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()){
            System.out.println("未到时间或者超过结束时间");
            return new Exposer(false, seckillId, nowTime.getTime(),
                    startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        System.out.println("return new Exposer(true, md5, seckillId);");
        return new Exposer(true, md5, seckillId);
    }


    /**
     * 注解开发控制事务方法的优点
     * 1 开发事务达成一致约定 明确标注事务方法的编程风格
     * 2 保证事务方法的执行时间尽可能短 不要穿插其他网络操作剥离到外层
     * 3 不是所有方法都需要事务 比如只有一条修改操作 或者只读操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillCloseException
     * @throws RepeatKillException
     * @throws SeckillException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑: 减少库存 + 记录购买行为
        Date nowTime = new Date();
        try {
            //记录购买行为
            int insertConunt = this.successSeckilledDao.insertSuccessKilled(seckillId, userPhone);
            if (insertConunt <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                // 减库存 热点商品竞争
                int updateCount = this.seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录,秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒杀成功
                    SuccessSeckilled successSeckilled = this.successSeckilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successSeckilled);
                }
            }
        }catch (SeckillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e) {
            e.printStackTrace();
            //所有编译器异常 转换为运行期异常
            throw new SeckillException("seckill inner error" + e.getMessage());

        }
    }

    /**
     * 生成 MD5值
     * @param seckillId 秒杀id
     * @return String md5
     */
    private String getMD5(long seckillId){
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
