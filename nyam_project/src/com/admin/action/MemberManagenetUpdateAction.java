package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.MemberDAO;
import com.admin.model.MemberDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class MemberManagenetUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원 정보를 수정하는 비지니스로직
		
		int member_num = Integer.parseInt(request.getParameter("num").trim());
		String member_phone = request.getParameter("phone").trim();
		String member_mail = request.getParameter("mail").trim();
		String member_nickname = request.getParameter("nickname").trim();
		String member_block = request.getParameter("block").trim();
		
		MemberDTO dto  = new MemberDTO();
		
		dto.setMember_num(member_num);
		dto.setMember_phone(member_phone);
		dto.setMember_mail(member_mail);
		dto.setMember_nickname(member_nickname);
		dto.setMember_block(member_block);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.MemberUpdate(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("admin_member_Management.do");
		}else {
			out.println("<script>");
			out.println("alert('회원정보 수정에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		

		
		return forward;
	}

}
