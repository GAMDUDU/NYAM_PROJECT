<%@page import="com.ogj.model.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
userDAO dao = userDAO.getInstance();


String nickname = request.getParameter("nickname");
String id = (String)session.getAttribute("userID");



int result = dao.nicknamecheck(nickname , id);




out.println(result);


%>