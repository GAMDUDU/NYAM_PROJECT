<%@page import="com.ogj.model.userDTO"%>
<%@page import="com.ogj.model.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
userDAO dao = userDAO.getInstance();

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String email = request.getParameter("email");

userDTO dto = new userDTO();

dto.setMember_id(id);
dto.setMember_pwd(pwd);
dto.setMember_name(name);
dto.setMember_mail(email);
dto.setMember_phone(phone);

int result = dao.join(dto);




out.println(result);


%>