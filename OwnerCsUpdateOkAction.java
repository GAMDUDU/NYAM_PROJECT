package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;
import com.owner.model.ServiceNyamDAO;
import com.owner.model.ServiceNyamDTO;

public class OwnerCsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title").trim();
		String cont = request.getParameter("cont").trim();
		int boardNum = Integer.parseInt(request.getParameter("num"));

		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		
		int check = dao.updateService(boardNum, title, cont);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("owner_cs_content.do?num=" + boardNum);
		} else {
			out.println("<script>");
			out.println("alert('글 수정중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
