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

public class ReviewDAO {
	
	


	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static ReviewDAO instance = null;
	
	
	private ReviewDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static ReviewDAO getInstance() {
		
		if(instance == null) {
			instance = new ReviewDAO();
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
	
	// 전체 리뷰를 조회하는 메서드
	public List<ReviewDTO> getReviewList(int page, int rowsize){
		
		
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		
		int startNo = (page*rowsize) - (rowsize-1);
		
		int endNo = (page*rowsize);

		
		try {
			openConn();
			
			sql = "select * from "
					+ " (select row_number() "
					+ " over(order by review_num desc) rnum, "
					+ " b.* from review_nyam b) "
					+ " where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO dto =  new ReviewDTO();
				
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date").substring(0, 10));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return list;
		
		
		
	}//getReviewList() 메서드의 end
	
	
	// Review_nyam 테이블의 전체 게시물의 수를 확인하는 메서드.
		public int getReviewCount() {
			int count = 0;
			
			try {
				openConn();
				
				sql = "select count(*) from review_nyam order by review_num desc";
				
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
		} //getCeoCount() 메서드의 end

		
		public ReviewDTO getReviewCont(int num) {
			
			ReviewDTO dto = new ReviewDTO();
			

			try {
				openConn();
				sql="select*from review_nyam where review_num = ?";
				pstmt =con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs =pstmt.executeQuery();
				
				if(rs.next()) {
					
					dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
					dto.setReview_num(rs.getInt("review_num"));
					dto.setReview_title(rs.getString("review_title"));
					dto.setReview_cont(rs.getString("review_cont"));
					dto.setReview_id(rs.getString("review_id"));
					dto.setReview_image(rs.getString("review_image"));
					dto.setReview_rate(rs.getInt("review_rate"));
					dto.setReview_went(rs.getString("review_went").substring(0, 10));
					dto.setReview_date(rs.getString("review_date").substring(0, 10));
					dto.setReview_like(rs.getInt("review_like"));
					dto.setReview_bad(rs.getInt("review_bad"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return dto;
	
		}//getReviewCont() 메서드의 end
		
		// 리뷰삭제 메서드
		public int ReviewDelete(int no) {
			
			int result = 0;
			

			try {
				openConn();
				sql = "delete from review_nyam where review_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				
				sql="update review_nyam set review_num = review_num-1 "
						+ " where review_num > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return result;
			
		}//ReviewDelete() 메서드의 end
		
		// 검색한 게시글 수를 구하는 메서드
		public int getSearchReviewCount(String field, String keyword) {
			int count = 0;
			openConn();
			
			if(field.equals("id")) {
				

				try {
					sql="select count(*) from review_nyam "
							+ " where review_id like ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count =rs.getInt(1);
					} rs.close(); pstmt.close(); con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(field.equals("title")) {
				try {
					sql="select count(*) from review_nyam "
							+ " where review_title like ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count =rs.getInt(1);
					} rs.close(); pstmt.close(); con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}return count;
			
		}//getSearchReviewCount() 메서드의 끝
		
		
		// 검색한 내용
		public List<ReviewDTO> getSearchReviewList(String field, String keyword, int page, int rowsize){
			
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();
			
			int startNo = (page*rowsize) - (rowsize-1);
			
			int endNo = (page*rowsize);
			
			openConn(); 
			
			if(field.equals("id")) {

				try {
					sql = "select * from "
							+ " (select row_number() "
							+ " over(order by review_num desc) rnum, "
							+ "b.* from review_nyam b where review_id like ?) "
							+ " where rnum >= ? and rnum <=?";
					pstmt =con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						ReviewDTO dto = new ReviewDTO();
						
						dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
						dto.setReview_num(rs.getInt("review_num"));
						dto.setReview_title(rs.getString("review_title"));
						dto.setReview_cont(rs.getString("review_cont"));
						dto.setReview_id(rs.getString("review_id"));
						dto.setReview_image(rs.getString("review_image"));
						dto.setReview_rate(rs.getInt("review_rate"));
						dto.setReview_went(rs.getString("review_went").substring(0, 10));
						dto.setReview_date(rs.getString("review_date").substring(0, 10));
						dto.setReview_like(rs.getInt("review_like"));
						dto.setReview_bad(rs.getInt("review_bad"));
						
						list.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
			}else if(field.equals("title")) {

				try {
					
					sql = "select * from "
							+ " (select row_number() "
							+ " over(order by review_num desc) rnum, "
							+ " b.* from review_nyam b where review_title like ?) "
							+ " where rnum >= ? and rnum <= ?";
					
					pstmt =con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						ReviewDTO dto = new ReviewDTO();
						
						dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
						dto.setReview_num(rs.getInt("review_num"));
						dto.setReview_title(rs.getString("review_title"));
						dto.setReview_cont(rs.getString("review_cont"));
						dto.setReview_id(rs.getString("review_id"));
						dto.setReview_image(rs.getString("review_image"));
						dto.setReview_rate(rs.getInt("review_rate"));
						dto.setReview_went(rs.getString("review_went").substring(0, 10));
						dto.setReview_date(rs.getString("review_date").substring(0, 10));
						dto.setReview_like(rs.getInt("review_like"));
						dto.setReview_bad(rs.getInt("review_bad"));
						
						list.add(dto);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}

			}return list;
		
		}//getSearchReviewList() 메서드의 end
		
		
		//리뷰 수정 메서드
		public int ReviewUpdate(ReviewDTO dto) {
			
			int result = 0;

					
			try {
				
				openConn();
				sql="update review_nyam set review_bad=?,"
						+ " review_image=? "
						+ " where review_num=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getReview_bad());
				pstmt.setString(2, dto.getReview_image());
				pstmt.setInt(3, dto.getReview_num());
			
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return result;
			
		}//ReviewUpdate() 메서드의 end
		
}
