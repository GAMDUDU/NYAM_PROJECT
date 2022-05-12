package com.kdh.review.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.ReviewDAO;
import com.kdh.review.model.ReviewDTO;

public class ReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no=Integer.parseInt(request.getParameter("no").trim());
		
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewDTO dto = dao.reviewContent(no);
		
		request.setAttribute("cont", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/dohyung/view/review_content.jsp");
		
		return forward;
				
		
	}

}
