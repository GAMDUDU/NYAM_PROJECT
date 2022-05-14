package com.ogj.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class UserLoginOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(); 
		
		String id = request.getParameter("id");
		String user = request.getParameter("user");
		
		session.setAttribute("id", id);
		session.setAttribute("user", user);
		
		request.setAttribute("id", id);
		request.setAttribute("user", user);
				
		System.out.println("param에 저장된 아이디"+id);
		System.out.println("param에 저장된 "+user);
		System.out.println("세션에 저장된 아이디"+session.getAttribute("id"));
		System.out.println("세션에 저장된 유형"+session.getAttribute("user"));
		
		ActionForward forward = new ActionForward();
		
		String admin = "admin";
		
		forward.setRedirect(false);
		
		if(user.equals(admin)) {
			forward.setPath("kimmin/admin_main.jsp");
		}else {
		forward.setPath("dohyung/main.jsp");}
		
		
		return forward;
	}

}
