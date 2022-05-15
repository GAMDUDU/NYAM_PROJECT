package com.owner.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerMyPageInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CeoNyamDTO dto = new CeoNyamDTO();

		int ceo_num = Integer.parseInt(request.getParameter("ceo_num").trim());
		String ceo_pwd = request.getParameter("pwd").trim();
		String ceo_phone = request.getParameter("phone").trim();
		String ceo_mail = request.getParameter("mail").trim();
		//지도
		double lat = Double.parseDouble(request.getParameter("lat").trim());
		double lng = Double.parseDouble(request.getParameter("lng").trim());
		
		//db에안넘기는것들
		String ceo_id = request.getParameter("id").trim();
		String ceo_conum = request.getParameter("conum").trim();
		String ceo_name = request.getParameter("name").trim();
		
		dto.setCeo_num(ceo_num);
		dto.setCeo_pwd(ceo_pwd);
		dto.setCeo_phone(ceo_phone);
		dto.setCeo_mail(ceo_mail);
		dto.setCeo_lat(lat);
		dto.setCeo_lng(lng);
		
		dto.setCeo_id(ceo_id);
		dto.setCeo_conum(ceo_conum);
		dto.setCeo_name(ceo_name);
		
		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		int check = dao.updateMyCeo(dto);
		
		request.setAttribute("cont", dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(false);
			forward.setPath("eunchae/view/owner_mypage.jsp");
		}else if (check == -1) {
			out.println("<script>");
			out.println("alert('해당 가게 사장님이 아닙니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('정보 수정중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}

