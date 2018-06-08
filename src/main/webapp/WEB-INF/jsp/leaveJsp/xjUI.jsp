<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>添加请假</title>
<script type="text/javascript">
$(document).ready(function(){
    var xjzt='${xjzt}';
    if(xjzt=="02"||xjzt=="03"){
       $('#ly').show();
    }else{
       $('#ly').hide();
    }
});
</script> 
</head>
<body>
<div class="page-container">
 <form class="form form-horizontal" enctype="multipart/form-data"  method="post" id="add">

	<div class="row cl">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>销假情况：</label>
		<div class="formControls col-xs-7 ">
			 <c:if test="${xjzt=='01'}"><input class="input-text radius"  type="text" name="xjzt" value="正常" readonly='readonly'></c:if>
			 <c:if test="${xjzt=='02'}"><input class="input-text radius"  type="text" name="xjzt" value="延期" readonly='readonly'></c:if>
			 <c:if test="${xjzt=='03'}"><input class="input-text radius"  type="text" name="xjzt" value="提前" readonly='readonly'></c:if>
		</div>
	</div>
	<div class="row cl" id="ly">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>提前/延期原因：</label>
		<div class="formControls col-xs-7 ">
			<textarea  class="textarea radius" id="xjly"  name="xjly" placeholder="请输入提前/延期销假原因"></textarea>
		</div>
	</div>
	<div>
	 <font color='red'>
	   <c:if test="${xjzt=='01'}"></c:if>
	   <c:if test="${xjzt=='02'}"><font color='red'>注:已延期${count}天销假</font></c:if>
	   <c:if test="${xjzt=='03'}"><font color='red'>注:已提前${count}天销假</font></c:if>
	 </font>
	</div>
	<br>
	<div style="text-align:center;">
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" onclick="add()">
	</div>
	<input type="hidden" value="${leave.id}" name="id">
  </form>
</div>
<script>
function add(){
	var xjzt='${xjzt}';
	var xjly=$("#xjly").val();
	if((xjzt=="02"||xjzt=="03")&&xjly==""){
       layer.alert('提前/延期销假原因不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
	else{
	   $("#add").ajaxSubmit({   
       	 url: '<%=basePath%>/leave/xj.do',
       	 type: "post",
       	 dataType:"text",
       	 success:function(data){
       	 	if (data=="success") {
						layer.alert('销假成功！',{icon: 1}, function(index){
	            			 var index = parent.layer.getFrameIndex(window.name);
	       	          		   parent.layer.close(index);
	       	          		   parent.location.reload();
	   	   				  	   layer.close(index);
	   	   				});
					}else {
						layer.alert('销假失败!', {
							icon : 2,
							title : '错误',
							offset: '100px'
						     });
						    return false;
				    }
                }
             });
	}
}
</script>
</body>
</html>