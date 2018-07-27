package org.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessSeckilled;
import org.springframework.stereotype.Repository;

@Mapper
public interface SuccessSeckilledDao {
    /**
     * 插入购买明细 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 影响的sql行数,即插入的数量
     */
    int insertSuccessKilled(@Param(value = "seckillId") long seckillId, @Param(value = "userPhone") long userPhone);

    /**
     * 根据id, 用户手机号码查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessSeckilled queryByIdWithSeckill(@Param(value = "seckillId") long seckillId, @Param(value = "userPhone") long userPhone);

}
