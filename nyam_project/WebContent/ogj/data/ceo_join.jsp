
<%@page import="com.ogj.model.ceoDAO"%>
<%@page import="com.ogj.model.ceoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String email = request.getParameter("email");
String conum = request.getParameter("conum");

ceoDTO dto = new ceoDTO();
ceoDAO dao = ceoDAO.getInstance();

dto.setCeo_id(id);
dto.setCeo_pwd(pwd);
dto.setCeo_name(name);
dto.setCeo_mail(email);
dto.setCeo_phone(phone);
dto.setCeo_conum(conum);

int result = dao.join(dto);




out.println(result);


%>