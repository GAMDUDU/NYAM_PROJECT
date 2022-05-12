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

public class CeoDAO {
	
	
	
	
	


	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static CeoDAO instance = null;
	
	
	private CeoDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static CeoDAO getInstance() {
		
		if(instance == null) {
			instance = new CeoDAO();
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
	
	// 가게(ceo)전체 리스트를 조회하는 메서드
	public List<CeoDTO> getCeoList(int page, int rowsize){
		
		List<CeoDTO> list = new ArrayList<CeoDTO>();
		int startNo = (page*rowsize) - (rowsize-1);
		int endNo = (page*rowsize);
		
		try {
			openConn();
			
			sql = "select * from "
					+ " (select row_number() "
					+ " over(order by ceo_num desc) rnum, "
					+ " b.* from ceo_nyam b) "
					+ " where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CeoDTO dto = new CeoDTO();
				
				dto.setCeo_num(rs.getInt("ceo_num"));
				dto.setCeo_id(rs.getString("ceo_id"));
				dto.setCeo_name(rs.getString("ceo_name"));
				dto.setCeo_pwd(rs.getString("ceo_pwd"));
				dto.setCeo_phone(rs.getString("ceo_phone"));
				dto.setCeo_cont(rs.getString("ceo_cont"));
				dto.setCeo_addr(rs.getString("ceo_addr"));
				dto.setCeo_image(rs.getString("ceo_image"));
				dto.setCeo_car(rs.getString("ceo_car"));
				dto.setCeo_avgrate(rs.getInt("ceo_avgrate"));
				dto.setCeo_conum(rs.getString("ceo_conum"));
				dto.setCeo_mail(rs.getString("ceo_mail"));
				dto.setCeo_date(rs.getString("ceo_date").substring(0, 10));
				dto.setCeo_lat(rs.getDouble("ceo_lat"));
				dto.setCeo_lng(rs.getDouble("ceo_lng"));
				
				list.add(dto);
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		} return list;
		
		
		
	}//getCeoList()메서드의 end
	
	// ceo_nyam 테이블의 전체 게시물의 수를 확인하는 메서드.
	public int getCeoCount() {
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from ceo_nyam order by ceo_num desc";
			
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

	
	// ceo 상세내역을 확인하는 메서드
	public CeoDTO getCeoCont(int num) {
		
		CeoDTO dto = new CeoDTO();
		

		
		
		try {
			openConn();
			
			sql="select * from ceo_nyam where ceo_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setCeo_num(rs.getInt("ceo_num"));
				dto.setCeo_id(rs.getString("ceo_id"));
				dto.setCeo_name(rs.getString("ceo_name"));
				dto.setCeo_pwd(rs.getString("ceo_pwd"));
				dto.setCeo_phone(rs.getString("ceo_phone"));
				dto.setCeo_cont(rs.getString("ceo_cont"));
				dto.setCeo_addr(rs.getString("ceo_addr"));
				dto.setCeo_image(rs.getString("ceo_image"));
				dto.setCeo_car(rs.getString("ceo_car"));
				dto.setCeo_avgrate(rs.getInt("ceo_avgrate"));
				dto.setCeo_conum(rs.getString("ceo_conum"));
				dto.setCeo_mail(rs.getString("ceo_mail"));
				dto.setCeo_date(rs.getString("ceo_date").substring(0, 10));
				dto.setCeo_lat(rs.getDouble("ceo_lat"));
				dto.setCeo_lng(rs.getDouble("ceo_lng"));
				
			}


			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return dto;
		
		
		
	} //getCeoCont()메서드의 end
	
	
	// 가게 정보를 업데이트 하는 메서드
	public int CeoUpdate(CeoDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql ="update ceo_nyam set ceo_name=?, "
					+ " ceo_pwd =?, ceo_phone=?, ceo_addr=?, "
					+ " ceo_car=?, ceo_conum=?, ceo_cont=?, "
					+ " ceo_image=? "
					+ " where ceo_num=?";
			

			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCeo_name());
			pstmt.setString(2, dto.getCeo_pwd());
			pstmt.setString(3, dto.getCeo_phone());
			pstmt.setString(4, dto.getCeo_addr());
			pstmt.setString(5, dto.getCeo_car());
			pstmt.setString(6, dto.getCeo_conum());
			pstmt.setString(7, dto.getCeo_cont());
			pstmt.setString(8, dto.getCeo_image());
			
			pstmt.setInt(9, dto.getCeo_num());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return result;
		
		
	}//CeoUpdate() 메서드의 end
	
	// ceo 테이블 삭제 메서드
	public int CeoDelete(int no) {
		int result = 0;
		try {
			openConn();
			
			sql="delete from ceo_nyam where ceo_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			sql="update ceo_nyam set ceo_num = ceo_num-1 "
					+ " where ceo_num > ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return result;
		
	}//CeoDelete() 메서드의 end
	
	//검색어에 해당하는 게시불의 수를 구하는 메서드
	
	public int getSearchCeoCount(String field, String keyword) {
		
		int count = 0;
		openConn();
		
		if(field.equals("name")) {
			
			try {
				
				
				sql = "select count(*) from ceo_nyam "
						+ " where ceo_name like ?";
				
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
			
		}else if(field.equals("id")) {
			try {
				
				
				sql = "select count(*) from ceo_nyam "
						+ " where ceo_id like ?";
				
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
		}else if(field.equals("addr")){
			try {
				
				sql = "select count(*) from ceo_nyam "
						+ " where ceo_addr like ?";
				
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
		
		
	}//getSearchCeoCount() 메서드의 end
	
	//검색리스트 메서드
	public List<CeoDTO> getSearchCeoList(String field, String keyword,int page, int rowsize) {
		
		List<CeoDTO> list = new ArrayList<CeoDTO>();
		
	int startNo = (page*rowsize) - (rowsize-1);
		
		int endNo = (page*rowsize);
		
		openConn();
		
		if(field.equals("name")) {
			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by ceo_num desc) rnum, "
						+ " b.* from ceo_nyam b where ceo_name like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs =pstmt.executeQuery();
				
				while(rs.next()) {
					
					CeoDTO dto = new CeoDTO();
					
					dto.setCeo_num(rs.getInt("ceo_num"));
					dto.setCeo_id(rs.getString("ceo_id"));
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_pwd(rs.getString("ceo_pwd"));
					dto.setCeo_phone(rs.getString("ceo_phone"));
					dto.setCeo_cont(rs.getString("ceo_cont"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_image(rs.getString("ceo_image"));
					dto.setCeo_car(rs.getString("ceo_car"));
					dto.setCeo_avgrate(rs.getInt("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date").substring(0, 10));
					dto.setCeo_lat(rs.getDouble("ceo_lat"));
					dto.setCeo_lng(rs.getDouble("ceo_lng"));
					
					list.add(dto);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
		}else if(field.equals("id")){
			

			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by member_num desc) rnum, "
						+ " b.* from ceo_nyam b where ceo_id like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					CeoDTO dto = new CeoDTO();
					
					dto.setCeo_num(rs.getInt("ceo_num"));
					dto.setCeo_id(rs.getString("ceo_id"));
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_pwd(rs.getString("ceo_pwd"));
					dto.setCeo_phone(rs.getString("ceo_phone"));
					dto.setCeo_cont(rs.getString("ceo_cont"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_image(rs.getString("ceo_image"));
					dto.setCeo_car(rs.getString("ceo_car"));
					dto.setCeo_avgrate(rs.getInt("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date").substring(0, 10));
					dto.setCeo_lat(rs.getDouble("ceo_lat"));
					dto.setCeo_lng(rs.getDouble("ceo_lng"));
					
					list.add(dto);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		}else if(field.equals("addr")) {

			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by ceo_num desc) rnum, "
						+ " b.* from ceo_nyam b where ceo_addr like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					CeoDTO dto = new CeoDTO();
					
					dto.setCeo_num(rs.getInt("ceo_num"));
					dto.setCeo_id(rs.getString("ceo_id"));
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_pwd(rs.getString("ceo_pwd"));
					dto.setCeo_phone(rs.getString("ceo_phone"));
					dto.setCeo_cont(rs.getString("ceo_cont"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_image(rs.getString("ceo_image"));
					dto.setCeo_car(rs.getString("ceo_car"));
					dto.setCeo_avgrate(rs.getInt("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date").substring(0, 10));
					dto.setCeo_lat(rs.getDouble("ceo_lat"));
					dto.setCeo_lng(rs.getDouble("ceo_lng"));
					
					list.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		}return list;
		
		
	}//getSearchCeoList() 메서드의 end
	
}
