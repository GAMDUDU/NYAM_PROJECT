package com.kdh.owner.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.Ceo_NyamDAO;
import com.kdh.review.model.Ceo_NyamDTO;
import com.kdh.review.model.ReviewDAO;
import com.kdh.review.model.ReviewDTO;

public class OwnerContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int rowsize=10;
		int block=4;
		int totalRecord=0;
		int allPage=0;
		int page=0;
		
		
		
		
		int no=Integer.parseInt(request.getParameter("no").trim());
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page").trim());
			
		}else {
			page=1;
		}
		
		int startNo= (page*rowsize)-(rowsize-1);
		
		int endNo = (page*rowsize);
		
		int startBlock=((page-1)/block*block)+1;
		
		int endBlock=(((page-1)/block)*block)+block;
		
		allPage=(int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock=allPage;
		}
		
		
		
		
		
		
		ReviewDAO dar = ReviewDAO.getInstance();
		totalRecord=dar.getReviewCount(no);
		List<ReviewDTO> pageList=dar.reviewList(no,page,rowsize);
		
		
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
	
		request.setAttribute("No", no);
		
		//상단 위 임시로직
		

		
		/* int nowPage=Integer.parseInt(request.getParameter("page").trim()); */
		
		
		Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();
		Ceo_NyamDTO dto=dao.ceoContent(no);
		
		
		
		
		
		
		request.setAttribute("cont", dto);
		/* request.setAttribute("page", nowPage); */
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("dohyung/view/owner_content.jsp");
		return forward;
	}

}
