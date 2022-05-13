package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;
import com.owner.model.ServiceNyamDAO;
import com.owner.model.ServiceNyamDTO;

public class OwnerCsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_num = Integer.parseInt(request.getParameter("num").trim());
		
		System.out.println(board_num);
		
		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		
		ServiceNyamDTO dto = dao.getCont(board_num);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();

		//뷰페이지로 넘겨주니 폴스...
		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_cs_content.jsp");
		
		//주소값 반환
		return forward;
	}

}
