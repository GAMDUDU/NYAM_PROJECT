<%@page import="com.ogj.model.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String name = request.getParameter("name");
String email = request.getParameter("email");

userDAO dao = userDAO.getInstance();

String id =dao.searchUserId(name , email);




out.println(id);


%>