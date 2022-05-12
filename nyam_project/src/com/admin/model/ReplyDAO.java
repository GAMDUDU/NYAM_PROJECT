package com.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDAO {
	
	

	


	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static ReplyDAO instance = null;
	
	
	private ReplyDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static ReplyDAO getInstance() {
		
		if(instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
		
	}  // getInstance() 메서드 end
	
	
	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds =
					(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}  // openConn() 메서드 end
	
	
	
	// DB에 연결된 자원을 종료하는 메서드.
	public void closeConn(ResultSet rs,
			PreparedStatement pstmt, Connection con) {
		

			try {
				
				if(rs != null) {
				rs.close();
			  }

				if(pstmt !=null) {
					pstmt.close();
				}
			
				if(con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	} // closeConn()메서드의 end
	
	
	// 리플 테이블의 전체 게시물 수를 확인하는 메서드
	
	public int getReplyCount() {
	
		
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from reply_nyam order by reply_num desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
			}
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
		
	}//getReplyCount() 메서드의 end
	
	//전체 댓글목록을 가져오는 메서드
	public List<ReplyDTO> getReplyList(int page, int rowsize){
		
		
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		
		int startNo = (page*rowsize) - (rowsize-1);
		
		int endNo = (page*rowsize);

		
		try {
			openConn();
			//매개변수 넘겨서 로우값
			
			sql = "select * from "
					+ " (select row_number() "
					+ " over(order by reply_num desc) rnum, "
					+ " b.* from reply_nyam b) "
					+ " where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReplyDTO dto = new ReplyDTO();
				
				dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
				dto.setReply_review_num(rs.getInt("reply_review_num"));
				dto.setReply_num(rs.getInt("reply_num"));
				dto.setReply_cont(rs.getString("reply_cont"));
				dto.setReply_id(rs.getString("reply_id"));
				dto.setReply_date(rs.getString("reply_date").substring(0, 10));
				dto.setReply_bad(rs.getInt("reply_bad"));
				
				list.add(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return list;
		
	}//getReplyList() 메서드의 end
	
	
	//댓글 삭제 비지니스 로직
	
	public int ReplyDelete(int num) {
		
		int result = 0;
		
		try {
			openConn();
			sql="delete from reply_nyam where reply_num = ?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
			sql="update reply_nyam set reply_num=reply_num-1 "
					+ " where reply_num > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return result;
		
		
	}//ReplyDelete() 삭제 비지니스 로직

	
	//댓글검색 수 메서드
	public int getSearchReplyCount(String field, String keyword) {
		int count = 0;
		openConn();
		
		if(field.equals("id")) {
			
			try {
				
				
				sql = "select count(*) from reply_nyam "
						+ " where reply_id like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
				}
				rs.close(); pstmt.close(); con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(field.equals("cont")) {
			try {
				
				
				sql = "select count(*) from reply_nyam "
						+ " where reply_cont like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
				}
				rs.close(); pstmt.close(); con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}//getSearchReplyCount() 메서드의 end
	
	//댓글 검색 리스트
	public List<ReplyDTO> getSearchReplyList(String field, String keyword,int page, int rowsize){
		
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		
		int startNo = (page*rowsize) - (rowsize-1);
		
		int endNo = (page*rowsize);
		
		openConn();
		
		if(field.equals("id")) {
			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by reply_num desc) rnum, "
						+ " b.* from reply_nyam b where reply_id like ?) "
						+ " where rnum >= ? and rnum <= ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs =pstmt.executeQuery();
				
				while(rs.next()) {
					
					ReplyDTO dto = new ReplyDTO();
					
					dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
					dto.setReply_review_num(rs.getInt("reply_review_num"));
					dto.setReply_num(rs.getInt("reply_num"));
					dto.setReply_cont(rs.getString("reply_cont"));
					dto.setReply_id(rs.getString("reply_id"));
					dto.setReply_date(rs.getString("reply_date").substring(0, 10));
					dto.setReply_bad(rs.getInt("reply_bad"));
					
					list.add(dto);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
		}else if(field.equals("cont")){
			

			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by reply_num desc) rnum, "
						+ " b.* from reply_nyam b where reply_cont like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ReplyDTO dto = new ReplyDTO();
					
					dto.setReply_ceo_num(rs.getInt("reply_ceo_num"));
					dto.setReply_review_num(rs.getInt("reply_review_num"));
					dto.setReply_num(rs.getInt("reply_num"));
					dto.setReply_cont(rs.getString("reply_cont"));
					dto.setReply_id(rs.getString("reply_id"));
					dto.setReply_date(rs.getString("reply_date").substring(0, 10));
					dto.setReply_bad(rs.getInt("reply_bad"));
					
					list.add(dto);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		}
		return list;
	}//getSearchReplyList() 메서드의 end
}
