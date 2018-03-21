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
    private long seckill_id;
    private String goods_name;
    private int num;
    private Date start_time;
    private Date end_time;
    private Date create_time;

    public long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckill_id=" + seckill_id +
                ", goods_name='" + goods_name + '\'' +
                ", num=" + num +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", create_time=" + create_time +
                '}';
    }
}
