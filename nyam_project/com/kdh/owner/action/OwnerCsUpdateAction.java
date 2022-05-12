package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.Service_nyamDAO;
import com.owner.model.Service_nyamDTO;

public class OwnerCsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int boardNum = Integer.parseInt(request.getParameter("num"));
		
		Service_nyamDAO dao = Service_nyamDAO.getInstance();
		Service_nyamDTO dto = dao.getCont(boardNum);
		
		request.setAttribute("cont", dto);
		request.setAttribute("num", boardNum);

		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("owner_cs_update.jsp");
		
		return forward;
	}

}
