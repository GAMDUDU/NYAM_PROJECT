package com.kdh.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.owner.model.Ceo_NyamDAO;
import com.kdh.owner.model.Ceo_NyamDTO;
import com.kdh.owner.model.Service_nyamDAO;
import com.kdh.owner.model.Service_nyamDTO;

public class OwnerCsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title").trim();
		String cont = request.getParameter("cont").trim();
		//세션으로 넘어온 아이디
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("userId");

		Ceo_NyamDTO pwdDTO = new Ceo_NyamDTO();
		
		Ceo_NyamDAO ceo_NyamDAO = Ceo_NyamDAO.getInstance();
		
		pwdDTO = ceo_NyamDAO.getCeo(userId);
		
		String pwd = pwdDTO.getCeo_pwd();
		
		Service_nyamDTO dto = new Service_nyamDTO();
		
		dto.setService_name(userId);
		dto.setService_pwd(pwd);
		dto.setService_title(title);
		dto.setService_cont(cont);
		
		Service_nyamDAO dao = Service_nyamDAO.getInstance();
		
		request.setAttribute("cont", dto);
		request.setAttribute("boardNum", dto.getService_num());
		
		int boardNum = dto.getService_num();
		
		int check = dao.insertService(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("owner_cs_content.do?num=" + boardNum);
		} else {
			out.println("<script>");
			out.println("alert('글 등록중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
