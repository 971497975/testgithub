<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>请假详情</title>
</head>
<body>
<div class="page-container">
 <form class="form form-horizontal" enctype="multipart/form-data"  method="post" id="add">

	<div class="row cl">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>销假情况：</label>
		<div class="formControls col-xs-7 ">
			 <c:if test="${leave.xjzt=='01'}"><input class="input-text radius"  type="text" name="xjzt" value="正常" readonly='readonly'></c:if>
			 <c:if test="${leave.xjzt=='02'}"><input class="input-text radius"  type="text" name="xjzt" value="延期" readonly='readonly'></c:if>
			 <c:if test="${leave.xjzt=='03'}"><input class="input-text radius"  type="text" name="xjzt" value="提前" readonly='readonly'></c:if>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>销假时间：</label>
		<div class="formControls col-xs-7 ">
            <p><fmt:formatDate value="${leave.xjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>提前/延期原因：</label>
		<div class="formControls col-xs-7 ">
			<textarea  class="textarea radius" id="xjly"  name="xjly">${leave.xjly}</textarea>
		</div>
	</div>
	
  </form>
</div>

</body>
</html>