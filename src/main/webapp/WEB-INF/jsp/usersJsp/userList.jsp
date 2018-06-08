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
<title>系统用户列表</title>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<script>
//多条件查询
function query(){
	document.getElementById('queryForm').submit();
}
</script>
</head>
<body>
  <div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
	  <form id="queryForm" action="<%=basePath%>/user/userList.do" method="post" class="form-inline">
		<div class="text-c"> 
	          姓名：<input type="text"  name="ZSXM" value="${ZSXM}" class="input-text radius" style="width:150px;"/>&nbsp;
	          警号：<input type="text"  name="JH" value="${JH}" class="input-text radius" style="width:150px;"/>&nbsp;
	          部门：<input type="text" id="pId2" name="pId2" value="${pId2}" class="input-text radius" style="width:200px;" readonly="readonly"/>
            <input type="hidden" id="did" name="did" value="${did}"/>
		    <div id="treediv" style="text-align:left; display: none;position:relative; z-index:99; overflow:scroll;  width:200px;height:300px;  padding: 5px;background: #fff;color: #fff;border: 1px solid #cccccc;">
             <script language="JavaScript" type="text/JavaScript">
				//树代码
				var ulist =<%=request.getAttribute("jsonString")%>;
				mydtree = new dTree('mydtree','../images/','no','no');
				mydtree.add('0', -1, "","","","", false);
				for(var i=0;i<ulist.length;i++){
					mydtree.add(ulist[i].bMBH,ulist[i].sJBM,ulist[i].bMMC,"javascript:setvalue('"+ulist[i].bMBH+"','"+ulist[i].bMMC+"')",ulist[i].bMMC,'_self',false);
				}
			   mydtree.closeAllChildren(1);
			   document.write(mydtree);
			</script>
	    	</div>
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
				<th scope="col" colspan="11"><center><font size='2'>系统用户列表</font></center></th>
			</tr>
			<tr class="text-c">
				<th width="5%">序号</th>
                <th>姓名</th>
                <th>警号</th>
                <th>身份证号</th>
                <th>性别</th>
                <th>婚否</th>
                <th>参加工作时间</th>
                <th>职务</th>
                <th>所属部门</th>
                <th>角色</th>
                <th>操作</th>
			</tr>
		</thead>
		<tbody>
	     <c:forEach items="${pageResult.list}" var="u" varStatus="status">
	          <tr>
	            <td><center>${pageResult.page.startIndex+status.count}</center></td>
	            <td><center>
	              <a onclick="tz('用户信息','<%=basePath%>/user/userInfo.do?id=${u.id}','600','450')" style="color:blue;">${u.ZSXM}</a>
	            </center></td>
	            <td><center>${u.JH}</center></td>
	            <td><center>${u.SFZH}</center></td>
	            <td>
	               <center>
				      <c:if test="${u.XB=='0'}">男</c:if>
				      <c:if test="${u.XB=='1'}">女</c:if>
				   </center>
				</td>
				<td>
	               <center>
				      <c:if test="${u.HF=='0'}">未婚</c:if>
				      <c:if test="${u.HF=='1'}">已婚</c:if>
				   </center>
				</td>
				<td><center><fmt:formatDate value="${u.CJGZSJ}" pattern="yyyy-MM-dd"/></center></td>
                <td><center>${u.ZWMC}</center>
                <td><center>${u.bmmc}</center></td>
                <td>
	               <center>
				      <c:if test="${u.role=='00'}">系统管理员</c:if>
				      <c:if test="${u.role=='01'}">部门管理员</c:if>
				      <c:if test="${u.role=='02'}">支队管理员</c:if>
				      <c:if test="${u.role=='03'}">普通用户</c:if>
				   </center>
				</td>
                <td><center>
                    <a onclick="chongzhi('${u.id}')"><font color='blue'>重置密码</font></a> 
                    &nbsp;
                    <a onclick="tz('修改用户信息','<%=basePath%>/user/updateUserUI.do?id=${u.id}','650','400')"><font color='blue'>修改</font></a> 
                    &nbsp;
                    <a onclick="del('${u.id}')"><font color='blue'>删除</font></a> 
                    </center>
                </td>
	          </tr>
          </c:forEach>
		   <!-- 显示页码开始 -->    
           <!-- 引入计算页码方法-->	   
           <%@ include file="/WEB-INF/jsp/yema.jsp" %>
           <!-- 显示页码部分 -->
           <tr>
            <td colspan="11">
              <div style="float:left;margin-top:6px;">
                <font size='2'>第<font size='2' color='red'>${pageResult.page.currentPage}</font>页
		        /共<font size='2' color='red'>${pageResult.page.totalPage}</font>页,
		               每页显示<font size='2' color='red'>${pageResult.page.pageRecord}</font>条,
		               共<font size='2' color='red'>${pageResult.page.totalRecord }</font>条</font>
	          </div>
	          <div style="float:right;margin-right:0px;">
              <c:choose>
                <c:when test="${pageResult.page.hasPrePage}">
                  <a href="<%=basePath%>/user/userList.do?currentPage=1&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}"><font size='2'>首页</font></a> | 
                  <a href="<%=basePath%>/user/userList.do?currentPage=${pageResult.page.currentPage-1}&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}"><font size='2'>上一页</font></a>
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
			           <a href='<%=basePath%>/user/userList.do?currentPage=<%=i%>&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}'><font size='2'>[<%=i%>]</font></a>   
			     <%	  
				     }
				}
			   %> 
               <c:choose>
                <c:when test="${pageResult.page.hasNextPage}">
                  <a href="<%=basePath%>/user/userList.do?currentPage=${pageResult.page.currentPage+1}&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}"><font size='2'>下一页</font></a> | 
                  <a href="<%=basePath%>/user/userList.do?currentPage=${pageResult.page.totalPage}&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}"><font size='2'>尾页</font></a>
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
//删除用户
function del(id) {
	$id=id; 
	//询问框
	 layer.confirm('你确定要删除该用户信息吗？', {
	 icon : 0,
	 title : '警告',
	 offset: '100px',//弹出位置
	 btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/user/delete.do',
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
//通用跳转
function tz(title,url,w,h){
	layer_show(title,url,w,h);
}
//重置用户密码
function chongzhi(id) {
	$id=id; 
	//询问框
	 layer.confirm('你确定要把该用户的密码重置为 “123” 吗？', {
	 icon : 0,
	 title : '警告',
	 offset: '100px',//弹出位置
	 btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>/user/chongzhi.do',
	    	data: {"id":id},
			success: function(data){
				 if("success" == data){
					 layer.alert('重置密码成功!',{icon: 1,offset: '100px'},function(){
						 window.location.reload();
	   	   				});
				 }
				 else{
					 layer.alert('未知错误，重置密码失败!', {
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
//页码跳转
function tiaozhuan(){
	var currentPage=$("#currentPage").val();
	window.location.href='<%=basePath%>/user/userList.do?currentPage='+currentPage+'&ZSXM=${ZSXM}&JH=${JH}&did=${did}&pId2=${pId2}';
}
</script>
<script type="text/javascript" src="../js/ztreexs.js"></script>
</body>
</html>