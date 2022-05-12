<%@page import="com.ogj.model.ceoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String id = request.getParameter("id");
String email = request.getParameter("email");

ceoDAO dao = ceoDAO.getInstance();

String pwd =dao.searchCeoPwd(id , email);




out.println(pwd);


%>