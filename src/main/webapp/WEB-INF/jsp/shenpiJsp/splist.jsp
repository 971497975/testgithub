<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>待我审批请假单列表</title>
</head>
<body style="width:100%;">
  <div class="page-container">
	<c:choose>
	<c:when test="${! empty list}">
	   <table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<tr>
				<th scope="col" colspan="11"><center><font size='2'>待我审批请假单列表</font></center></th>
			</tr>
			<tr class="text-c">
			    <th width="5%">序号</th>
			    <th>请假人</th>
			    <th>所在部门</th>
				<th>请假开始时间</th>
				<th>请假结束时间</th>
				<th>请假天数</th>
				<th>请假类别</th>
				<th>请假原因</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="q" varStatus="status">
	    <tr>
	      <td><center>${pageResult.page.startIndex+status.count}</center></td>
	      <td><center>
	          <a onclick="userInfo('用户信息','<%=basePath%>/user/userInfo.do?id=${qjuserlist[status.count-1].id}','600','450')" style="color:blue;">${qjuserlist[status.count-1].ZSXM}</a>
	         </center></td>
	      <td><center>${qjuserlist[status.count-1].bmmc}</center></td>
	      <td><center><fmt:formatDate value="${q.startDate}" pattern="yyyy-MM-dd"/></center></td>
	      <td><center><fmt:formatDate value="${q.endDate}" pattern="yyyy-MM-dd"/></center></td>
	      <td><center>${q.days}</center></td>
	      <td><center>${q.leaveType}</center></td>
	      <td><center><span class="titles" title="${q.cause}">${q.cause}</span></center></td>
		  <td>
             <center> 
		       <c:if test="${q.spState=='00'}"><a onclick="sprz('审批日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">待审批</a></c:if>
		       <c:if test="${q.spState=='01'}"><a onclick="sprz('审批日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">审批中</a></c:if>
		       <c:if test="${q.spState=='02'}"><a onclick="sprz('审批日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">审批通过</a></c:if>
		       <c:if test="${q.spState=='03'}"><a onclick="sprz('审批日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:red;">审批不通过</a></c:if>
		     </center>
		  </td>
		  <td>
		    <center>
              <a onclick="details('请假详情','<%=basePath%>/leave/details.do?id=${q.id}','800','450')" style="color:blue;">详情</a> 
              &nbsp;
              <a onclick="sp('审批','<%=basePath%>/shenpi/spUI.do?id=${q.id}','600','350')" style="color:blue;">审批</a>
            </center>
          </td>
	    </tr>
	    </c:forEach>   
	 </tbody>
	</table>
	</c:when> 
    <c:otherwise> 
      <center><br>
         <font color="red" size="4">暂无待审批信息!</font>
      </center>
	</c:otherwise>
	</c:choose>
</div>
<script>
//审批页面
function sp(title,url,w,h){
	layer_show(title,url,w,h);
}
//用户信息
function userInfo(title,url,w,h){
	layer_show(title,url,w,h);
}
//详情
function details(title,url,w,h){
	layer_show(title,url,w,h);
}
//审批日志
function sprz(title,url,w,h){
	layer_show(title,url,w,h);
}
</script>
<br/><br/>
</body>
</html>