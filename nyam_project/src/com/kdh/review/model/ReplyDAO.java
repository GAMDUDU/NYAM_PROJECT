package com.kdh.review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	//싱글턴 방식
	//instance : 참조변수(주소값)
	private static ReplyDAO instance;
	
	public ReplyDAO() {
	}

	//기본생성자 대신 싱글턴 객체를 return해주는 getInstance()라는 메소드를 만들어서 그 메소드에 외부에서 접근할수 있도록 함
	//어디서 생성하든 항상 동일한 주소값
	public static ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}	//getInstance() 끝
	
	//db연동 메소드 - dbcp방식
	public void openCon() {
		try {
			//jndi 서버 객체 생성
			Context ctx = new InitialContext();
			
			//lookup 메소드 이용하여 매칭되는 커넥션 찾기
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			//DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	//opencon() 끝
	
	//db에 연결된 자원 종료하는 메소드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
	}	//closeconn() 끝
	
	public String getReplyList(int rcno, int rno) {
		String result="";
		
		
		try {
			openCon();
			sql="select * from reply_nyam where reply_ceo_num=? and reply_review_num=? order by reply_num desc";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, rcno); 
			pstmt.setInt(2, rno);
			
			rs=pstmt.executeQuery();
			
			result+="<replys>";
			
			while(rs.next()) {
				result+="<reply>";
				
				result+="<id>"+rs.getString("reply_id")+"</id>";
				result+="<cont>"+rs.getString("reply_cont")+"</cont>";
				result+="<date>"+rs.getString("reply_date")+"</date>";
				result+="<no>"+rs.getInt("reply_num")+"</no>";
				result+="</reply>";
				
			}
			
			result+="</replys>";
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return result;
		
		
	}
	
	
	public String getReplyList(int no) {
		String result="";
		
		
		try {
			openCon();
			sql="select * from reply_nyam where reply_ceo_num=? and reply_review_num=? order by reply_num desc";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, no); 
			pstmt.setInt(2, 0);
			
			rs=pstmt.executeQuery();
			
			result+="<replys>";
			
			while(rs.next()) {
				result+="<reply>";
				
				result+="<id>"+rs.getString("reply_id")+"</id>";
				result+="<cont>"+rs.getString("reply_cont")+"</cont>";
				result+="<date>"+rs.getString("reply_date")+"</date>";
				result+="<no>"+rs.getInt("reply_num")+"</no>";
				result+="</reply>";
				
			}
			
			result+="</replys>";
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return result;
		
		
	}
	
	public int insertReply(ReplyDTO dto) {
		int result=0,count=0;
		
		
		try {
			openCon();
			sql="select max(reply_num) from reply_nyam";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1)+1;
			}
			
			sql="insert into reply_nyam values(?,?,?,?,?,sysdate,0)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getReply_ceo_num());
			pstmt.setInt(2, dto.getReply_review_num());
			pstmt.setInt(3, count);
			pstmt.setString(4, dto.getReply_cont());
			pstmt.setString(5, dto.getReply_id());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
			
		}
		
		return result;
		
		
		
	}
	

}
