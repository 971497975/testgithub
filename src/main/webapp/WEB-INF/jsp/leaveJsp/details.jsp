<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/head.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>交管局机关工作人员休假申请表</title>
<style type="text/css">
* {
	outline: none;
}
table td{
font-size:14px;
}
</style>
</head>
<body>
<div style="width:640px; margin:0 auto; font-family:'楷体';font-size:14px;"  id="print">
<div style="width:100%; font-size:32px;text-align:center; padding-bottom:10px; font-weight:bold;">交管局机关工作人员休假申请表</div>
<br>
<table width="100%" border="1" cellpadding="0" cellspacing="0" style=" border-collapse: collapse; font-size:16px; margin:0 auto; text-align:center; ">
  <tr>
    <td style="padding:25px 3px;width:110px; ">姓名</td>
    <td style="width:110px;">${user.ZSXM}</td>
    <td style="padding:25px 3px;width:80px;">所在单位</td>
    <td style="width:120px;">${user.bmmc}</td>
    <td style="padding:25px 3px;width:80px;">职务</td>
    <td style="width:100px;">${user.ZWMC}</td>
  </tr>
  <tr>
    <td style="padding:25px 3px;">参加工作时间</td>
    <td><fmt:formatDate value="${user.CJGZSJ}" pattern="yyyy-MM-dd"/></td>
    <td style="padding:25px 3px;">籍贯</td>
    <td>${user.JG}</td>
    <td style="padding:25px 3px;">婚否</td>
    <td>${HF}</td>
  </tr>
  <tr>
    <td style="padding:25px 3px;">休假类别</td>
    <td colspan="5">${leave.leaveType}</td>
  </tr>
  <tr>
    <td style="padding:25px 3px;">休假天数</td>
    <td colspan="5" style="height:70px;"><label>从&nbsp;
        ${startYear}年${startMonth}月${startDay}日 至 ${endYear}年${endMonth}月${endDay}日
        </label><br/>
        <label style="margin-top:7px; display:inline-block;">共${leave.days}天(其中，双休日、法定节假日${leave.otherDays}天)</label>
        </td>
  </tr>
  <tr>
    <td style="padding:25px 3px;">休假事由<br/>
      (如因私出国境需详细说明去向时间等)</td>
    <td colspan="5">${leave.cause }</td>
  </tr>
  <tr style="height:100px;">
    <td style="padding:25px 3px;">所在单位意见</td>
    <td colspan="5">
       <c:if test="${user.ZW=='01'||user.ZW=='04'}">
        <c:if test="${desprid!=''}"> 审核人：${desprid}&nbsp;&nbsp;意见：${despryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${desprsj}" pattern="yyyy-MM-dd"/>
              <br></c:if>
                             审核人：${dysprid}&nbsp;&nbsp;意见：${dyspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dysprsj}" pattern="yyyy-MM-dd"/>
       </c:if>
       <c:if test="${user.ZW=='02'||user.ZW=='05'}">
                             审核人：${dysprid}&nbsp;&nbsp;意见：${dyspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dysprsj}" pattern="yyyy-MM-dd"/>
       </c:if>
    
    </td>
  </tr>
  <tr style="height:100px;">
    <td style="padding:25px 3px;">政工监察处意见</td>
    <td colspan="5">
       <c:if test="${user.ZW=='01'||user.ZW=='04'}">
          <c:if test="${dssprid!=''}">审核人：${dssprid}&nbsp;&nbsp;意见：${dsspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dssprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='02'||user.ZW=='05'}">
          <c:if test="${desprid!=''}">审核人：${desprid}&nbsp;&nbsp;意见：${despryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${desprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='03'||user.ZW=='07'||user.ZW=='08'}">
          <c:if test="${dysprid!=''}">审核人：${dysprid}&nbsp;&nbsp;意见：${dyspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dysprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
    </td>
  </tr>
  <tr style="height:100px;">
    <td style="padding:25px 3px;">局分管领导意见</td>
    <td colspan="5">
       <c:if test="${user.ZW=='01'||user.ZW=='04'}">
          <c:if test="${dsisprid!=''}">审核人：${dsisprid}&nbsp;&nbsp;意见：${dsispryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dsisprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='02'||user.ZW=='05'}">
          <c:if test="${dssprid!=''}">审核人：${dssprid}&nbsp;&nbsp;意见：${dsspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dssprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='03'}">
          <c:if test="${desprid!=''}">审核人：${desprid}&nbsp;&nbsp;意见：${despryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${desprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
    </td>
  </tr>
  <tr style="height:100px;">
    <td style="padding:25px 3px;">局主要领导意见</td>
    <td colspan="5">
       <c:if test="${user.ZW=='03'}">
          <c:if test="${dssprid!=''}">审核人：${dssprid}&nbsp;&nbsp;意见：${dsspryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dssprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='05'}">
          <c:if test="${dsisprid!=''}">审核人：${dsisprid}&nbsp;&nbsp;意见：${dsispryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${dsisprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
       <c:if test="${user.ZW=='07'||user.ZW=='08'}">
          <c:if test="${desprid!=''}">审核人：${desprid}&nbsp;&nbsp;意见：${despryj}&nbsp;&nbsp;日期：<fmt:formatDate value="${desprsj}" pattern="yyyy-MM-dd"/></c:if>
       </c:if>
    </td>
  </tr>
</table>
<div style="width:100%; margin-top:10px; font-size:16px; ">备注：除年休假，事假不包括双休日和法定节日外，其余假期均包括双休日和法定节日。</div>
</div>
  <br>
  <div style="text-align:center">
      <input id="dy" class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;打印&nbsp;&nbsp;"  onclick="printxun()" >
  </div>
  <br>
  <script>
  //开始打印
	function printxun(){
	    //隐藏不需要打印的
		$("#dy").hide(); 
	       //开始打印
		window.print(); 
	       //打印完成还原
		$("#dy").show(); 
	}  
  </script>
</body>
</html>

