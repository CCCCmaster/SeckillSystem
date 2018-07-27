package org.seckill.controller;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 秒杀活动相关接口
 */
@RestController
@RequestMapping(value = "/goods") //
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 秒杀商品列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Seckill> list(){
        List<Seckill> list = this.seckillService.getSeckillList();
        return list;
    }

    /**
     * 获取商品详细信息
     *
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public Seckill detail(@PathVariable("seckillId") Long seckillId) {
        Seckill seckill = null;
        if (seckillId == null) {
            return seckill;
        }
        seckill = this.seckillService.getById(seckillId);
        return seckill;

    }

    /**
     * 获取秒杀接口地址
     * @param seckillId
     * @return SeckillResult的实例
     */
    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST)
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        System.out.println(seckillId);
        try{
            Exposer exposer = this.seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        }catch (Exception e){
            e.printStackTrace();
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 秒杀接口
     * @param seckillId
     * @param md5
     * @param phone
     * @return SeckillResult的实例
     */
    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
        method = RequestMethod.POST)
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone){
        if(phone == null ){
            return new SeckillResult<SeckillExecution>(false, "未注册" );
        }
        System.out.println("seckillId: "+seckillId);
        SeckillResult<SeckillExecution> result = null ;
        try{
            SeckillExecution execution = this.seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (RepeatKillException e){
            // 重复秒杀
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }catch (SeckillCloseException e){
            // 秒杀结束
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }
        catch (Exception e){
            // 未知异常
            e.printStackTrace();
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }

    }

    /**
     * 时间校准 获取当前服务器时间
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }

}
