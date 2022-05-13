package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.CeoDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class CeoManagenetDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 가게 테이블 삭제 메서드
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		CeoDAO dao = CeoDAO.getInstance();
		
		int check = dao.CeoDelete(num);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_ceo_Management.do");
		}else {
			out.println("<script>");
			out.println("alert('정보 삭제에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
				
		
		return forward;
	}

}
