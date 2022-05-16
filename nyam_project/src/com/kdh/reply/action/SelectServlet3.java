package com.kdh.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdh.review.model.ReplyDAO;

/**
 * Servlet implementation class SelectServlet2
 */
@WebServlet("/select3")
public class SelectServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("reviewnum")); 
		int cnum=Integer.parseInt(request.getParameter("ceoNum"));
		
		System.out.println("rnum 은"+rnum);
		System.out.println("cnum 은"+cnum);
		
		
		//int page=Integer.parseInt(request.getParameter("page"));
		
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		
		ReplyDAO dao= ReplyDAO.getInstance();
		
		
		
		String str=dao.getReplyList2(rnum,cnum);
		
		
		//반환된 고객정보를 클라이언트(ajax) 쪽으로 응답을 해주면 된다
		out.println(str);
		
		
	}

}
