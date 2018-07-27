-- 秒杀存储过程
DELIMITER $$ -- console ; 转换为 $$
-- 定义存储过程
-- 参数 in 输入参数
-- 参数 out 输出参数
-- row_count() 返回上一条修改类型sql影响的行数
-- row_count() 0 未修改数据 >0 修改的行数 <0
-- sql错误或者未执行
CREATE PROCEDURE  `seckill`.`execute_seckill`
  (in v_seckill_id int(13), in v_phone BIGINT,
    in v_kill_time TIMESTAMP, out r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT  0;
    START TRANSACTION ;
    INSERT IGNORE INTO success_killed
    (seckillId, userPhone, createTime)
      VALUES (v_seckill_id, v_phone, v_kill_time);
    SELECT  row_count() INTO insert_count;
    IF  (insert_count=0) THEN
      ROLLBACK ;
      SET r_result = -1;
    ELSEIF(insert_count<0) THEN
      ROLLBACK ;
      SET r_result = -2;
    ELSE
      UPDATE  seckill
        set number = number - 1
      WHERE seckillId = v_seckill_id
        AND endTime > v_kill_time
        AND startTime < v_kill_time
        AND number > 0;
      SELECT row_count() into insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK ;
        set r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK ;
        set r_result = -2;
      ELSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;

  END;
$$
-- 存储过程定义结束
DELIMITER ;
SET  @r_result = -3;
-- 执行存储过程
call xecute_seckill(1000, 1325704565, now(), @r_result);
-- 获取结果
SELECT @r_result;