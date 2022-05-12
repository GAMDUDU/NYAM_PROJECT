package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.ServiceNyamDAO;
import com.owner.model.ServiceNyamDTO;

public class OwnerCsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardNum = Integer.parseInt(request.getParameter("num"));
		
		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		ServiceNyamDTO dto = dao.getCont(boardNum);
		
		request.setAttribute("cont", dto);
		request.setAttribute("num", boardNum);

		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("owner_cs_update.jsp");
		
		return forward;
	}

}
