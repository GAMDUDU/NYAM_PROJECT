package com.kdh.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kdh.review.model.ReplyDAO;
import com.kdh.review.model.ReplyDTO;

/**
 * Servlet implementation class InsertServlet2
 */
@WebServlet("/insert2")
public class InsertServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;chareset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		int r_c_num=Integer.parseInt(request.getParameter("r_c_num").trim());
		int r_num=Integer.parseInt(request.getParameter("r_num").trim());
		
		String reply_cont=request.getParameter("cont").trim();
		
		
		ReplyDTO dto = new ReplyDTO();
		dto.setReply_id(userId);
		dto.setReply_cont(reply_cont);
		dto.setReply_ceo_num(r_c_num);
		dto.setReply_review_num(r_num);
		
		
		ReplyDAO dao = ReplyDAO.getInstance();
		int check=dao.insertReply(dto);
		
		
		
		out.println(check);
	}

}
