package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String ceo_id = (String)session.getAttribute("id");
		
		System.out.println("불러온 ceoid = "+ceo_id );
		
		/*int ceo_num = Integer.parseInt(request.getParameter("ceo_num").trim());*/
		

		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		CeoNyamDTO dto = dao.getCeo("ceo_id");
		
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();
		

		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_insert.jsp");
		
		return forward;
	}

}
