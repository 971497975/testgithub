<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>审批页面</title>
<script>
$(document).ready(function(){
    var xs='${xs}';
    if(xs==""){
       $('#xyspr').show();
    }else{
       $('#xyspr').hide();
    }
});
</script>
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" enctype="multipart/form-data" action="user/addUser.do" method="post" id="add">
	<div class="row cl">
		<label class="form-label col-xs-3 "><span class="c-red">*</span>审核意见：</label>
		<div class="formControls col-xs-7 ">
			<textarea  class="textarea radius" id="spyj"  name="spyj" placeholder="请填写审批意见！"></textarea>
		</div>
	</div>
	
	<div class="row cl" id="xyspr">
		<label class="form-label col-xs-3 ">转交下一审核人：(${xyspr})</label>
		<div class="formControls col-xs-7 ">
			<select class="input-text radius"  id="dysprid" name="dysprid">
			   <option value=""></option>
			   <c:forEach items="${sprList}" var="spr">
			     <option value="${spr.id}">${spr.ZSXM}</option>
			   </c:forEach>
			</select>
			<font color='red'>(注：不同意则不需要转交下一审核人)</font>
		</div>
	</div>
	
	<br>
	<div style="text-align:center;">
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;不同意&nbsp;&nbsp;" onclick="butongyi()">&nbsp;
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;同意&nbsp;&nbsp;" onclick="tongyi()">
	</div>
	<input type="hidden" value="${id}" name="id">
  </form>
</div>
<script>
//同意操作
function tongyi(){
	var spyj=$("#spyj").val();
	var xs='${xs}';
	var dysprid=$("#dysprid").val();
	if(spyj==""){
		$("#spyj").val("同意")
	}
	//如果有下一审批人但是又没选
	if(xs==""&&dysprid==""){
	    layer.alert('请选择下一审批人!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
	}
	else{
	   $("#add").ajaxSubmit({   
       	 url: '<%=basePath%>/shenpi/tysp.do',
       	 type: "post",
       	 dataType:"text",
       	 success:function(data){
       	 	if (data=="success") {
						layer.alert('审核成功！',{icon: 1}, function(index){
	            			 var index = parent.layer.getFrameIndex(window.name);
	       	          		   parent.layer.close(index);
	       	          		   parent.location.reload();
	   	   				  	   layer.close(index);
	   	   				});
					}else {
						layer.alert('审核失败!', {
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
//不同意操作
function butongyi(){
	var spyj=$("#spyj").val();
	if(spyj==""){
		$("#spyj").val("不同意")
	}
	$("#add").ajaxSubmit({   
       	 url: '<%=basePath%>/shenpi/btysp.do',
       	 type: "post",
       	 dataType:"text",
       	 success:function(data){
       	 	if (data=="success") {
						layer.alert('审核成功！',{icon: 1}, function(index){
	            			 var index = parent.layer.getFrameIndex(window.name);
	       	          		   parent.layer.close(index);
	       	          		   parent.location.reload();
	   	   				  	   layer.close(index);
	   	   				});
					}else {
						layer.alert('审核失败!', {
							icon : 2,
							title : '错误',
							offset: '100px'
						     });
						    return false;
				    }
                }
             });
}
</script> 
</body>
</html>