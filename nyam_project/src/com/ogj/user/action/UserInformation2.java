package com.ogj.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.ogj.model.userDAO;
import com.ogj.model.userDTO;

public class UserInformation2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		String id = (String)session.getAttribute("id");
		
		
		
		userDAO dao = userDAO.getInstance();
		
		System.out.println(id);
		
		userDTO dto = dao.getInfo(id);
		
		request.setAttribute("userdto", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("ogj/login/information2.jsp");
		
		
		
		
		
		
		
		return forward;
	}

}
