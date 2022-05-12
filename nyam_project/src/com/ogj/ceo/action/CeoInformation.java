package com.ogj.ceo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class CeoInformation implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("ogj/login/ceoinformation.jsp");
		
		return forward;
	}

}
