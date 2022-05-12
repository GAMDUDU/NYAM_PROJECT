package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.ReplyDAO;
import com.admin.model.ReplyDTO;
import com.owner.controller.Action;
import com.owner.controller.ActionForward;

public class ReplyManagenetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 댓글목록을 보는 비지니스로직
		
		
				int rowsize = 5;      // 한 페이지당 보여질 게시물의 수.
				int block = 3;        // 아래에 보여질 페이지의 최대 수. 예) [1][2][3] / [4][5][6]...
				int totalRecord = 0;  // DB 상의 게시물의 전체 수
				int allPage = 0;      // 전체 페이지 수
				
				int page = 0;         // 현재 페이지 변수
				
				if(request.getParameter("page") != null) {
					System.out.println("currunt page : " + request.getParameter("page"));
					page = Integer.parseInt(request.getParameter("page").trim());
				}else {
					page = 1;
				}
				
				// 해당 페이지에서 시작 번호
				int startNo = (page*rowsize) - (rowsize-1);
				
				// 해당 페이지에서 끝번호
				int endNo = (page*rowsize);
				
				//해당 페이지에서 시작 블럭
				int startBlock = (((page -1) / block)*block)+1;
				
				//해당 페이지에서 끝 블럭
				int endBlock = (((page -1) / block)*block)+block;
		
				
				ReplyDAO dao = ReplyDAO.getInstance();
				
				totalRecord = dao.getReplyCount();
				
				// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 함.
				// 이 과정을 거치면 전체 페이지 수가 나오게 됨.
				// 전체 페이지 수가 나올 때  나머지가 있으면 무조건 페이지 수를 하나 올려 주어야함.
				// Math.ceil 나머지가 나오면 무조건 올려주는 함수.
				// 더블형으로 반환하기 때문에 형변환 해주고, allPage는 int형이기 때문에 다시 또 형변환을 해주어야함.
				
				allPage =(int)Math.ceil(totalRecord / (double)rowsize);   //전체게시물수 / 한페이지당 보여질 게시물수
				
				if(endBlock > allPage) {
					endBlock = allPage; //endBlock 블럭이 남아있을경우 삭제작업
				}
		
				
				List<ReplyDTO> list =  dao.getReplyList(page,rowsize);
				
				request.setAttribute("replylist", list);
				request.setAttribute("page", page);
				request.setAttribute("block", block);
				request.setAttribute("startBlock", startBlock);
				request.setAttribute("endBlock", endBlock);
				request.setAttribute("allPage", allPage);
		
				ActionForward forward = new ActionForward();
				
				forward.setRedirect(false);
				
				forward.setPath("kimmin/admin/admin_reply_list.jsp");
				
				
				return forward;
	}

}
