package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.ReviewNyamDAO;

public class OwnerReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("userId");
		int ceo_num = Integer.parseInt(request.getParameter("num"));
		int review_num = Integer.parseInt(request.getParameter("review_no").trim());
	
		System.out.println("test" + userId);
		ReviewNyamDAO dao = ReviewNyamDAO.getInstance();
		
		int check = dao.reviewReport(userId, ceo_num, review_num);
		 
		PrintWriter out = response.getWriter();

		request.setAttribute("num", ceo_num);

		ActionForward forward = new ActionForward();

		
		if (check > 0) { 
			forward.setRedirect(false);
			forward.setPath("owner_reply.do"); 
		} else { 
			out.println("<script>");
			out.println("alert('신고 처리 중 오류가 발생했습니다.')"); 
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
