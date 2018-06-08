<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<link rel="stylesheet" type="text/css" href="css/login.css"/>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="layer/layer.js"></script>
<script>
//按回车键触发某个事件
document.onkeydown=keyListener;
function keyListener(e){
    e = e ? e : event;
    if(e.keyCode == 13){
        deng();
    }
}	
//登录按钮方法
function deng(){
    var policeNumOrID=$("#policeNumOrID").val();
    var pwd=$("#pwd").val();
    if(policeNumOrID==""){
       layer.alert('用户名不能为空!', {
				icon : 0,
				title : '警告',
				offset: 'm'
			});
			return false;
    }
    else if(pwd==""){
          layer.alert('密码不能为空!', {
				icon : 0,
				title : '警告',
				offset: 'm'
			});
			return false;
    }
    else{
    	$.ajax({
    		url: '<%=basePath%>/user/login.do',
            type: "post",
            data:{"policeNumOrID":policeNumOrID,"pwd":pwd},
            dataType:"json", 
            success:function(data){
                  if(data.status==200){
                      document.getElementById('deng').submit();
                  }else{
                       layer.alert(data.msg, {
    				   icon : 2,
    				   title : '错误',
    				   offset: 'm',//弹出位置
    			        });
    			       return false;
                  }
              }
           });
    }
 }
</script>
<title>请销假系统</title>
</head>
<body>
	<div class="header">
	    <img src="images/logo3.png" />
	</div>
	<div class="ConText">
	<div class="ConText_Left"><img src="images/img1.jpg" /></div>
	<div class="ConText_Right">
	<h1>测试github666111</h1>
		<div class="ConText_RInfo">
			<form class="form form-horizontal" action="<%=basePath%>/main.do" method="post" id="deng">
				<input type="text" id="policeNumOrID" name="policeNumOrID" class="input_name" placeholder="警号/身份证号"/>
				<input type="password" class="input_pwd" id="pwd" name="pwd" placeholder="密码"/>
				<input type="button" class="input_btn" value="立即登录" onclick="deng()" />
			</form>
		</div>
	</div>
	<div style="clear:both;"></div>
	</div>
	<div class="bg_box"></div>
</body>
</html>