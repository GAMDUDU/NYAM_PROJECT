package com.ogj.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ogj.model.userDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;


public class infoInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		String pwd = request.getParameter("p");
		String nick = request.getParameter("n");
		String phone = request.getParameter("ph");
		
		String id = (String)session.getAttribute("id");
		
		
		
		userDAO dao = userDAO.getInstance();
		
		int result = dao.updateinfo(pwd,nick,phone,id);
		
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(result >0) {
			forward.setRedirect(true);
			forward.setPath("eunchae/view/main_list.jsp");
			
		}else {
			out.println("<script>");
			out.println("변경 안됨");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
