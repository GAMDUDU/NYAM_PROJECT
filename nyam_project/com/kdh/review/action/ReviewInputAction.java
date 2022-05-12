package com.review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.review.model.Ceo_NyamDAO;
import com.review.model.ReviewDAO;
import com.review.model.ReviewDTO;

public class ReviewInputAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String saveFolder="C:\\Users\\user1\\git\\nyam_nyam\\nyamnyam\\WebContent\\upload";
		
		int fileSize=10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				
				);
		
		
		int cnum=Integer.parseInt(multi.getParameter("cnum").trim());
		
		String rtitle=multi.getParameter("rtitle").trim();
		String rcont=multi.getParameter("rcont").trim();
		String rid=multi.getParameter("rid").trim();
		int rate=Integer.parseInt(multi.getParameter("rate").trim());
		String went=multi.getParameter("went").trim();
		
		String rimage=multi.getFilesystemName("rimage");
		
		
		System.out.println(went);
		
		ReviewDTO dto = new ReviewDTO();
		
		dto.setReview_ceo_num(cnum);
		dto.setReview_title(rtitle);
		dto.setReview_cont(rcont);
		dto.setReview_id(rid);
		dto.setReview_rate(rate);
		dto.setReview_went(went);
		dto.setReview_image(rimage);
		
		ReviewDAO dao= ReviewDAO.getInstance();
		
		
		
		int check=dao.insertReview(dto);
		
		double rat = dao.getAvgSelect(cnum);
		
		String drate = String.format("%.2f", rat);
		
		System.out.println(drate);
		
		Ceo_NyamDAO dar = Ceo_NyamDAO.getInstance();
		dar.getInsertAvg(drate, cnum);
		
		
		ActionForward forward= new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("review_main.do");
			
		}else {
			out.println("<script>");
			out.println("alert('리뷰 등록 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
				
		
		
		
		return forward;
	}

}
