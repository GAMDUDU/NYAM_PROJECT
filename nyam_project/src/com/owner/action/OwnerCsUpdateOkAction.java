package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.Ceo_NyamDAO;
import com.owner.model.Ceo_NyamDTO;
import com.owner.model.Service_nyamDAO;
import com.owner.model.Service_nyamDTO;

public class OwnerCsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title").trim();
		String cont = request.getParameter("cont").trim();
		int boardNum = Integer.parseInt(request.getParameter("num"));

		Service_nyamDAO dao = Service_nyamDAO.getInstance();
		
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
