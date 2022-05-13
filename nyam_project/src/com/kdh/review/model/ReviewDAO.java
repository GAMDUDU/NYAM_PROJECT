package com.kdh.review.model;

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
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	String sql=null;
	
	private static ReviewDAO instance;
	private ReviewDAO() {	}
	
	public static ReviewDAO getInstance() {
		
		if(instance==null) {
			instance = new ReviewDAO();
		}
		
		return instance;
	}
	
	public void openConn() {
		
		
		try {
			Context ctx = new InitialContext();
			
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			con=ds.getConnection();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} //openconn() �޼��� end
	
	// DB�� ����� �ڿ��� �����ϴ� �޼���
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		
		
			try {
				if(rs!=null) {
				rs.close();
				}
				
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}	//closeConn �޼��� end
	
	public int getReviewCount() {
		int count=0;
		
		
		try {
			openConn();
			sql="select count(*) from Review_nyam";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
		
		
	}
	
	public int getReplyCount(String id) {
		int count=0;
		
		
		try {
			openConn();
			sql="select count(*) from Reply_nyam where reply_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
		
		
	}
	
	public int getReviewCount(int no) {
		int count=0;
		
		
		try {
			openConn();
			sql="select count(*) from Review_nyam where review_ceo_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
		
		
	}
	
	public int getReviewCount(String id) {
		int count=0;
		
		
		try {
			openConn();
			sql="select count(*) from Review_nyam where review_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
		
		
	}
	
	
	public ReviewDTO getReviewCont(int no) {
		ReviewDTO dto = new ReviewDTO();
		
		try {
			openConn();
			sql="select * from review_nyam where review_ceo_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
			
		}
		return dto;
		
	}
	
	public List<ReviewDTO> reviewList(int no, int page, int rowsize){
		List<ReviewDTO> list= new ArrayList<ReviewDTO>();
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		
		
		try {
			openConn();
			
			sql="select * from(select row_number() over(order by review_num desc) rnum, b.* from review_nyam b where review_ceo_num=?) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto=new ReviewDTO();
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConn(rs, pstmt, con);
			
		}
		
		return list;
		
		
	}
	
	
	public List<ReplyDTO> getReplyList(int page, int rowsize, String id){
		List<ReplyDTO> list= new ArrayList<ReplyDTO>();
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		
		
		try {
			openConn();
			
			sql="select * from(select row_number() over(order by reply_num desc) rnum, b.* from reply_nyam b where reply_id=?) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyDTO dto=new ReplyDTO();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConn(rs, pstmt, con);
			
		}
		
		return list;
		
		
	}
	
	
	public ReviewDTO reviewContent(int no) {
		ReviewDTO dto= new ReviewDTO();
		
		
		try {
			openConn();
			sql="select*from review_nyam where review_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
		
		
		
	}
	
	
	
	
	public List<ReviewDTO> getReviewList(int page, int rowsize){
		List<ReviewDTO> list= new ArrayList<ReviewDTO>();
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		
		
		try {
			openConn();
			sql="select * from(select row_number() over(order by review_num desc) rnum, b.* from review_nyam b) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto=new ReviewDTO();
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConn(rs, pstmt, con);
			
		}
		
		return list;
		
		
	}
	
	public List<ReviewDTO> getReviewList(int page, int rowsize,String id){
		List<ReviewDTO> list= new ArrayList<ReviewDTO>();
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		
		
		try {
			openConn();
			sql="select * from(select row_number() over(order by review_num desc) rnum, b.* from review_nyam b where review_id=?) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto=new ReviewDTO();
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConn(rs, pstmt, con);
			
		}
		
		return list;
		
		
	}
	
	public List<ReviewDTO> getReviewList(int page, int rowsize,int no){
		List<ReviewDTO> list= new ArrayList<ReviewDTO>();
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		openConn();
		if(no==1) {
			
		
		try {
			
			sql="select * from(select row_number() over(order by review_rate desc, review_like desc ) rnum, b.* from search_nyam b) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto=new ReviewDTO();
				dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
				dto.setReview_num(rs.getInt("review_num"));
				dto.setReview_title(rs.getString("review_title"));
				dto.setReview_cont(rs.getString("review_cont"));
				dto.setReview_id(rs.getString("review_id"));
				dto.setReview_image(rs.getString("review_image"));
				dto.setReview_rate(rs.getInt("review_rate"));
				dto.setReview_went(rs.getString("review_went"));
				dto.setReview_date(rs.getString("review_date"));
				dto.setReview_like(rs.getInt("review_like"));
				dto.setReview_bad(rs.getInt("review_bad"));
				
				list.add(dto);
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConn(rs, pstmt, con);
			
		}
		}else if(no==2){
			
			try {
				
				sql="select * from(select row_number() over(order by review_rate desc) rnum, b.* from review_nyam b) where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					ReviewDTO dto=new ReviewDTO();
					dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
					dto.setReview_num(rs.getInt("review_num"));
					dto.setReview_title(rs.getString("review_title"));
					dto.setReview_cont(rs.getString("review_cont"));
					dto.setReview_id(rs.getString("review_id"));
					dto.setReview_image(rs.getString("review_image"));
					dto.setReview_rate(rs.getInt("review_rate"));
					dto.setReview_went(rs.getString("review_went"));
					dto.setReview_date(rs.getString("review_date"));
					dto.setReview_like(rs.getInt("review_like"));
					dto.setReview_bad(rs.getInt("review_bad"));
					
					list.add(dto);
					
				}
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeConn(rs, pstmt, con);
				
			}
			
		}
		
		return list;
		
		
	}
	
	
	public int insertReview(ReviewDTO dto) {
		int result=0, count=0;
		
		
		try {
			
			openConn();
			sql="select max(review_num) from review_nyam";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1)+1;
				
			}
			
			sql="insert into review_nyam values(?,?,?,?,?,?,?,?,sysdate,0,0)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,dto.getReview_ceo_num());
			pstmt.setInt(2,count);
			pstmt.setString(3,dto.getReview_title());
			pstmt.setString(4,dto.getReview_cont());
			pstmt.setString(5,dto.getReview_id());
			pstmt.setString(6,dto.getReview_image());
			pstmt.setInt(7,dto.getReview_rate());
			pstmt.setString(8,dto.getReview_went());
			
			
			result=pstmt.executeUpdate();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
		
		
		
	}
	
	public int searchListCount(String field, String keyword) {
		int count = 0;
		
		openConn();
		
		if(field.equals("title_content")) {
			
		
		try {
			sql="select count(*) from search_nyam where review_title like ? or review_cont like ?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		} else if(field.equals("rate")) {
			
			
				
				try {
					sql="select count(*) from search_nyam where review_rate>=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(keyword));
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						count=rs.getInt(1);
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
			}else if(field.equals("adress")) {
			
			
				
				try {
					sql="select count(*) from search_nyam where ceo_addr like ?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						count=rs.getInt(1);
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
			}
		
		return count;
				
			
			
		}
	
	
	public List<SearchDTO> searchReviewList(String field, String keyword, int page, int rowsize){
		List<SearchDTO> list=new ArrayList<SearchDTO>();
		
		int startNo=(page*rowsize)-(rowsize -1);
		int endNo=(page*rowsize);
		openConn();
		
		if(field.equals("title_content")) {
				
			
			try {
				sql="select *from(select row_number() over(order by review_rate desc, ceo_avgrate desc,review_went desc)"
						+"rnum, b.* from search_nyam b where review_title like ? or review_cont like ?)where rnum>=? and rnum<=?";
				
				
				pstmt=con.prepareStatement(sql);
				

				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					SearchDTO dto=new SearchDTO();
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_avgrate(rs.getString("ceo_avgrate"));
					dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
					dto.setReview_num(rs.getInt("review_num"));
					dto.setReview_title(rs.getString("review_title"));
					dto.setReview_cont(rs.getString("review_cont"));
					dto.setReview_id(rs.getString("review_id"));
					dto.setReview_image(rs.getString("review_image"));
					dto.setReview_rate(rs.getInt("review_rate"));
					dto.setReview_went(rs.getString("review_went"));
					dto.setReview_date(rs.getString("review_date"));
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
			} else if(field.equals("rate")) {
				
				
					
					try {
						sql="select *from(select row_number() over(order by review_rate desc,ceo_avgrate desc, review_went desc)"  
													+"rnum, b.* from search_nyam b)where review_rate>=? and rnum>=? and rnum<=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, Integer.parseInt(keyword));
						pstmt.setInt(2, startNo);
						pstmt.setInt(3, endNo);

						rs=pstmt.executeQuery();
						
						while(rs.next()) {
							SearchDTO dto=new SearchDTO();
							dto.setCeo_name(rs.getString("ceo_name"));
							dto.setCeo_addr(rs.getString("ceo_addr"));
							dto.setCeo_avgrate(rs.getString("ceo_avgrate"));
							dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
							dto.setReview_num(rs.getInt("review_num"));
							dto.setReview_title(rs.getString("review_title"));
							dto.setReview_cont(rs.getString("review_cont"));
							dto.setReview_id(rs.getString("review_id"));
							dto.setReview_image(rs.getString("review_image"));
							dto.setReview_rate(rs.getInt("review_rate"));
							dto.setReview_went(rs.getString("review_went"));
							dto.setReview_date(rs.getString("review_date"));
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
				}else if(field.equals("adress")) {
					
					
					try {
						sql="select *from(select row_number() over(order by review_rate desc,ceo_avgrate desc, review_went desc)"
								+"rnum, b.* from search_nyam b where ceo_addr like ?)where rnum>=? and rnum<=?";
						pstmt=con.prepareStatement(sql);
						

						pstmt.setString(1, "%"+keyword+"%");
						pstmt.setInt(2, startNo);
						pstmt.setInt(3, endNo);
						
						rs=pstmt.executeQuery();
						
						while(rs.next()) {
							SearchDTO dto=new SearchDTO();
							dto.setCeo_name(rs.getString("ceo_name"));
							dto.setCeo_addr(rs.getString("ceo_addr"));
							dto.setCeo_avgrate(rs.getString("ceo_avgrate"));
							dto.setReview_ceo_num(rs.getInt("review_ceo_num"));
							dto.setReview_num(rs.getInt("review_num"));
							dto.setReview_title(rs.getString("review_title"));
							dto.setReview_cont(rs.getString("review_cont"));
							dto.setReview_id(rs.getString("review_id"));
							dto.setReview_image(rs.getString("review_image"));
							dto.setReview_rate(rs.getInt("review_rate"));
							dto.setReview_went(rs.getString("review_went"));
							dto.setReview_date(rs.getString("review_date"));
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
				}
		return list;
		
	}
		
		
	
	public double getAvgSelect(int no) {
		double avg = 0,avg2=0;
		
		
		try {
			openConn();
			sql="select avg(review_rate) from review_nyam where review_ceo_num=? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				avg=rs.getDouble(1);
				
			}
			
			sql="select avg(rate_star) from rate_nyam where rate_ceo_num=? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				avg2=rs.getDouble(1);
				
			}
			
			if(avg2>0) {
				avg=(avg+avg2)/2;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConn(rs, pstmt, con);
		}
		
		return avg;
		
	}
	
	/*
	 * public RateDTO findrate(int no) { RateDTO dto = new RateDTO();
	 * 
	 * 
	 * try { openConn(); sql="select * from rate_nyam where rate_ceo_num=?";
	 * pstmt=con.prepareStatement(sql); pstmt.setInt(1, no);
	 * rs=pstmt.executeQuery();
	 * 
	 * if(rs.next()) {
	 * 
	 * 
	 * dto.setRate_ceo_num(rs.getInt("rate_ceo_num"));
	 * dto.setRate_star(rs.getInt("rate_star"));
	 * dto.setRate_id(rs.getString("rate_id"));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { closeConn(rs, pstmt, con); }
	 * 
	 * return dto;
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	public int searchRate(int no, String id) {
		int result=0;
		
		
		
		try {
			openConn();
			
			sql="select * from review_nyam where review_ceo_num=? or review_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else{
				result=-1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}
	
	public int insertRate(RateDTO dto) {
		int result=0;
		
		
		try {
			openConn();
			
			sql="select * from rate_nyam where rate_ceo_num=? and rate_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,dto.getRate_ceo_num());
			pstmt.setString(2, dto.getRate_id());
			
			rs=pstmt.executeQuery();
			
			
			
			if(rs.next()){
				
				result=-1;
				
				
				} else {
					
					sql="select * from review_nyam where review_ceo_num=? and review_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,dto.getRate_ceo_num());
					pstmt.setString(2, dto.getRate_id());
					
					rs=pstmt.executeQuery();
					
					
					if(rs.next()) {
						
					result=-2;
					
					} else {
						
						sql="insert into rate_nyam values(?,?,?)";
						pstmt=con.prepareStatement(sql);
						
						pstmt.setInt(1,dto.getRate_ceo_num());
						pstmt.setInt(2, dto.getRate_star());
						pstmt.setString(3, dto.getRate_id());
						
						/*
						 * dto.setRate_ceo_num(rs.getInt("rate_ceo_num");
						 * dto.setRate_star(rs.getInt("rate_star");
						 * dto.setRate_id(rs.getString("rate_id");
						 */
						result=pstmt.executeUpdate();
					}
					
						
						


			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
				
		return result;
		
	}
	
	 public int likeInsert(int no) {
		 int like=0;
		 
		 
		  try {
			  openConn();
			  sql="update review_nyam set review_like=review_like+1 where review_num = ?";
			pstmt=con.prepareStatement(sql);
			
			  pstmt.setInt(1, no); 
			  pstmt.executeUpdate();
			  
			  sql="select review_like from review_nyam where review_num =?";
			  pstmt=con.prepareStatement(sql); 
			  pstmt.setInt(1, no);
			  rs=pstmt.executeQuery();
			  
			  if(rs.next()) { 
				  like = rs.getInt("review_like");
			  
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			closeConn(rs, pstmt, con);
		}
		
		
		
		 return like ;
		 
	 }
	 
	 public int likeDelete(int no) {
		 int like=0;
		 
		 
		  try {
			  openConn();
			  sql="update review_nyam set review_like=review_like-1 where review_num = ?";
			pstmt=con.prepareStatement(sql);
			
			  pstmt.setInt(1, no); 
			  pstmt.executeUpdate();
			  
			  sql="select review_like from review_nyam where review_num =?";
			  pstmt=con.prepareStatement(sql); 
			  pstmt.setInt(1, no);
			  rs=pstmt.executeQuery();
			  
			  if(rs.next()) { 
				  like = rs.getInt("review_like");
			  
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			closeConn(rs, pstmt, con);
		}
		
		
		
		 return like ;
		 
	 }
	 
	 
	
	
	  public int likeMethod(int no,String id) {
	  
	  int like=0;
	  
	  try {
	  
	  openConn(); 
	  sql="select * from like_nyam where like_review_num=? and like_id=?";
	  pstmt=con.prepareStatement(sql); 
	  pstmt.setInt(1,no); 
	  pstmt.setString(2, id);
	  
	  rs=pstmt.executeQuery();
	  
	  if(rs.next()) { 
		  
		  sql="delete from like_nyam where like_review_num=? and like_id=?";
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1,no); 
		  pstmt.setString(2, id);
		  pstmt.executeUpdate();
		  
		  like=0;
		  
		  
	  
	  } else {
	  
	  sql="insert into like_nyam values(?,?,?)"; 
	  pstmt=con.prepareStatement(sql);
	  pstmt.setInt(1, no); 
	  pstmt.setString(2, id); 
	  pstmt.setInt(3, 1);
	  
	  like=pstmt.executeUpdate();
	  
	  
	  }

	
	  
	  } catch (SQLException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); }finally {
	  
	  closeConn(rs, pstmt, con);
	  }
	  
	  return like;
	  
	  }
	 
		
		
	

}
