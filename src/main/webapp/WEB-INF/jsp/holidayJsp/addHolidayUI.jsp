<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>添加用户</title>
<script type="text/javascript">
function add(){
	var holidayDate=$("#holidayDate").val();
    if(holidayDate==""){
    	layer.alert('节假日不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else{
    	//先查找该节假日是否已经添加
    	$.ajax({
	            url: '<%=basePath%>/holiday/findExitHoliday.do',
	            data:{"holidayDate":holidayDate},
	            type: "post",
	            success:function(data){
	               if(data=="success"){
	                 //实现添加
	            	 $("#add").ajaxSubmit({
   	 		            url: '<%=basePath%>/holiday/addHoliday.do',
   	 		            type: "post",
   	 		            success:function(data){
   	 		               if(data=="success"){
	   	 		          	     layer.alert('添加成功！',{icon: 1,offset: '100px'}, function(index){
      	            			 var index = parent.layer.getFrameIndex(window.name);
      	       	          		   parent.layer.close(index);
      	       	          		   parent.location.reload();
      	   	   				  	   layer.close(index);
      	   	   				});
	   	 		          }
   	 		            }
   	 	            }); 
	               }
	               else{
	                    layer.alert('已经添加!', {
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

</script> 
</head>
<body>
<div class="page-container">
  <form class="form form-horizontal" enctype="multipart/form-data"  method="post" id="add">
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>节假日：</label>
		<div class="formControls col-xs-6 ">
			<input type="text" class="input-text radius Wdate"  id="holidayDate" name="holidayDate"  onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'holidayDate\')||\'2100-01-01\'}' })">
		</div>
	</div>
	<br>
	<div style="text-align:center;">
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" onclick="add()">
	</div>
	</form>
</div>
</body>
</html>