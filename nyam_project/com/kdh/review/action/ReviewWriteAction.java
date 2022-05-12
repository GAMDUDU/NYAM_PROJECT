package com.review.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.review.model.ReviewDAO;
import com.review.model.ReviewDTO;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int cnum=Integer.parseInt(request.getParameter("cnum").trim());
		
		ReviewDAO dao =ReviewDAO.getInstance();
		
		
		ReviewDTO dto =dao.getReviewCont(cnum);
		
		request.setAttribute("cnum", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("view/review_write.jsp");
		
		
		return forward;
	}

}
