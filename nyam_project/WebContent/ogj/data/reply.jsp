<%@page import="com.ogj.model.ceoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String type = request.getParameter("type");
String id = request.getParameter("id");
String text = request.getParameter("text");
int ceonum = Integer.parseInt(request.getParameter("ceonum"));
int reviewnum = Integer.parseInt(request.getParameter("reviewnum"));

System.out.println("type은"+type);
System.out.println("type은"+id);
System.out.println("type은"+text);
System.out.println("type은"+ceonum);
System.out.println("type은"+reviewnum);

ceoDAO dao = ceoDAO.getInstance();

int result = dao.inputreply(type, id, text, ceonum, reviewnum);

out.println(result);
%>