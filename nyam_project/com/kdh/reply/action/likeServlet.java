package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.review.model.ReviewDAO;

import jdk.nashorn.api.scripting.JSObject;

/**
 * Servlet implementation class likeServlet
 */
@WebServlet("/like")
public class likeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// TODO Auto-generated method stub
				
				/*
				 * int rno=Integer.parseInt(request.getParameter("num")); int
				 * id=Integer.parseInt(request.getParameter("id"));
				 * 
				 * 
				 * 
				 * 
				 * 
				 * response.setContentType("text/html; charset=UTF-8");
				 * 
				 * PrintWriter out=response.getWriter();
				 * 
				 * 
				 * ReviewDAO dao= ReviewDAO.getInstance();
				 * 
				 * int likecheck=dao.likeMethod(rno,id);
				 */
				  
				  
					 int rno=Integer.parseInt(request.getParameter("num").trim()); 
					 String id=request.getParameter("id").trim();
					 int like = 0, likecheck = 0;
					
					  
					  
					  System.out.println("id"+id);
					  
					  response.setContentType("application/json; charset=UTF-8");
					  
					  PrintWriter out=response.getWriter();
					  
					
					  ReviewDAO dao= ReviewDAO.getInstance();
					  
					
					  
					  likecheck=dao.likeMethod(rno,id);
					  
					  if(likecheck==1) {
						  like = dao.likeInsert(rno);
					  } else if (likecheck==0) {
						  like = dao.likeDelete(rno);
						  
					  }
					  JSONObject obj = new JSONObject();
					  obj.put("likecheck",likecheck);
					  obj.put("like",like);
					  
				  
				 
				  
				  out.println(obj);
				
				  
				  
				  
				  
				  //반환된 고객정보를 클라이언트(ajax) 쪽으로 응답을 해주면 된다 out.println(like);


		}
		
		
	}


