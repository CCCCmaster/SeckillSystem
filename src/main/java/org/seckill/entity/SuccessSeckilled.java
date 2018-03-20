package org.seckill.entity;

import org.springframework.context.annotation.ComponentScan;

import java.sql.DatabaseMetaData;
import java.util.Date;

/**
 * 成功秒杀实体类
 */
@ComponentScan
public class SuccessSeckilled {
    private long seckill_id;
    private long user_phone;
    private short state;
    private Date create_time;
    private Seckill seckill;

    public long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill sckill) {
        this.seckill = sckill;
    }

    @Override
    public String toString() {
        return "SuccessSeckilled{" +
                "seckill_id=" + seckill_id +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", create_time=" + create_time +
                ", sckill=" + seckill +
                '}';
    }
}
