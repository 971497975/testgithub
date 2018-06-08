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
//显示隐藏
$(document).ready(function(){
     //请假类别如果没有选择年休假，都需要显示是否包含年休假
     $("#leaveType").bind("change",function(){
           var value=$("#leaveType").val();
           if(value=='年休假'||value==""){
              $('#bhnxj').hide();
              $('#bhnxjts').hide();
           }else{
              //'#bhnxj').show();
              $('#bhnxj').hide();
           }
     });
     
     $("#bhnxj").bind("change",function(){
         var value=$('input[name="bhnxj"]:checked').val();
         if(value=='1'){
              $('#bhnxjts').show();
         }else{
            $('#bhnxjts').hide();
            $('#nxjts').value(0);
         }
     });
     
     var count='${count}';
     if(count!='0'){
       layer.alert('您有 '+count+' 条请假记录需要销假，请抓紧时间处理！', {
	   icon : 0,
	   title : '警告',
	   offset: '100px'
       });
       return false;
     }
});
//添加请假
function add(){
	var leaveType=$("#leaveType").val();
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	var nxjts=$("#nxjts").val();
	var cause=$("#cause").val();
	var tt=/^\d+$/g;
	var dysprid=$("#dysprid").val();
    if(leaveType==""){
    	layer.alert('请选择请假类别!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(startDate==""){
    	layer.alert('请假开始时间不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(endDate==""){
    	layer.alert('请假结束时间不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(startDate>endDate){
    	layer.alert('请假开始时间不能大于请假结束时间!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(!tt.test(nxjts)){
	   	layer.alert('包含的年休假天数必须为正整数!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
	    return false;
    } 
    else if(nxjts>15){
	   	layer.alert('包含的年休假天数不符合规定!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
	    return false;
    } 
    else if(cause==""){
    	layer.alert('请假事由不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(dysprid==""){
    	layer.alert('审批人不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else{
         //实现添加
       	 $("#add").ajaxSubmit({
	            url: '<%=basePath%>/leave/addLeave.do',
	            type: "post",
	            dataType:"text",
	            success:function(data){
	               if(data=="success"){
		          	     layer.alert('请假成功，等待审核！请在我的请假单中查看。',{icon: 1,offset:'100px'}, function(index){
      	          		 window.location.reload();
  	   				});
		          }
		          else if(data=="nxjtsbz"){
	                layer.alert('可休年休假不足!', {
					icon : 2,
					title : '错误',
					offset: '100px',//弹出位置
				     });
				    return false;
                 }else if(data=="nxjtsgd"){
	                layer.alert('包含的年休假天数不能大于请假天数!', {
					icon : 2,
					title : '错误',
					offset: '100px',//弹出位置
				     });
				    return false;
                 }else{
	                layer.alert('添加失败，请重新添加!', {
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
     <div style="text-align:center;">
		 <font size='5'><b>添加请假单</b></font>
	 </div>
	<form class="form form-horizontal" enctype="multipart/form-data" action="user/addUser.do" method="post" id="add">
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>请假类别：</label>
		<div class="formControls col-xs-4 ">
			<select class="input-text radius"  id="leaveType" name="leaveType">
			   <option value=""></option>
			   <option value="事假">事假</option>
			   <option value="病假">病假</option>
			   <option value="婚假">婚假</option>
			   <option value="产假">产假</option>
			   <option value="护理假">护理假</option>
			   <option value="探亲假">探亲假</option>
			   <option value="年休假">年休假</option>
			   <option value="丧假">丧假</option>
			</select>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>请假开始时间：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius Wdate"  id="startDate" name="startDate"  onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'startDate\')||\'2100-01-01\'}' })">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>请假结束时间：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius Wdate"  id="endDate" name="endDate"  onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')||\'2100-01-01\'}' })">
		</div>
	</div>
	<div class="row cl" id="bhnxj" style="display:none;">
		<label class="form-label col-xs-4 ">是否包含年休假：</label>
		<div class="formControls col-xs-4 ">
			<input type="radio" name="bhnxj" value="0" checked="checked">
            <label>否</label>&nbsp;
            <input type="radio" name="bhnxj" value="1">
            <label>是</label>&nbsp;
		</div>
	</div> 
	<div class="row cl" id="bhnxjts" style="display:none;">
		<label class="form-label col-xs-4 ">包含的年休假天数：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  value="0" id="nxjts" name="nxjts">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>休假事由：</label>
		<div class="formControls col-xs-4 ">
			<textarea  class="textarea radius" id="cause"  name="cause" placeholder="如因私出国，必须填写去向、时间等。"></textarea>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>审核人(${xyspr})：</label>
		<div class="formControls col-xs-4 ">
			<select class="input-text radius"  id="dysprid" name="dysprid">
			   <option value=""></option>
			   <c:forEach items="${sprList}" var="spr">
			     <option value="${spr.id}">${spr.ZSXM}</option>
			   </c:forEach>
			</select>
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