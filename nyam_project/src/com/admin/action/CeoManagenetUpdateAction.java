package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.CeoDAO;
import com.admin.model.CeoDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class CeoManagenetUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 가게정보를 수정하는 비지니스 로직

		// 첨부파일이 저장될 위치(경로)를 설정.
				String saveFolder = 
						"C:\\Users\\GAMDU\\Documents\\nyam_nyam_0513\\nyam_project\\WebContent\\dohyung\\upload";
				//경로수정
				
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
		
		String ceo_name = multi.getParameter("name").trim();
		String ceo_pwd = multi.getParameter("pwd").trim();
		String ceo_phone = multi.getParameter("phone").trim();
		String ceo_addr = multi.getParameter("addr").trim();
		String ceo_car = multi.getParameter("car").trim();
		String ceo_conum = multi.getParameter("conum").trim();
		String ceo_cont = multi.getParameter("ceocont").trim();
		
		
		String ceo_image = multi.getFilesystemName("image_new");
		
		if(ceo_image == null) {  // 수정할 첨부파일 이미지가 없는 경우.
			ceo_image = multi.getParameter("image_old");
		}
		
		CeoDTO dto = new CeoDTO();
		
		dto.setCeo_num(num);
		dto.setCeo_name(ceo_name);
		dto.setCeo_pwd(ceo_pwd);
		dto.setCeo_phone(ceo_phone);
		dto.setCeo_addr(ceo_addr);
		dto.setCeo_car(ceo_car);
		dto.setCeo_conum(ceo_conum);
		dto.setCeo_cont(ceo_cont);
		dto.setCeo_image(ceo_image);
		
		CeoDAO dao = CeoDAO.getInstance();
		
		int check = dao.CeoUpdate(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("admin_ceo_Management.do");
			
		}else {
			out.println("<script>");
			out.println("alert('정보 수정에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
