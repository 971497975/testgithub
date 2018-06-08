<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的信息</title>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
</head>
  <body>
  <br><br>
    <table class="table table-hover table-bordered" style="margin:auto; width:80%;">
	  <thead>
	    <tr>
	      <td width="40%"><center><b>真实姓名</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.ZSXM}" readonly="readonly"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>警号</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.JH}" readonly="readonly"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>身份证号</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.SFZH}" readonly="readonly"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>性别</b></center></td>
	      <td>
	        <c:if test="${user.XB=='0'}"><input type="text" class="input-text radius" value="男" readonly="readonly"></c:if>
			<c:if test="${user.XB=='1'}"><input type="text" class="input-text radius" value="女" readonly="readonly"></c:if>
	      </td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>婚否</b></center></td>
	      <td>
	        <c:if test="${user.HF=='0'}"><input type="text" class="input-text radius" value="未婚" readonly="readonly"></c:if>
			<c:if test="${user.HF=='1'}"><input type="text" class="input-text radius" value="已婚" readonly="readonly"></c:if>
	      </td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>参加工作时间</b></center></td>
	      <td><fmt:formatDate value="${user.CJGZSJ}" pattern="yyyy-MM-dd"/></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>剩余可休年休假</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.NXJTS}天" readonly="readonly"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>职务</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.ZWMC}" readonly="readonly"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>所属部门</b></center></td>
	      <td><input type="text" class="input-text radius" value="${user.bmmc}" readonly="readonly"></td>
	    </tr>
	   </thead>
	  </table>
	  <br>
  </body>
</html>
