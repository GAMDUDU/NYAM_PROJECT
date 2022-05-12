<%@page import="com.ogj.model.userDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
userDAO dao = userDAO.getInstance();



String id = request.getParameter("id");
String pwd = request.getParameter("pwd");



int result = dao.logincheck(id,pwd);

if(result>0){
	session.setAttribute("userID", id);
	session.setAttribute("memberType", "user");
}
out.println(result);




%> 