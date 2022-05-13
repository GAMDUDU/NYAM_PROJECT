package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ServiceDAO;
import com.admin.model.ServiceDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ServiceManagenetReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 고객센터 댓글 폼페이지에서 넘어온 데이터를 db에 저장하는 비지니스 로직
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		int group = Integer.parseInt(request.getParameter("service_group").trim());
		int step = Integer.parseInt(request.getParameter("service_step").trim());
		int indent = Integer.parseInt(request.getParameter("service_indent").trim());
		String pwd = request.getParameter("pwd").trim();
		String code = request.getParameter("code").trim();
		String title = request.getParameter("title").trim();
		String writer = request.getParameter("writer").trim();
		String check = request.getParameter("check").trim();
		String content = request.getParameter("content").trim();
		
	
		
		ServiceDTO dto = new ServiceDTO();
		
		dto.setService_num(num);
		dto.setService_group(group);
		dto.setService_step(step);
		dto.setService_indent(indent);
		dto.setService_pwd(pwd);
		dto.setService_code(code);
		dto.setService_title(title);
		dto.setService_name(writer);
		dto.setService_check(check);
		dto.setService_cont(content);
		
		ServiceDAO dao = ServiceDAO.getInstance();
		
		//기존답변글이 있을경우 step를 하나 증가시켜주는 메서드호출
		dao.replyUpdate(group, step);
		
		//답변글을 db에 저장하는 메서드호출
		int reply = dao.replyService(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(reply > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_service_Management.do");
		}else {
			out.println("<script>");
			out.println("alert('답변글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		
		return forward;
	}

}
