package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ServiceDAO;
import com.admin.model.ServiceDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ServiceManagenetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 고객센터 문의글 전체내용을 뽑아오는 비지니스 로직
		
		int rowsize = 8;      // 한 페이지당 보여질 게시물의 수.
		int block = 3;        // 아래에 보여질 페이지의 최대 수. 예) [1][2][3] / [4][5][6]...
		int totalRecord = 0;  // DB 상의 게시물의 전체 수
		int allPage = 0;      // 전체 페이지 수
		
		int page = 0;         // 현재 페이지 변수
		
		if(request.getParameter("page") != null) {
			System.out.println("currunt page : " + request.getParameter("page"));
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {
			page = 1;
		}
		
				// 해당 페이지에서 시작 번호
				int startNo = (page*rowsize) - (rowsize-1);
				
				// 해당 페이지에서 끝번호
				int endNo = (page*rowsize);
				
				//해당 페이지에서 시작 블럭
				int startBlock = (((page -1) / block)*block)+1;
				
				//해당 페이지에서 끝 블럭
				int endBlock = (((page -1) / block)*block)+block;
		
		
				ServiceDAO dao = ServiceDAO.getInstance();
				
				
				totalRecord = dao.getServiceCount();
				
				allPage =(int)Math.ceil(totalRecord / (double)rowsize);   //전체게시물수 / 한페이지당 보여질 게시물수
				
				if(endBlock > allPage) {
					endBlock = allPage;
				}
				
				List<ServiceDTO> list =  dao.getServiceList(page,rowsize);
				
				request.setAttribute("servicelist", list);
				request.setAttribute("page", page);
				request.setAttribute("block", block);
				request.setAttribute("startBlock", startBlock);
				request.setAttribute("endBlock", endBlock);
				request.setAttribute("allPage", allPage);
				
				ActionForward forward = new ActionForward();
				
				forward.setRedirect(false);
				forward.setPath("kimmin/admin/admin_service_list.jsp");
				
		
		return forward;
	}

}
