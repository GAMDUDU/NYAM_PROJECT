package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ServiceDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ServiceManagenetDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 문의글 삭제 비지니스 로직
		
		
		int no = Integer.parseInt(request.getParameter("num").trim());
		
		ServiceDAO dao = ServiceDAO.getInstance();
		
		int check = dao.ServiceDelete(no);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("service_Management.do");
		}else {
			out.println("<script>");
			out.println("alert('회원정보 삭제에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

	}


