
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入jstl-->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页面</title>
    <%@ include file="common/head.jsp" %>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="pannel-heading">
                ${seckill.goods_name}
                <div id="seckillId"> ${seckill.seckill_id}</div>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <!--显示time图标-->
                    <span class="glyphicon glyphicon-time"></span>
                    <!--展示倒计时-->
                    <span class="glyphion" id="seckill-box"></span>
                </h2>
            </div>
            <botton class="btn btn-primary btn-lg" id="killBtn" onclick="seckill.handleSeckillkill(${seckill.seckill_id})">开始秒杀</botton>
        </div>
    </div>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="midal-header">
                    <h3 class="modal-title text-center" >
                        <span class="glyphicon glyphicon-phone">
                            手机号码
                        </span>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="offset5 span7">
                            <input type="text" name="killPhone" id="killPhone"
                                placeholder="手机号码" class="form-select-button"
                            >
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killPhoneMessage" class="glyphicon">
                    </span>
                    <botton type="button" id="killPhoneBtn" class="btn btn-success">
                        Submit
                    </botton>
                </div>
            </div>
        </div>
    </div>
</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../res/scripts/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function ( ) {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId: ${seckill.seckill_id},
            startTime: ${seckill.start_time.time},
            endTime: ${seckill.end_time.time}
        });
    });
</script>
</html>