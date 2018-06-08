<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="layer/layer.js"></script>
<script>
	function getout(url) {
		layer.confirm('您的账户已过期，请重新登录 ！', {
		btn : [ '确定', '取消' ] ,
		title : '警告' ,
		icon : 0
		}, function() {
		window.open(url, "_top");
		});
	}
</script>
</head>

<body onload="getout('<%=basePath%>');">

</body>
</html>