package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ReviewDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ReviewManagenetDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 리뷰삭제 비지니스로직
		
		int no = Integer.parseInt(request.getParameter("no").trim());
		ReviewDAO dao = ReviewDAO.getInstance();
		int check = dao.ReviewDelete(no);
		
		ActionForward forward = new ActionForward();
		
		
			forward.setRedirect(true);
			forward.setPath("review_Management.do");
		
		return forward;
	}

}
