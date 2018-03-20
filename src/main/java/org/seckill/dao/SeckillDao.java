package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface SeckillDao {
    /**
     * 减少库存
     * @param seckillId
     * @param killTime
     * @return 影响的sql行数
     * @Param(valuue="xml中参数名称")
     */
    int reduceNumber(@Param(value = "seckillId") long seckillId,@Param(value = "killTime") Date killTime);

    /**
     * 查询
     * @param seckillId
     * @return Seckill
     */
    Seckill queryById(long seckillId);

    /**
     * 偏移量查询
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param(value ="offset" ) int offset, @Param(value = "limit") int limit);

    /**
     * 调用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
