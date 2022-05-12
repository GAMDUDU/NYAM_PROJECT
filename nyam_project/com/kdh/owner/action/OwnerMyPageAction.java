package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.Ceo_NyamDAO;
import com.owner.model.Ceo_NyamDTO;

public class OwnerMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//페이지에서 입력한 비번
		String pwd = request.getParameter("pwd").trim();
		
		//세션으로 넘어온 아이디
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();
		
		//아이디 이용해서 데베에있는 비번 접근
		Ceo_NyamDTO dto = dao.getCeo(userId);
		
		ActionForward forward = new ActionForward();

		PrintWriter out = response.getWriter();
		
		if (pwd.equals(dto.getCeo_pwd())) {
			forward.setRedirect(false);
			forward.setPath("owner_mypage.jsp");
		} else {
			out.println("<scirpt>");
			out.println("alert('비밀번호를 다시 확인해주세요.')");
			out.println("history.back()");
			out.println("</scirpt>");
		}
		return forward;
	}

}
