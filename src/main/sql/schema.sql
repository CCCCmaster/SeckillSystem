-- 数据库初始化

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;
-- 建立秒杀库存表
CREATE  TABLE seckill(
  seckillId INT NOT NULL  AUTO_INCREMENT COMMENT '商品库存id',
  goodsName VARCHAR(120) NOT NULL COMMENT '商品名称',
  number int NOT NULL COMMENT '库存数量',
  startTime TIMESTAMP NOT NULL  COMMENT '秒杀开启时间',
  endTime TIMESTAMP NOT NULL  COMMENT '结束时间',
  createTime TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckillId),
  KEY  idx_start_time(startTime),
  KEY idx_end_time(endTime),
  key idx_create_time(createTime)
)ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT ='秒杀库存表';

-- 初始数据
--  ('1000元秒杀IPhone6', 100, '2018-03-12 00:00:00','2018-03-13 00:00:00'),
--  ('800元秒杀IPhone6s', 50, '2018-03-12 00:00:00','2018-03-13 00:00:00'),
--  ('1100元秒杀IPad', 150, '2018-03-12 00:00:00','2018-03-13 00:00:00')


-- 秒杀成功明细表
-- 用户登陆认证相关的信息

CREATE  TABLE success_killed(
  seckillId INT NOT NULL  COMMENT '秒杀商品id',
  userPhone INT NOT NULL  COMMENT '用户手机号',
  state INT NOT NULL  DEFAULT -1 COMMENT '状态标识： -1 无效 0 成功 1 已付款',
  createTime TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckillId, userPhone), /* 联合主键*/
  KEY idx_create_time (createTime)
)ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT ='秒杀成功明细表';
