package com.review.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.review.model.ReviewDAO;
import com.review.model.ReviewDTO;

public class ReviewMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int rowsize=15;
		int block=4;
		int totalRecord=0;
		int allPage=0;
		int page=0;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page").trim());
			
		}else {
			page=1;
		}
		
		int startNo= (page*rowsize)-(rowsize-1);
		
		int endNo = (page*rowsize);
		
		int startBlock=((page-1)/block*block)+1;
		
		int endBlock=(((page-1)/block)*block)+block;
		
		ReviewDAO dao=ReviewDAO.getInstance();
		
		totalRecord=dao.getReviewCount();
		
		System.out.println("record >>> " + totalRecord);
		
		allPage=(int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock=allPage;
		}
		
		
		
		
		List<ReviewDTO> pageList=dao.getReviewList(page,rowsize);
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", pageList);
		
		ActionForward forward= new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("main.jsp");
		
		
		
		return forward;
	}

}
