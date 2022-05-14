package com.ogj.ceo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.ogj.model.ceoDAO;
import com.ogj.model.ceoDTO;

public class CeoLoginOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String user = request.getParameter("user");
		
		
		ceoDAO dao = ceoDAO.getInstance();
		
		ceoDTO dto = dao.getInfo(id);
		//여기 그 정보 저장
		
		int ceo_num = dto.getCeo_num();
		
		session.setAttribute("id", id);
		session.setAttribute("user", user);
		session.setAttribute("num", ceo_num);
		
		request.setAttribute("content", dto);
		//여기 파람에 넘겨주는거 dto
		request.setAttribute("id", id);
		request.setAttribute("user", user);
		request.setAttribute("num", ceo_num);
		System.out.println("받은 아이디"+id);
		System.out.println("회원 유형"+user);
		System.out.println("세션에 저장된 아이디"+session.getAttribute("id"));
		System.out.println("세션에 저장된 유형"+session.getAttribute("user"));
		System.out.println("ceoNum" + session.getAttribute("num"));
		System.out.println("뭐임");
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("eunchae/view/main.jsp"); 
		
		
		return forward;
	}

}
