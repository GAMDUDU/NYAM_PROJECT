package com.owner.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyNyamDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	//싱글턴 방식
	//instance : 참조변수(주소값)
	private static ReplyNyamDAO instance;
	
	public ReplyNyamDAO() {
	}

	//기본생성자 대신 싱글턴 객체를 return해주는 getInstance()라는 메소드를 만들어서 그 메소드에 외부에서 접근할수 있도록 함
	//어디서 생성하든 항상 동일한 주소값
	public static ReplyNyamDAO getInstance() {
		if (instance == null) {
			instance = new ReplyNyamDAO();
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
	
	//가게 번호에 해당하는 전체 댓글 수를 확인하는 메소드
	public int getReplyCount(int no) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from reply_nyam where reply_ceo_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	//회원 아이디 해당하는 전체 댓글 수를 확인하는 메소드
	public int getReplyCount2(String id) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from reply_nyam where reply_ceo_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	//reply_nyam 테이블에서 현재 페이지에 해당하는 댓글을 조회하는 메소드
	public List<ReplyNyamDTO> getReplyList(int page, int rowsize, int no) {
		List<ReplyNyamDTO> list = new ArrayList<ReplyNyamDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 끝번호
		int endNo = (page * rowsize);
		
		try {
			openCon();
			
			sql = "select * from (select row_number() over(order by reply_date desc) rnum, b.* from reply_nyam b where reply_ceo_num = ?) where (rnum >= ? and rnum <= ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReplyNyamDTO dto = new ReplyNyamDTO();
				
				dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
				dto.setReply_review_num(rs.getInt("reply_review_num"));
				dto.setReply_num(rs.getInt("reply_num"));
				dto.setReply_cont(rs.getString("reply_cont"));
				dto.setReply_id(rs.getString("reply_id"));
				dto.setReply_date(rs.getString("reply_date"));
				dto.setReply_bad(rs.getInt("reply_bad"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}	
	
	//reply_nyam 테이블에서 현재 페이지에 해당하는 댓글을 조회하는 메소드
		public List<ReplyNyamDTO> getReplyList2(int page, int rowsize, String id) {
			List<ReplyNyamDTO> list = new ArrayList<ReplyNyamDTO>();
			
			int startNo = (page * rowsize) - (rowsize - 1);
			
			//해당 페이지에서 끝번호
			int endNo = (page * rowsize);
			
			try {
				openCon();
				
				sql = "select * from (select row_number() over(order by reply_date desc) rnum, b.* from reply_nyam b where reply_id = ?) where (rnum >= ? and rnum <= ?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ReplyNyamDTO dto = new ReplyNyamDTO();
					
					dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
					dto.setReply_review_num(rs.getInt("reply_review_num"));
					dto.setReply_num(rs.getInt("reply_num"));
					dto.setReply_cont(rs.getString("reply_cont"));
					dto.setReply_id(rs.getString("reply_id"));
					dto.setReply_date(rs.getString("reply_date"));
					dto.setReply_bad(rs.getInt("reply_bad"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}	
	
	//댓글 신고하는 메소드
	public int replyReport(String user_id, int ceo_no, int review_no, int reply_no) {
		int result = 0;
		
		ReplyNyamDTO dto = new ReplyNyamDTO();
		
		try {
			openCon();
			
			sql = "update reply_nyam set reply_bad = 1 where reply_ceo_num = ? and reply_review_num = ? and reply_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ceo_no);
			pstmt.setInt(2, review_no);
			pstmt.setInt(3, reply_no);
			
			pstmt.executeUpdate();
			
			sql = "select * from reply_nyam where reply_ceo_num = ? and reply_review_num = ? and reply_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ceo_no);
			pstmt.setInt(2, review_no);
			pstmt.setInt(3, reply_no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
				dto.setReply_review_num(rs.getInt("reply_review_num"));
				dto.setReply_num(rs.getInt("reply_num"));
				dto.setReply_cont(rs.getString("reply_cont"));
				dto.setReply_id(rs.getString("reply_id"));
				dto.setReply_date(rs.getString("reply_date"));
				dto.setReply_bad(rs.getInt("reply_bad"));
			}
			
			sql = "insert into bad_nyam values(?, ?, ?, ?, ?, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, dto.getReply_id());
			pstmt.setInt(3, ceo_no);
			pstmt.setInt(4, review_no);
			pstmt.setInt(5, reply_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	//신고한 댓글 수 카운트하는 메소드
	public int getReportedReplyCount(String id) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from bad_nyam where bad_user_id = ? and bad_reply_num != 0";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	//reply_nyam 테이블에서 현재 페이지에 해당하는 신고된 댓글을 조회하는 메소드
	public List<ReplyNyamDTO> getReportedReplyList(int page, int rowsize, String id) {
		List<ReplyNyamDTO> list = new ArrayList<ReplyNyamDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 끝번호
		int endNo = (page * rowsize);
		
		try {
			openCon();
			
			sql = "select * from (select row_number() over(order by reply_date desc) rnum, b.* from reply_nyam b inner join bad_nyam a on b.reply_num = a.bad_reply_num and b.reply_id = a.bad_writer_id and b.reply_review_num = a.bad_review_num where a.bad_user_id = ?) where (rnum >= ? and rnum <= ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReplyNyamDTO dto = new ReplyNyamDTO();
				
				dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
				dto.setReply_review_num(rs.getInt("reply_review_num"));
				dto.setReply_num(rs.getInt("reply_num"));
				dto.setReply_cont(rs.getString("reply_cont"));
				dto.setReply_id(rs.getString("reply_id"));
				dto.setReply_date(rs.getString("reply_date"));
				dto.setReply_bad(rs.getInt("reply_bad"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	//내가 쓴 댓글 개수 확인하는 메소드
		public int getMyReplyCount(String id) {
			int count = 0;
			
			try {
				openCon();
				
				sql = "select count(*) from reply_nyam where reply_id = ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return count;
		}
		
		//reply_nyam 테이블에서 현재 페이지에 해당하는 내가 쓴 댓글을 조회하는 메소드
		public List<ReplyNyamDTO> getMyReplyList(int page, int rowsize, String id) {
			List<ReplyNyamDTO> list = new ArrayList<ReplyNyamDTO>();
			
			int startNo = (page * rowsize) - (rowsize - 1);
			
			//해당 페이지에서 끝번호
			int endNo = (page * rowsize);
			
			try {
				openCon();
				
				sql = "select * from (select row_number() over(order by reply_date desc) rnum, b.* from reply_nyam b where reply_id = ?) where (rnum >= ? and rnum <= ?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ReplyNyamDTO dto = new ReplyNyamDTO();
					
					dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
					dto.setReply_review_num(rs.getInt("reply_review_num"));
					dto.setReply_num(rs.getInt("reply_num"));
					dto.setReply_cont(rs.getString("reply_cont"));
					dto.setReply_id(rs.getString("reply_id"));
					dto.setReply_date(rs.getString("reply_date"));
					dto.setReply_bad(rs.getInt("reply_bad"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}	
}
