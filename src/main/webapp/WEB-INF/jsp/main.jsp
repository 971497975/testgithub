<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>请销假系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" /> 
	<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="assets/js/bui.js"></script>
    <script type="text/javascript" src="assets/js/config.js"></script>
  </head>
  <body>
    <div class="header">
    <div class="dl-title">
      <span class="lp-title-port"><img src="images/logo-1.png" style="margin-top:8px;" /></span>
    </div>
    <div class="dl-log">
       欢迎您！&nbsp;<span class="dl-log-user">${sessionScope.sessionZSXM}</span>
      <a href="<%=basePath%>/outLogin.do" onFocus="this.blur()" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <ul id="J_Nav"  class="nav-list ks-clear">
        <!-- 科级及以下是没有审批权限的 -->
        <%if(!(session.getAttribute("sessionZW").equals("01"))){%>
          <li class="nav-item" ><div class="nav-item-inner nav-order">待我审批</div></li>
        <%}%> 
        <%if(session.getAttribute("sessionJH").equals("003017")){%>
          <li class="nav-item" ><div class="nav-item-inner nav-order">待我审批</div></li>
        <%}%> 
        <li class="nav-item" ><div class="nav-item-inner nav-order">请假管理</div></li>
        <li class="nav-item" ><div class="nav-item-inner nav-order">个人中心</div></li>
        <%if(session.getAttribute("sessionRole").equals("00")||session.getAttribute("sessionRole").equals("01")){%>
        <li class="nav-item" ><div class="nav-item-inner nav-order">系统管理</div></li>
        <%}%> 
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
   </div>
  <script type="text/javascript">
  $(document).ready(function(){
	   //设置栏目是否需要左侧二级导航栏目
	   $('.nav-list li').click(function(){
		   var LN=$(this).attr('lang');
		   if(LN=='non'){
			   $(".dl-second-nav").hide();
		       $('.dl-inner-tab').css('margin-left','0');
		   }
		   else{
			   $(".dl-second-nav").show();
		       $('.dl-inner-tab').css('margin-left','230px');
		  } 
		   	      
	  });
	});
   </script>
  <script>
    BUI.use('common/main',function(){
      var config = [
      <%if(!(session.getAttribute("sessionZW").equals("01"))){%>
           {
            id:'dwsp',
            homePage : 'code',
            menu:[{
                text:'待我审批',
                items:[
                  {id:'code',text:'待我审批',href:'shenpi/splist.do'},
                  {id:'code2',text:'我的审批记录',href:'shenpi/sphistory.do'},
                ]
              }]
          },<%}%>
          <%if((session.getAttribute("sessionJH").equals("003017"))){%>
           {
            id:'dwsp',
            homePage : 'code',
            menu:[{
                text:'待我审批',
                items:[
                  {id:'code',text:'待我审批',href:'shenpi/splist.do'},
                  {id:'code2',text:'我的审批记录',href:'shenpi/sphistory.do'},
                ]
              }]
          },<%}%>
          {
      		id:'qjgl',
      		homePage : 'code',
            menu:[{
                text:'请假管理',
                items:[
                  {id:'code',text:'请假审批流程图',href:'leave/splcUI.do'},
                  {id:'code3',text:'添加请假单',href:'leave/addUI.do'},
                  {id:'code2',text:'我的请假单',href:'leave/mylist.do'},
                  /* {id:'code3',text:'我的销假管理',href:'leave/myXjList.do'}, */
                   <%if(session.getAttribute("sessionRole").equals("01")){%>
                  {id:'code4',text:'部门请假单',href:'leave/bmList.do'},
                  {id:'code7',text:'部门销假管理',href:'leave/bmxjList.do'},<%}%>
                   <%if(session.getAttribute("sessionRole").equals("00")){%>
                  {id:'code5',text:'系统请假单',href:'leave/xtList.do'},
                  {id:'code6',text:'系统销假管理',href:'leave/xtxjList.do'},<%}%>
                ]
              }]
          },
          {
            id:'gezx',
            homePage : 'code',
            menu:[{
                text:'个人中心',
                items:[
                  {id:'code',text:'我的信息',href:'user/myinfo.do'},
                  {id:'code2',text:'修改密码',href:'user/changePwdUI.do'},
                ]
              }]
          }
          <%if(session.getAttribute("sessionRole").equals("00")||session.getAttribute("sessionRole").equals("01")){%>
          ,{
            id:'xtgl',
            homePage : 'code',
            menu:[{
                text:'系统管理',
                items:[
                  {id:'code',text:'用户列表',href:'user/userList.do'},
                  {id:'code2',text:'添加用户',href:'user/addUserUI.do'},
                  <%if(session.getAttribute("sessionRole").equals("00")){%>
                  {id:'code3',text:'节假日管理',href:'holiday/holidayList.do'},<%}%>
                ]
              }]
          }<%}%>];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });  

</script>
  <div style="text-align:center;">
  </div>

 </body>
</html>

