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

import sun.reflect.generics.visitor.Reifier;

public class OwnerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int rowsize=4;
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
		
		
	
		
		
		
		Ceo_NyamDAO dao=Ceo_NyamDAO.getInstance();
		
		totalRecord=dao.getCeoCount();
		
		allPage=(int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock=allPage;
		}
		
		String imageurl = request.getSession().getServletContext().getRealPath("/image/ceoimage"); //톰캣 이미지 경로
		
		List<Ceo_NyamDTO> pageList=dao.getCeoList(page,rowsize);
		
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
		request.setAttribute("imageurl", imageurl); //톰캣 이미지경로

		
		ActionForward forward= new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("dohyung/view/owner_list.jsp");
		
		
		
		return forward;
	}

}
