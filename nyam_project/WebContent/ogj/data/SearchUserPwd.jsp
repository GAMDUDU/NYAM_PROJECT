<%@page import="com.ogj.model.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String id = request.getParameter("id");
String email = request.getParameter("email");

userDAO dao = userDAO.getInstance();

String pwd =dao.searchUserPwd(id , email);




out.println(pwd);


%>