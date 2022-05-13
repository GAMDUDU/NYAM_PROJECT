package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//페이지에서 입력한 비번
		String pwd = request.getParameter("pwd").trim();
		
		//세션으로 넘어온 아이디
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		//아이디 이용해서 데베에있는 비번 접근
		CeoNyamDTO dto = dao.getCeo(userId);
		
		ActionForward forward = new ActionForward();

		PrintWriter out = response.getWriter();
		
		if (pwd.equals(dto.getCeo_pwd())) {
			forward.setRedirect(false);
			forward.setPath("eunchae/view/owner_mypage.jsp");
		} else {
			out.println("<scirpt>");
			out.println("alert('비밀번호를 다시 확인해주세요.')");
			out.println("history.back()");
			out.println("</scirpt>");
		}
		return forward;
	}

}
