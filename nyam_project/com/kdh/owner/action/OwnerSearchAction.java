package com.owner.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.review.model.Ceo_NyamDAO;
import com.review.model.Ceo_NyamDTO;
import com.review.model.ReviewDAO;

public class OwnerSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String search_field=request.getParameter("search_field").trim();
		String search_keyword=request.getParameter("search_keyword").trim();
		
		int rowsize = 4;
		int block = 4;
		
		int totalRecord=0;
		int allPage=0;	
		
		int page=0;													//���� ������ ����
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page").trim());
		}else {	//ó������ "��ü ��ù� ���" a �±׸� ������ ���
			page = 1;
			
		}
		
		//�ش����������� ���۹�ȣ
		int startNo = (page*rowsize)-(rowsize-1);
		
		//�ش� ���������� �� ��ȣ 
		int endNo= (page*rowsize);
		
		//�ش����������� ���� ��
		int startBlock=(((page-1)/block)*block)+1;
		
		//�ش����������� �� ��
		int endBlock=(((page-1)/block)*block)+block;
				
		Ceo_NyamDAO dao=Ceo_NyamDAO.getInstance();
		
		totalRecord=dao.searchListCount(search_field,search_keyword);
		
		allPage=(int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock=allPage;
		}
		
		List<Ceo_NyamDTO>searchList=dao.searchCeoList(search_field,search_keyword,page,rowsize);
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("search_field", search_field);
		request.setAttribute("search_keyword", search_keyword);
		request.setAttribute("List", searchList);
				
				
				
		ActionForward forward= new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("view/owner_search.jsp");
		
		
		
		return forward;
		
		
		
	}

}
