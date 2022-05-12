package com.owner.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//로그인할때 넘겨준 아이디
		String owner_id = request.getParameter("owner_id").trim();
		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
				
		CeoNyamDTO dto = dao.getCeo(owner_id);
		
		int ceo_num = dto.getCeo_num();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("num", dto.getCeo_num());
		session.setAttribute("userId", owner_id);
		session.setAttribute("dto", dto);
		session.setAttribute("ceo_num", ceo_num);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_main.jsp");
		
		return forward;
	}

}
