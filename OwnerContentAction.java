package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerContentAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		int ceo_num = (Integer)session.getAttribute("num");
		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		CeoNyamDTO dto = dao.getCeo(ceo_num);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();

		//뷰페이지로 넘겨주니 폴스...
		forward.setRedirect(false);
		forward.setPath("eunchae/view/owner_content.jsp");
		
		//주소값 반환
		return forward;
	}

}
