package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.MemberDAO;
import com.admin.model.MemberDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class MemberManagenetDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원 삭제 비지니스 로직
		
		
		int no = Integer.parseInt(request.getParameter("no").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.MemberDelete(no);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("member_Management.do");
		}else {
			out.println("<script>");
			out.println("alert('회원정보 삭제에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
