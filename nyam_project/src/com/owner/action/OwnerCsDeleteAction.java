package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.ServiceNyamDAO;

public class OwnerCsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardNum = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession(); 
		int ceo_num = (Integer)session.getAttribute("num");
		
		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		
		int check = dao.deleteService(boardNum);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("owner_cs.do?num=" + ceo_num);
		} else {
			out.println("<script>");
			out.println("alert('글 삭제중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
