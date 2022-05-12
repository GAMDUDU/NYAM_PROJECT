package com.ogj.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.ogj.model.userDAO;

public class UserInformation3 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		
		userDAO dao = userDAO.getInstance();
		
		int result = dao.updateInfo(id, pwd, phone);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		
		if (result > 0) {
			out.println("<script>");
			out.println("alert('회원정보 수정 성공')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("ogj/login/login_main.jsp");
			
		} else {
			out.println("<script>");
			out.println("alert('에러발생')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		return forward;
	}

}
