package com.kdh.owner.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class OwnerLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그아웃이라는 글자를 클릭하면 현재의 모든 정보(session)를 종료시켜주는 비즈니스 로직
		HttpSession session = request.getSession();
		
		session.invalidate();	//현재 사용중인 세션을 만료시키는 메서드
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("/dohyung/mains.jsp");
		
		return forward;
	}

}
