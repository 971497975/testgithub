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
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>审批日志</title>
</head>
<body >
  <div class="page-container">
	   <table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<tr>
				<th scope="col" colspan="13"><center>审批日志</center></th>
			</tr>
			<tr class="text-c">
				<th>序号</th>
				<th>审核人</th>
                <th>审核意见</th>
                <th>审核结果</th>
                <th>审核时间</th>
			</tr>
			<c:if test="${dysprid!=''}">
			   <tr>
			     <td><center>1</center></td>
			     <td><center>${dysprid}</center></td>
			     <td><center>${dyspryj}</center></td>
			      <td><center>
			         <c:if test="${dysprjg=='01'}">同意</c:if>
			         <c:if test="${dysprjg=='02'}">不同意</c:if>
			     </center></td>
			     <td><center><fmt:formatDate value="${dysprsj}" pattern="yyyy-MM-dd"/></center></td>
			   </tr>
			</c:if>
			<c:if test="${desprid!=''}">
			   <tr>
			     <td><center>2</center></td>
			     <td><center>${desprid}</center></td>
			     <td><center>${despryj}</center></td>
			     <td><center>
			         <c:if test="${desprjg=='01'}">同意</c:if>
			         <c:if test="${desprjg=='02'}">不同意</c:if>
			     </center></td>
			     <td><center><fmt:formatDate value="${desprsj}" pattern="yyyy-MM-dd"/></center></td>
			   </tr>
			</c:if>
			<c:if test="${dssprid!=''}">
			   <tr>
			     <td><center>3</center></td>
			     <td><center>${dssprid}</center></td>
			     <td><center>${dsspryj}</center></td>
			      <td><center>
			         <c:if test="${dssprjg=='01'}">同意</c:if>
			         <c:if test="${dssprjg=='02'}">不同意</c:if>
			     </center></td>
			     <td><center><fmt:formatDate value="${dssprsj}" pattern="yyyy-MM-dd"/></center></td>
			   </tr>
			</c:if>
			
			<c:if test="${dsisprid!=''}">
			   <tr>
			     <td><center>4</center></td>
			     <td><center>${dsisprid}</center></td>
			     <td><center>${dsispryj}</center></td>
			      <td><center>
			         <c:if test="${dsisprjg=='01'}">同意</c:if>
			         <c:if test="${dsisprjg=='02'}">不同意</c:if>
			     </center></td>
			     <td><center><fmt:formatDate value="${dsisprsj}" pattern="yyyy-MM-dd"/></center></td>
			   </tr>
			</c:if>
		
		</thead>
		<tbody>
			
	 </tbody>
	</table>
</div>
</body>
</html>