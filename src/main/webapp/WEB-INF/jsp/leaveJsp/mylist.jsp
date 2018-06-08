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
<title>我的请假单列表</title>
</head>
<body style="width:100%;">
  <div class="page-container">
	<c:choose>
	<c:when test="${! empty pageResult.list}">
	   <table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<tr>
				<th scope="col" colspan="9"><center><font size='2'>我的请假单列表</font></center></th>
			</tr>
			<tr class="text-c">
			    <th width="5%">序号</th>
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
		    <c:if test="${q.sfxj=='00'&&q.spState=='02'}"><font color='red'>未销假</font></c:if>
		    </center>
		  </td>
		  <td>
		    <center>
		      <c:if test="${q.sfxj=='00'&&q.spState=='02'}">
		         <a onclick="tz('销假','<%=basePath%>/leave/xjUI.do?id=${q.id}','650','350')" style="color:blue;">销假</a>&nbsp;
		      </c:if>
              <a onclick="tz('请假详情','<%=basePath%>/leave/details.do?id=${q.id}','800','450')" style="color:blue;">详情</a> 
              &nbsp;
              <c:if test="${q.spState=='00'}">
                 <a onclick="del('${q.id}')"><font color='blue'>删除</font></a> 
              </c:if>
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
                  <a href="<%=basePath%>leave/mylist.do?currentPage=1"><font size='2'>首页</font></a> | 
                  <a href="<%=basePath%>leave/mylist.do?currentPage=${pageResult.page.currentPage-1}"><font size='2'>上一页</font></a>
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
			           <a href='<%=basePath%>leave/mylist.do?currentPage=<%=i%>'><font size='2'>[<%=i%>]</font></a>   
			     <%	  
				     }
				}
			   %> 
               <c:choose>
                <c:when test="${pageResult.page.hasNextPage}">
                  <a href="<%=basePath%>leave/mylist.do?currentPage=${pageResult.page.currentPage+1}"><font size='2'>下一页</font></a> | 
                  <a href="<%=basePath%>leave/mylist.do?currentPage=${pageResult.page.totalPage}"><font size='2'>尾页</font></a>
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
//删除请假信息
function del(id) {
	$id=id;
	//询问框
	 layer.confirm('你确定要删除该请假单吗？', {
	 icon : 0,
	 title : '警告',
	 offset: '100px',//弹出位置
	 btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url : "<%=basePath%>/leave/deleteLeave.do",
			data : {"id":id},
			type : "post",
			success : function(data) {
				if ("success" == data) {
					layer.alert('删除成功！',{icon: 1,offset: '100px'},function(){
						 window.location.reload();
	   	   			});
				} 
				else {
					layer.alert('未知错误，删除失败!', {
					 icon : 2,
					 title : '错误',
					 offset: '100px',//弹出位置
				     });
				     return false;
				}
			}
		});  
	}, function(){
	  
	});
	//询问框结束
}
//通用跳转
function tz(title,url,w,h){
	layer_show(title,url,w,h);
}
//页码跳转
function tiaozhuan(){
	var currentPage=$("#currentPage").val();
	window.location.href='<%=basePath%>leave/mylist?currentPage='+currentPage+'';
}
</script>
</body>
</html>