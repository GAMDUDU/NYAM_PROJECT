<%@page import="java.io.PrintWriter"%>
<%@page import="com.kdh.review.model.ReplyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

int rnum=Integer.parseInt(request.getParameter("reviewnum")); 
int cnum=Integer.parseInt(request.getParameter("ceonum"));


System.out.println("rnum 은"+rnum);
System.out.println("cnum 은"+cnum);



ReplyDAO dao= ReplyDAO.getInstance();



String str=dao.getReplyList2(rnum,cnum);


//반환된 고객정보를 클라이언트(ajax) 쪽으로 응답을 해주면 된다
out.println(str);
%>
