package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.MemberDAO;
import com.admin.model.MemberDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class MemberManagenetContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원정보 상세내역
		
		int member_num = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto =  dao.getMemberCont(member_num);
		
		request.setAttribute("content", dto);
	
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("kimmin/admin/admin_member_content.jsp");
		
		return forward;
	}

}
