<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
int begin=0,end=0;
int zong_page=0;
int in_page=0;
if(request.getSession().getAttribute("totalPage")!=null){
    zong_page=Integer.parseInt(request.getSession().getAttribute("totalPage").toString());
    in_page=Integer.parseInt(request.getSession().getAttribute("currentPage").toString());
}
if(zong_page<=10){
     begin=1;
     end=zong_page;
}
else{
    begin=in_page-5;
    end=in_page+4;
    if(begin<1){
        begin=1;
        end=10;
    }
    if(end>zong_page){
         end=zong_page;
         begin=zong_page-9;
    }
}
%> 




