package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ogj.model.ceoDAO;
import com.ogj.model.ceoDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String ceo_id = (String)session.getAttribute("id");
		int ceo_num = (Integer)session.getAttribute("num");
		
		System.out.println("불러온 ceoid = "+ceo_id );
		System.out.println("ceo num" + ceo_num);
		

		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		CeoNyamDTO dto = dao.getCeo(ceo_id);
		
		System.out.println("dto 불러온 값"+dto.getCeo_id());
		
		request.setAttribute("num", ceo_num);
		request.setAttribute("content", dto);

		System.out.println("dto" + ceo_num);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_insert.jsp");
		
		return forward;
	}

}
