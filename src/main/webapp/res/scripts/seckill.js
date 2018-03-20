//存放主要交互逻辑js代码

var seckill = {
    //封装秒杀相关ajax的url
    URL:{
        now : function(){
            return "/seckill/operate/time/now";

        },
        exposer : function (seckillId) {
            return "/seckill/operate/"+seckillId+'/exposer';
        },
        execution: function (seckillId, md5) {
          return   seckillId+'/'+md5+'/execution';
        },
    },
    //处理秒杀逻辑
    handleSeckillkill : function (seckillId) {
        window.console.info("in handleSeckillkill start " + seckillId);
        //node.hide();
        // node.html('<botton class="btn btn-primary btn-lg" id="killBtn">开始秒杀</botton>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //再回掉函数中执行交互流程
            if(result && result['success']){
                var exposer = result['data'];
                //alert("get data" + exposer);

                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5']
                    var killUrl = "/seckill/operate/"+seckillId+"/"+md5+"/execution";
                    window.console.info("killUrl: "+ killUrl);
                    //绑定一次点击事件
                    // $('#killBtn').one('click', function () {
                        //绑定执行秒杀请求的操作
                        //禁用按钮
                        // $(this).addClass('disable');
                        //发送请求
                        $.post(killUrl, {}, function (result) {
                           if(result && result['success']){
                               var killResult = result['data'];
                               var state = killResult['state'];
                               //显示秒杀结果
                               //node.html('<span class="label label-success">+stateInfo+</span>');
                           }
                            var stateInfo = result['data']['stateInfo'];
                            alert(stateInfo);
                        });
                }else {
                    alert("秒杀还未开始哦 0 0");
                }
            }
        });
    },
    //验证手机号
    validatePhone:function (phone) {
        if(phone && phone.length==11 && !isNaN(phone) ){
            window.console.info("手机号正确");
            return true;
        }
        window.console.info("手机号错误");
        return false;
    },
    countdown:function (seckillId, nowTime, startTime, endTime ) {
        var seckillBox = $('#seckill-box');
        window.console.info('seckillId in countdown'+seckillId);
        window.console.info(seckillBox);
        window.console.info(startTime);
        window.console.info(endTime);
        if(nowTime > endTime){
            // 秒杀结束
            // alert("秒杀结束");
            seckillBox.html('秒杀结束');
        }else if (nowTime < startTime) {
            // 秒杀未开始 计时事件绑定
            var killTime = new Date(startTime + 1000);
            // alert("秒杀未开始 计时事件绑定");
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //获取秒杀地址 控制实现逻辑 执行秒杀
                window.console.info('seckillId in finish.countdown'+seckillId);
                seckill.handleSeckillkill(seckillId);//, $('#killBtn'));
            });

        }else {
            //　秒杀开始
            var seckill = $('#killBtn')
            if(seckill.click()) {
                seckill.handleSeckillkill(seckillId);
            }
        }


    },
    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init : function (params) {
            //用户手机验证和登陆 计时交互
            //规划交互流程
            //在cookie中查找手机号码
            var killPhone = $.cookie("killPhone"); // 从cookie中获取cookie
            //alert(killPhone);
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //验证手机号
            if(!seckill.validatePhone(killPhone)){ //
                var killPhoneModal = $　('#killPhoneModal');
                killPhoneModal.modal({
                    show: true, // 显示弹出层
                    backdrop:'static', // 禁止位置关闭
                    keyboard:false //　关闭键盘时间
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhone').val(); // 获取输入的手机号码
                    //alert(inputPhone);
                    if(seckill.validatePhone(inputPhone)){ // 判断号码是否合法
                        $.cookie('killPhone', inputPhone,{expire:7,path:'/seckill'}); // 设置cookie
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
        }
        //已经登陆
        //计时交互
        $.get(seckill.URL.now(), {}, function (result) {
            // alert("计时交互");
            if(result && result['success']){
                var nowTime = result['data'];
                //时间判断
                //("seckill.countDown");
                seckill.countdown(seckillId, nowTime, startTime, endTime );
            }else{
                console.log("result in seckill.URL.now()"+result);
            }
        })
    }
    }
}