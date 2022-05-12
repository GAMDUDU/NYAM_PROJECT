package com.ogj.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ogj.model.userDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;


public class UserInfoConformOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		String pwd = request.getParameter("pwd").trim();
		String id = (String)session.getAttribute("id");
		
		
		userDAO dao = userDAO.getInstance();
		
		int result = dao.confirmPwd(pwd,id);
		
		System.out.println(result);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(result >0) {
			forward.setRedirect(true);
			forward.setPath("user_information2.do");
			
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
