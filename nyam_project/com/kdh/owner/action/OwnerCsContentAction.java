package com.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.Ceo_NyamDAO;
import com.owner.model.Ceo_NyamDTO;
import com.owner.model.Service_nyamDAO;
import com.owner.model.Service_nyamDTO;

public class OwnerCsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_num = Integer.parseInt(request.getParameter("num").trim());
		
		Service_nyamDAO dao = Service_nyamDAO.getInstance();
		
		Service_nyamDTO dto = dao.getCont(board_num);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();

		//뷰페이지로 넘겨주니 폴스...
		forward.setRedirect(false);
		forward.setPath("owner_cs_content.jsp");
		
		//주소값 반환
		return forward;
	}

}
