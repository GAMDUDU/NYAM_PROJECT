package com.kdh.review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.kdh.review.model.Ceo_NyamDAO;
import com.kdh.review.model.RateDTO;
import com.kdh.review.model.ReviewDAO;

public class RateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int cnum=Integer.parseInt(request.getParameter("cnum").trim());
		int rate=Integer.parseInt(request.getParameter("rate").trim());
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("id");
		ReviewDAO dao = ReviewDAO.getInstance();
		RateDTO dto = new RateDTO();
		
		dto.setRate_ceo_num(cnum);
		dto.setRate_id(userId);
		dto.setRate_star(rate);
		
			
		
		
		int check=dao.insertRate(dto);
		
		double rat = dao.getAvgSelect(cnum);
		
		String drate = String.format("%.2f", rat);
		
		System.out.println(drate);
		
		Ceo_NyamDAO dar = Ceo_NyamDAO.getInstance();
		dar.getInsertAvg(drate, cnum);
		
		
		PrintWriter out = response.getWriter();
		
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('평점 입력 완료!')");
			out.println("window.close()");
			out.println("</script>");
			
		}else if(check==-1){
			out.println("<script>");
			out.println("alert('이미 평점을 작성하셨습니다')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('이미 리뷰를 작성하셨습니다')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		ActionForward forward= new ActionForward();
		
		
		
		return forward;
	}

}
