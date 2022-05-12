package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.CeoDAO;
import com.admin.model.CeoDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class CeoManagenetContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 가게 정보 상세내역
		int ceo_num = Integer.parseInt(request.getParameter("num").trim());

		CeoDAO dao = CeoDAO.getInstance();
		CeoDTO dto = dao.getCeoCont(ceo_num);
		
		request.setAttribute("content", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("kimmin/admin/admin_ceo_content.jsp");
		return forward;
	}

}
 