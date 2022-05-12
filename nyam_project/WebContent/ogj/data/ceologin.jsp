<%@page import="com.ogj.model.ceoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ceoDAO dao = ceoDAO.getInstance();



String id = request.getParameter("id");
String pwd = request.getParameter("pwd");



int result = dao.logincheck(id,pwd);

if(result>0){
	session.setAttribute("userID", id);
	session.setAttribute("memberType", "ceo");
}

out.println(result);




%>