package org.seckill.entity;


import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单的实体类
 * 名字要与xml中对应 无需开启驼峰命名
 * 实现Serializable接口 也可通过其他工具实现序列化 例如Google的protostuff
 */
@Component
public class Seckill implements Serializable{

    private static final long serialVersionUID = 697742643848374753L;
    private long seckillId;
    private String goodsName;
    private int num;
    private Date startTime;
    private Date endTime;
    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", goodsName='" + goodsName + '\'' +
                ", num=" + num +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
