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
<title>部门待销假列表</title>
<script>
$(function(){
	$("#sfxj").val('${sfxj}');
});
//多条件查询
function query(){
	document.getElementById('queryForm').submit();
}
</script>
</head>
<body style="width:100%;">
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
	  <form id="queryForm" action="<%=basePath%>/leave/bmxjList.do" method="post" class="form-inline">
		<div class="text-c"> 
	           姓名：<input type="text"  name="ZSXM" class="input-text radius" style="width:150px;" value="${ZSXM}"/>&nbsp;
		        销假状态：<select name="sfxj" id="sfxj" class="input-text radius" style="width:150px;">
		           <option></option>
		           <option value="01">已销假</option>
		           <option value="00">未销假</option>
		        </select>
			<button type="button" class="btn btn-primary radius" onclick="query()">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		<input type="hidden" name="first" value="1">
	  </form>
	</div>
	<c:choose>
	<c:when test="${! empty pageResult.list}">
	   <table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<tr>
				<th scope="col" colspan="11"><center><font size='2'>部门待销假请假单列表</font></center></th>
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
				<th>审核状态</th>
				<th>是否销假</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageResult.list}" var="q" varStatus="status">
	    <tr>
	      <td><center>${pageResult.page.startIndex+status.count}</center></td>
	      <td><center>
	          <a onclick="tz('用户信息','<%=basePath%>/user/userInfo.do?id=${qjuserlist[status.count-1].id}','600','450')" style="color:blue;">${qjuserlist[status.count-1].ZSXM}</a>
	         </center></td>
	      <td><center>${qjuserlist[status.count-1].bmmc}</center></td>
	      <td><center><fmt:formatDate value="${q.startDate}" pattern="yyyy-MM-dd"/></center></td>
	      <td><center><fmt:formatDate value="${q.endDate}" pattern="yyyy-MM-dd"/></center></td>
	      <td><center>${q.days}</center></td>
	      <td><center>${q.leaveType}</center></td>
	      <td><center><span class="titles" title="${q.cause}">${q.cause}</span></center></td>
	      <td>
             <center> 
		       <c:if test="${q.spState=='00'}"><a onclick="tz('审核日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">待审核</a></c:if>
		       <c:if test="${q.spState=='01'}"><a onclick="tz('审核日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">审核中</a></c:if>
		       <c:if test="${q.spState=='02'}"><a onclick="tz('审核日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:blue;">审核通过</a></c:if>
		       <c:if test="${q.spState=='03'}"><a onclick="tz('审核日志','<%=basePath%>/shenpi/sprzUI.do?id=${q.id}','650','350')" style="color:red;">审核不通过</a></c:if>
		     </center>
		  </td>
		  <td><center>
		    <c:if test="${q.sfxj=='01'}"><a onclick="tz('销假详情','<%=basePath%>/leave/xjdetail.do?id=${q.id}','650','350')" style="color:blue;">已销假</a></c:if>
		    <c:if test="${q.sfxj=='00'}"><font color='red'>未销假</font></c:if>
		    </center>
		  </td>
		  <td>
		    <center>
              <a onclick="tz('请假详情','<%=basePath%>/leave/details.do?id=${q.id}','800','450')" style="color:blue;">详情</a> 
              &nbsp;
            </center>
          </td>
	    </tr>
	    </c:forEach>   
  <!-- 显示页码开始 -->    
           <!-- 引入计算页码方法-->	   
           <%@ include file="/WEB-INF/jsp/yema.jsp" %>
           <!-- 显示页码部分 -->
           <tr>
            <td colspan="13">
              <div style="float:left;margin-top:6px;">
                <font size='2'>第<font size='2' color='red'>${pageResult.page.currentPage}</font>页
		        /共<font size='2' color='red'>${pageResult.page.totalPage}</font>页,
		               每页显示<font size='2' color='red'>${pageResult.page.pageRecord}</font>条,
		               共<font size='2' color='red'>${pageResult.page.totalRecord }</font>条</font>
	          </div>
	          <div style="float:right;margin-right:0px;">
              <c:choose>
                <c:when test="${pageResult.page.hasPrePage}">
                  <a href="<%=basePath%>leave/bmxjList.do?currentPage=1"><font size='2'>首页</font></a> | 
                  <a href="<%=basePath%>leave/bmxjList.do?currentPage=${pageResult.page.currentPage-1}"><font size='2'>上一页</font></a>
                </c:when>
                <c:otherwise>
                  <font size='2'>首页 | 上一页</font>
                </c:otherwise>
              </c:choose>
              <%
				for(int i=begin;i<=end;i++){
				     if(i==in_page){
			     %>
				        <font color='red' size='2'> [<%=i%>]</font><!--当前页就不需要超链接，字体变红  -->
			     <% 
				     }
				     else{
			     %>
			           <a href='<%=basePath%>leave/bmxjList.do?currentPage=<%=i%>'><font size='2'>[<%=i%>]</font></a>   
			     <%	  
				     }
				}
			   %> 
               <c:choose>
                <c:when test="${pageResult.page.hasNextPage}">
                  <a href="<%=basePath%>leave/bmxjList.do?currentPage=${pageResult.page.currentPage+1}"><font size='2'>下一页</font></a> | 
                  <a href="<%=basePath%>leave/bmxjList.do?currentPage=${pageResult.page.totalPage}"><font size='2'>尾页</font></a>
                </c:when>
                <c:otherwise>
                  <font size='2'>下一页 | 尾页</font>
                </c:otherwise>
               </c:choose>
              &nbsp;<font size='2'>指定页码</font>
			  <select style="height:20px;width:50px;font-size:12px;" name="currentPage" id="currentPage">
			    <%for(int i=1;i<=zong_page;i++){
			     %>
			        <option value="<%=i%>"><%=i%></option>
			     <%
			      }
			     %>
			  </select>
		    <button class="btn btn-primary btn-sm radius" style="height:28px;" onclick="tiaozhuan()">跳转</button>
           </div>
        </td>
       </tr>  
  <!-- 显示页码结束 -->     
	 </tbody>
	</table>
	</c:when> 
  <c:otherwise> 
      <center>
        <font color="red" size="4">没有记录!</font>
      </center>
	</c:otherwise>
	  </c:choose>
</div>

<script type="text/javascript">
//通用跳转页面
function tz(title,url,w,h){
	layer_show(title,url,w,h);
}
//页码跳转
function tiaozhuan(){
	  var currentPage=$("#currentPage").val();
	  window.location.href='<%=basePath%>leave/bmxjList?currentPage='+currentPage+'';
}
</script>
<script type="text/javascript" src="../js/ztreexs.js"></script>
</body>
</html>