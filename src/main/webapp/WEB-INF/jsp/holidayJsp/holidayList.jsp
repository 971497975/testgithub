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
<title>节假日列表</title>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
</head>
<body>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
	   <span class="l"> <a href="javascript:;" onclick="addHoliday('添加节假日','<%=basePath%>holiday/addHolidayUI.do','450','300')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加节假日</a></span>  
	</div>
	<c:choose>
      <c:when test="${! empty list}">
	  <table class="table table-border table-bordered table-bg table-hover">
		<thead>
		    <tr>
				<th scope="col" colspan="12"><center><font size='2'>节假日列表</font></center></th>
			</tr>
			<tr class="text-c">
				<th width="10%">序号</th>
                <th>日期</th>
                <th>操作</th>
			</tr>
		</thead>
		<tbody>
	     <c:forEach items="${list}" var="u" varStatus="status">
	          <tr>
	            <td><center>${pageResult.page.startIndex+status.count}</center></td>
	            <td><center><fmt:formatDate value="${u.holidayDate}" pattern="yyyy-MM-dd"/></center></td>
                <td><center>
                    <a onclick="del('${u.id}')"><font color='blue'>删除</font></a> 
                    </center>
                </td>
	          </tr>
          </c:forEach>
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
//添加节假日
function addHoliday(title,url,w,h){
	layer_show(title,url,w,h);
}
//删除单位信息
function del(id) {
	$id=id; 
	//询问框
	 layer.confirm('你确定要删除该节假日吗？', {
	 icon : 0,
	 title : '警告',
	 offset: '100px',//弹出位置
	 btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/holiday/deleteHoliday.do',
	    	data: {"id":id},
			success: function(data){
				if("success" == data){
					 layer.alert('删除成功！',{icon: 1,offset: '100px'},function(){
						 window.location.reload();
	   	   				});
				 }
				 else{
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
</script>
<br><br><br><br><br><br>
</body>
</html>