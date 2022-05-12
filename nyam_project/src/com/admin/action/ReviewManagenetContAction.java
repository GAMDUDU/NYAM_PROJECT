package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ReviewDAO;
import com.admin.model.ReviewDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ReviewManagenetContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 리뷰 상세내역 >> 리뷰글 페이지로 이동하는게 나을까요
		
		int review_num = Integer.parseInt(request.getParameter("num").trim());
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		ReviewDTO dto = dao.getReviewCont(review_num);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("kimmin/admin/admin_review_content.jsp");
		
		return forward;
	}

}
