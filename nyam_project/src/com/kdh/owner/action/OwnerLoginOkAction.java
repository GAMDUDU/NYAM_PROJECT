package com.kdh.owner.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.owner.model.Ceo_NyamDAO;
import com.kdh.owner.model.Ceo_NyamDTO;

public class OwnerLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//로그인할때 넘겨준 아이디
		String owner_id = request.getParameter("id").trim();
		
		Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();
				
		Ceo_NyamDTO dto = dao.getCeo(owner_id);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("num", dto.getCeo_num());
		session.setAttribute("userId", owner_id);
		session.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/dohyung/main.jsp");
		
		return forward;
	}

}
