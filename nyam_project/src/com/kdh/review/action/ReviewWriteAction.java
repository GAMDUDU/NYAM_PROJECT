package com.kdh.review.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.ReviewDAO;
import com.kdh.review.model.ReviewDTO;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int cnum=Integer.parseInt(request.getParameter("cnum").trim());
		/*
		 * ReviewDAO dao =ReviewDAO.getInstance();
		 * 
		 * 
		 * ReviewDTO dto =dao.getReviewCont(cnum);  이건 왜 있는건지..?
		 */ 
		
		request.setAttribute("cnum", cnum);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("dohyung/view/review_write.jsp");
		
		
		return forward;
	}

}
