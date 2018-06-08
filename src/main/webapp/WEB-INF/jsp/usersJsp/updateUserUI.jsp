<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<title>修改用户</title>
<script type="text/javascript">
function add(){
	var ZSXM=$("#ZSXM").val();
	var JH=$("#JH").val();
	var oldJH=$("#oldJH").val();
	var SFZH=$("#SFZH").val();
	var role=$("#role").val();
	var pid = $("#pid").val();
	var NXJTS = $("#NXJTS").val();
    var tt=/^\d+$/g;
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
    }else if(!tt.test(NXJTS)){
	   	layer.alert('年休假天数必须为正数!', {
			icon : 0,
			title : '警告',
			offset: '100px'
		});
	    return false;
    }else{
    	//如果用户名没变，就不需要判断
    	if(oldJH==JH){
    		    //实现修改
       	 		$("#add").ajaxSubmit({
		            url: '<%=basePath%>/user/updateUser.do',
		            type: "post",
		            dataType:"text",
		            success:function(data){
		               if(data=="success"){
		            	layer.alert('修改成功！',{icon: 1,offset: '100px'}, function(index){
            			 var index = parent.layer.getFrameIndex(window.name);
       	          		   parent.layer.close(index);
       	          		   parent.location.reload();
   	   				  	   layer.close(index);
   	   				   });
	 		          }else{
  	 		            layer.alert('系统异常，修改失败!', {
 						icon : 2,
 						title : '错误',
 						offset: '100px',//弹出位置
 					     });
 					    return false;
  	 	  	 		  }
		            }
	            });
    	}else{
    		//先查找该用户是否已经修改,警号查找
        	$.ajax({
    	            url: '<%=basePath%>/user/findExitUser.do',
    	            data:{"JH":JH},
    	            type: "post",
    	            success:function(data){
    	               if(data=="success"){
    	                 //实现修改
    	            	 $("#add").ajaxSubmit({
       	 		            url: '<%=basePath%>/user/updateUser.do',
       	 		            type: "post",
       	 		            dataType:"text",
       	 		            success:function(data){
       	 		               if(data=="success"){
       	 		            	layer.alert('修改成功！',{icon: 1,offset: '100px'}, function(index){
         	            			 var index = parent.layer.getFrameIndex(window.name);
         	       	          		   parent.layer.close(index);
         	       	          		   parent.location.reload();
         	   	   				  	   layer.close(index);
         	   	   				});
    	   	 		          }else{
  	    	 		            layer.alert('系统异常，修改失败!', {
    	 						icon : 2,
    	 						title : '错误',
    	 						offset: '100px',//弹出位置
    	 					     });
    	 					    return false;
  		    	 	  	 	   }
       	 		            }
       	 	            }); 
    	               }
    	               else{
    	                    layer.alert('该警号或身份证号已经被使用!', {
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
}

</script> 
</head>
<body>
<div class="page-container">
     <div style="text-align:center;">
		 <font size='5'><b>修改用户信息</b></font>
	 </div>
	<form class="form form-horizontal" enctype="multipart/form-data" method="post" id="add">
	
	<input type="hidden" id="id" name="id" value="${users.id}"/>
	<input type="hidden" id="oldJH" name="oldJH" value="${users.JH}"/>
	
	<div class="row cl">
		<label class="form-label col-xs-4"><span class="c-red">*</span>姓名：</label>
		<div class="formControls col-xs-6">
			<input type="text" class="input-text radius"  id="ZSXM" name="ZSXM" value="${users.ZSXM}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">警号：</label>
		<div class="formControls col-xs-6 ">
			<input type="text" class="input-text radius"  id="JH" name="JH" value="${users.JH}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">身份证号：</label>
		<div class="formControls col-xs-6 ">
			<input type="text" class="input-text radius"  id="SFZH" name="SFZH" value="${users.SFZH}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 "><span class="c-red">*</span>所属部门：</label>
		 <div class="formControls col-xs-6 ">
			<input type="text" id="SJDZZ" name="bmmc" class="input-text radius" readonly="readonly" value="${users.bmmc}"/>
            <input type="hidden" id="pid" name="did" value="${users.did}"/>
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
		<div class="formControls col-xs-6 ">
			<input type="radio" name="XB" value="0" <c:if test="${users.XB eq '0'}"> checked="checked"</c:if>/>
            <label>男</label>&nbsp;
            <input type="radio" name="XB" value="1" <c:if test="${users.XB eq '1'}"> checked="checked"</c:if>/>
            <label>女</label>&nbsp;
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">婚否：</label>
		<div class="formControls col-xs-6 ">
			<input type="radio" name="HF" value="1" <c:if test="${users.HF eq '1'}"> checked="checked"</c:if>/>
            <label>已婚</label>&nbsp;
            <input type="radio" name="HF" value="0" <c:if test="${users.HF eq '0'}"> checked="checked"</c:if>/>
            <label>未婚</label>&nbsp;
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">籍贯：</label>
		<div class="formControls col-xs-6 ">
			<input type="text" class="input-text radius"  id="JG" name="JG" value="${users.JG}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">参加工作时间：</label>
		<div class="formControls col-xs-6 ">
		   <input type="text" class="input-text radius Wdate" value="<fmt:formatDate value="${users.CJGZSJ}" pattern="yyyy-MM-dd"/>" id="CJGZSJ" name="CJGZSJ"  onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'CJGZSJ\')||\'2100-01-01\'}' })">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">年休假天数：</label>
		<div class="formControls col-xs-6">
			<input type="text" class="input-text radius"  id="NXJTS" name="NXJTS" value="${users.NXJTS}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">职务：</label>
		<div class="formControls col-xs-6">
			<input type="text" class="input-text radius"  id="ZWMC" name="ZWMC" value="${users.ZWMC}"/>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 ">职务类别：</label>
		<div class="formControls col-xs-6 ">
			<select class="input-text radius" name="ZW" id="ZW">
			    <option value="01" <c:if test="${users.ZW eq '01'}">selected="selected"</c:if>>科级及以下</option>
			    <option value="02" <c:if test="${users.ZW eq '02'}">selected="selected"</c:if>>副处长、副调研员</option>
			    <option value="03" <c:if test="${users.ZW eq '03'}">selected="selected"</c:if>>处长(政委)、调研员</option>
				<option value="04" <c:if test="${users.ZW eq '04'}">selected="selected"</c:if>>高警总队直属支队副调研员</option>
				<option value="05" <c:if test="${users.ZW eq '05'}">selected="selected"</c:if>>高警总队直属支队主要领导</option>
				<!-- <option value="06">高警总队主要负责人</option> -->
				<option value="07" <c:if test="${users.ZW eq '07'}">selected="selected"</c:if>>局分管领导</option>
				<option value="08" <c:if test="${users.ZW eq '08'}">selected="selected"</c:if>>局主要领导</option>
			</select>
		</div>
	</div>
    <div class="row cl">
		<label class="form-label col-xs-4 ">角色：</label>
		<div class="formControls col-xs-6">
			<select class="input-text radius" name="role" id="role">
			    <option value="03" <c:if test="${users.role eq '03'}">selected="selected"</c:if>>普通用户</option>
			    <option value="02" <c:if test="${users.role eq '02'}">selected="selected"</c:if>>支队管理员</option>
			    <option value="01" <c:if test="${users.role eq '01'}">selected="selected"</c:if>>部门管理员</option>
				<option value="00" <c:if test="${users.role eq '00'}">selected="selected"</c:if>>系统管理员</option>
			</select>
		</div>
	</div>
	<br>
	<div style="text-align:center;">
	   <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;修改&nbsp;&nbsp;" onclick="add()">
	</div>
	</form>
</div>
<script type="text/javascript" src="../js/ztreex.js"></script>
</body>
</html>