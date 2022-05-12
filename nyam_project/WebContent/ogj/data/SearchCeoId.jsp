<%@page import="com.ogj.model.ceoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String name = request.getParameter("name");
String email = request.getParameter("email");

ceoDAO dao = ceoDAO.getInstance();

String id =dao.searchCeoId(name , email);




out.println(id);


%>