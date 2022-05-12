package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ServiceDAO;
import com.admin.model.ServiceDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ServiceManagenetContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 문의글 상세내역
		
		int service_no = Integer.parseInt(request.getParameter("num").trim());
		ServiceDAO dao = ServiceDAO.getInstance();
		ServiceDTO dto = dao.getServiceCont(service_no);
		
		request.setAttribute("content", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("kimmin/admin/admin_service_content.jsp");
		return forward;
	}

}
