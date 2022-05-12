package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ServiceDAO;
import com.admin.model.ServiceDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ServiceManagenetReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//답변글 폼페이지로 이동 메서드 
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		ServiceDAO dao = ServiceDAO.getInstance();
		
		ServiceDTO dto =dao.getServiceCont(num);
		
		request.setAttribute("reply", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("kimmin/admin/admin_service_reply.jsp");
		
		return forward;
	}

}
