package com.owner.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;
import com.owner.model.CeoNyamDAO;
import com.owner.model.CeoNyamDTO;

public class OwnerInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CeoNyamDTO dto = new CeoNyamDTO();
		
		

		
		String saveFolder = request.getServletContext().getRealPath("image\\ceoimage");
		

		// 톰캣 절대경로로 수정했습니다. 
		// webcontent 에는 저장안됩니다. 
		
		//첨부파일 최대 크기 지정
		int fileSize = 10 * 1024 * 1024;	//10mb

		MultipartRequest multi = new MultipartRequest(
				request,						//일반적인 request객체 
				saveFolder,						//첨부파일이 저장될 경로
				fileSize,						//업로드할 파일의 최대 크기
				"UTF-8",						//문자 인코딩 방식
				new DefaultFileRenamePolicy()	//파일 이름이 같은 경우 중복 안되게 설정
				);
		
		int ceo_num = Integer.parseInt(multi.getParameter("ceo_num").trim());
		String ceo_cont = multi.getParameter("cont").trim();
		String ceo_phone = multi.getParameter("phone").trim();
		String ceo_addr = multi.getParameter("addr").trim();
		String ceo_car = multi.getParameter("car").trim();
		//지도
		double lat = Double.parseDouble(multi.getParameter("lat").trim());
		double lng = Double.parseDouble(multi.getParameter("lng").trim());
		
		File upload_file = multi.getFile("upload_file");
		
		if (upload_file != null) {	//첨부파일이 있으면
			//getName() : 첨부파일의 이름을 문자열로 반환해주는 메소드
			//첨부파일의 이름 받아오기
			String fileName = upload_file.getName();
			
			//날짜별로 구분하여 저장할것이기 때문에 날짜 객체 만들기
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			//	.../upload/2022-04-18
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
		
			//날짜 폴더 만들기
			File path1 = new File(homedir);
			
			if (!path1.exists()) {	//폴더가 존재하지 않으면
				path1.mkdir();		//폴더를 만들어줌
			}
			
			//파일 만들기 -> 예) 홍길동_파일명
			//.../upload/2022-04-18/홍길동_파일명
			String reFileName = ceo_num + "_" + fileName;
			
			upload_file.renameTo(new File(homedir + "/" + reFileName));
			
			// 실제 db에 저장되는 파일 이름 => "/2022-04-18/홍길동_파일명"
			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + reFileName;
			
			dto.setCeo_image(fileDBName);
		}
		
		dto.setCeo_num(ceo_num);
		dto.setCeo_cont(ceo_cont);
		dto.setCeo_phone(ceo_phone);
		dto.setCeo_addr(ceo_addr);
		dto.setCeo_car(ceo_car);
		dto.setCeo_lat(lat);
		dto.setCeo_lng(lng);

		CeoNyamDAO dao = CeoNyamDAO.getInstance();
		
		int check = dao.updateCeo(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			forward.setRedirect(true);
			forward.setPath("owner_content.do");
		}else if (check == -1) {
			out.println("<script>");
			out.println("alert('해당 가게 사장님이 아닙니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 등록중에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
