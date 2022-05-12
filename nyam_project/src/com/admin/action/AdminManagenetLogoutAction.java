package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class AdminManagenetLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 관리자 로그아웃 비지니스로직
		HttpSession session = request.getSession();
		
		session.invalidate();  // 현재 사용중인 세션을 만료시키는 메서드.
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main.jsp");
		
		return forward;
	
	}

}
