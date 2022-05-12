package com.ogj.ceo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.ogj.model.ceoDAO;
import com.ogj.model.ceoDTO;

public class CeoInformation2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		String id = (String)session.getAttribute("id");
		
		ceoDAO dao = ceoDAO.getInstance();
		
		ceoDTO dto = dao.getInfo(id);
		
		request.setAttribute("ceodto", dto);
		
		System.out.println("사업자번호는"+dto.getCeo_conum());
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("ogj/login/ceoinformation2.jsp");
		
		
		
		
		
		
		return forward;
	}

}
