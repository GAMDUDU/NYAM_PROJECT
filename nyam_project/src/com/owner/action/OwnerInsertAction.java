package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.Ceo_NyamDAO;
import com.owner.model.Ceo_NyamDTO;

public class OwnerInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int ceo_num = Integer.parseInt(request.getParameter("ceo_num").trim());
		
		Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();
		
		Ceo_NyamDTO dto = dao.getCeo(ceo_num);
		
		request.setAttribute("ceo_num", ceo_num);
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("owner_insert.jsp");
		
		return forward;
	}

}
