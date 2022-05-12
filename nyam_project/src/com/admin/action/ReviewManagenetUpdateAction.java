package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ReviewDAO;
import com.admin.model.ReviewDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ReviewManagenetUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 리뷰 수정 비지니스 로직
		
		// 첨부파일이 저장될 위치(경로)를 설정.
		String saveFolder = 
				"C:\\\\NCS\\\\workspace(jsp)\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp1\\\\wtpwebapps\\\\nyam_project\\\\upload";
		
		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize =10* 1024* 1024; //10MB
		
		// 이진파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
			);
		
		
		int num = Integer.parseInt(multi.getParameter("num").trim());
		int bad = Integer.parseInt(multi.getParameter("bad").trim());
		String image = multi.getFilesystemName("image_new");
		
		if(image == null) {  // 수정할 첨부파일 이미지가 없는 경우.
			image = multi.getParameter("image_old");
		}
		
		
		ReviewDTO dto = new ReviewDTO();
		
		dto.setReview_num(num);
		dto.setReview_bad(bad);
		dto.setReview_image(image);
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int check = dao.ReviewUpdate(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			if(bad ==2) {
				out.println("<script>");
				out.println("alert('리뷰를 차단하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			forward.setRedirect(true);
			forward.setPath("review_Management.do");
			

			
		}else {
			out.println("<script>");
			out.println("alert('리뷰 수정에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
