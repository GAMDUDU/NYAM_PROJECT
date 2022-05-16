package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ogj.model.userDAO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;
import com.owner.model.ServiceNyamDAO;
import com.owner.model.ServiceNyamDTO;

public class OwnerCsWriteOkAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title").trim();
		String cont = request.getParameter("cont").trim();
		//세션으로 넘어온 아이디
		HttpSession session = request.getSession(); 
		String id = (String)session.getAttribute("id");

		String pwd="";
		
		userDAO dao2 = userDAO.getInstance();
		
		pwd = dao2.getpwd(id);
		
		
		
		ServiceNyamDTO dto = new ServiceNyamDTO();
		
		ServiceNyamDAO dao = ServiceNyamDAO.getInstance();
		
		int count = dao.countService();
		
		dto.setService_num(count);
		dto.setService_name(id);
		dto.setService_pwd(pwd);
		dto.setService_title(title);
		dto.setService_cont(cont);
		
		request.setAttribute("cont", dto);
		request.setAttribute("boardNum", dto.getService_num());
		
		int check = dao.insertService2(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("owner_cs.do?id=${id}");
		} else {
			out.println("<script>");
			out.println("alert('글 등록중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
