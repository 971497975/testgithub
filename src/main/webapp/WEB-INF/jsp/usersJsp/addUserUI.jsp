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
	var ZSXM=$("#ZSXM").val();
	var JH=$("#JH").val();
	var SFZH=$("#SFZH").val();
	var role=$("#role").val();
	var pid = $("#pid").val();
	var CJGZSJ = $("#CJGZSJ").val();

    if(ZSXM==""){
    	layer.alert('姓名不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(JH==""&&SFZH==""){
    	layer.alert('警号和身份证号不能全为空，需要至少一个用于登录系统!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(pid==""){
    	layer.alert('所属部门不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
		return false;
    }
    else if(CJGZSJ==""){
	   	layer.alert('参加工作时间不能为空!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
	    return false;
    }else{
    	//先查找该用户是否已经添加,警号查找
    	$.ajax({
	            url: '<%=basePath%>/user/findExitUser.do',
	            data:{"JH":JH,"SFZH":SFZH},
	            type: "post",
	            success:function(data){
	               if(data=="success"){
	                 //实现添加
	            	 $("#add").ajaxSubmit({
   	 		            url: '<%=basePath%>/user/addUser.do',
   	 		            type: "post",
   	 		            dataType:"text",
   	 		            success:function(data){
   	 		               if(data=="success"){
	   	 		          	     layer.alert('添加成功！请在用户列表中查看。',{icon: 1,offset:'100px'}, function(index){
	   	       	          		 window.location.reload();
	   	   	   				});
	   	 		          }
   	 		            }
   	 	            }); 
	               }
	               else{
	                    layer.alert('该警号或身份证号已经添加!', {
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
		 <font size='5'><b>添加用户信息</b></font>
	 </div>
	<form class="form form-horizontal" enctype="multipart/form-data" action="user/addUser.do" method="post" id="add">
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>姓名：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  id="ZSXM" name="ZSXM">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">警号：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  id="JH" name="JH">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">身份证号：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  id="SFZH" name="SFZH">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>所属部门：</label>
		 <div class="formControls col-xs-4 ">
			<input type="text" id="SJDZZ" name="bmmc" class="input-text radius"  readonly="readonly">
            <input type="hidden" id="pid" name="did">
		    <div id="treediv" style="display: none;position:relative; z-index:99; overflow:scroll;  width: 250px;height:200px;  padding: 5px;background: #fff;color: #fff;border: 1px solid #cccccc;">
              <script language="JavaScript" type="text/JavaScript">
				//树代码
				var ulist =<%=request.getAttribute("jsonString")%>;
				mydtree = new dTree('mydtree','../images/','no','no');
				mydtree.add('<%=request.getAttribute("pid")%>', -1, "","","","", false);
				for(var i=0;i<ulist.length;i++){
					mydtree.add(ulist[i].bMBH,ulist[i].sJBM,ulist[i].bMMC,"javascript:setvalue('"+ulist[i].bMBH+"','"+ulist[i].bMMC+"')",ulist[i].bMMC,'_self',false);
				}
			   mydtree.closeAllChildren(1);
			   document.write(mydtree);
			  </script>
		   </div> 
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">性别：</label>
		<div class="formControls col-xs-4 ">
			<input type="radio" name="XB" value="0" checked="checked">
            <label>男</label>&nbsp;
            <input type="radio" name="XB" value="1">
            <label>女</label>&nbsp;
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">婚否：</label>
		<div class="formControls col-xs-4 ">
			<input type="radio" name="HF" value="1" checked="checked">
            <label>已婚</label>&nbsp;
            <input type="radio" name="HF" value="0">
            <label>未婚</label>&nbsp;
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">籍贯：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  id="JG" name="JG">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4"><span class="c-red">*</span>参加工作时间：</label>
		<div class="formControls col-xs-4">
			<input type="text" class="input-text radius Wdate"  id="CJGZSJ" name="CJGZSJ"  onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'CJGZSJ\')||\'2100-01-01\'}' })">
			<font color='red' size='1'>注:用于计算年休假，请认真填写!</font>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">职务：</label>
		<div class="formControls col-xs-4 ">
			<input type="text" class="input-text radius"  id="ZWMC" name="ZWMC">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">职务类别：</label>
		<div class="formControls col-xs-4 ">
			<select class="input-text radius" name="ZW" id="ZW">
			    <option value="01">科级及以下</option>
			    <option value="02">副处长、副调研员</option>
			    <option value="03">处长(政委)、调研员</option>
				<option value="04">高警总队直属支队副调研员</option>
				<option value="05">高警总队直属支队主要领导</option>
				<!-- <option value="06">高警总队主要负责人</option> -->
				<option value="07">局分管领导</option>
				<option value="08">局主要领导</option>
			</select>
		</div>
	</div>
    <div class="row cl">
		<label class="form-label col-xs-4 ">角色：</label>
		<div class="formControls col-xs-4 ">
			<select class="input-text radius" name="role" id="role">
			    <option value="03">普通用户</option>
			    <option value="01">部门管理员</option>
				<option value="00">系统管理员</option>
			</select>
		</div>
	</div>
	<br>
	<div style="text-align:center;">
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" onclick="add()">
	</div>
	</form>
</div>
<script type="text/javascript" src="../js/ztreex.js"></script>
<br><br><br><br><br><br><br>
</body>
</html>