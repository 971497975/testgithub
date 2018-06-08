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
<title>修改密码</title>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<script>
  function add(){
        var MM= $("#MM").val();
        var newMM= $("#newMM").val();
        var newMM2= $("#newMM2").val();
		 if (MM == "") {
		      layer.alert('旧密码不能为空!', {
				icon : 0,
				offset: '100px',//弹出位置
				title : '警告'
			});
			return false;
		  } 
		  else if (newMM == "") {
		      layer.alert('新密码不能为空!', {
				icon : 0,
				offset: '100px',//弹出位置
				title : '警告'
			});
			return false;
		  } 
		  else if (newMM2 == "") {
		      layer.alert('确认新密码不能为空!', {
				icon : 0,
				offset: '100px',//弹出位置
				title : '警告'
			});
			return false;
		  } 
		  else if (newMM2 != newMM) {
		      layer.alert('新密码与确认新密码不一样!', {
				icon : 0,
				offset: '100px',//弹出位置
				title : '警告'
			});
			return false;
		  }
          else{
              //查找当前用户的旧密码是否正确
              $.ajax({
		            url: "<%=basePath%>/user/findPwd.do",
		            data:{"MM":MM},
		            type: "post",
		            success:function(data){
		               if(data=="success"){
		                   $("#add").ajaxSubmit({
		                   url:'<%=basePath%>/user/changepwdok.do',
				            type: "post",
				            dataType:"text",	         
				            success:function(data){
				               layer.alert('修改成功！',{icon: 1,offset: '100px'},function(){
								 window.location.reload();
			   	   				});
				            }
		                   
		                   });
		               }
		               else{
		                    layer.alert('旧密码错误!', {
							icon : 2,
							title : '错误',
							offset: '100px',//弹出位置
						     });
						    return false;
		               }
		            }
	            });
          }
}
function quxiao(){
     $("#MM").val("");
     $("#newMM").val("");
     $("#newMM2").val("");
}
</script>
</head>
  <body>
  <br> <br>
  <form  method="post"  enctype="multipart/form-data" action="user/editPwd.do"  id="add">
    <table class="table table-hover table-bordered" style="margin:auto; width:40%;">
	  <thead>
	    <tr>
	      <th colspan="4"><center><font color='red' size='3'>修改密码</font></center></th>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>旧密码</b></center></td>
	      <td><input type="password" class="input-text radius" id="MM" name="MM"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>新密码</b></center></td>
	      <td><input type="password" class="input-text radius" id="newMM" name="newMM"></td>
	    </tr>
	    <tr>
	      <td width="40%"><center><b>确认新密码</b></center></td>
	      <td><input type="password" class="input-text radius" id="newMM2" name="newMM2"></td>
	    </tr>
	   </thead>
	  </table>
	  <br>
	    <div align="center">
	      <button type="button" class="btn btn-primary radius" onclick="add()">确定</button>
	      &nbsp;&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary radius" onclick="quxiao()">取消</button>
	    </div>
   </form>
  </body>
</html>
