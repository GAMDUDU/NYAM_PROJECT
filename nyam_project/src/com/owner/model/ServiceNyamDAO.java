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

public class ServiceNyamDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	//싱글턴 방식
	//instance : 참조변수(주소값)
	private static ServiceNyamDAO instance;
	
	public ServiceNyamDAO() {
	}

	//기본생성자 대신 싱글턴 객체를 return해주는 getInstance()라는 메소드를 만들어서 그 메소드에 외부에서 접근할수 있도록 함
	//어디서 생성하든 항상 동일한 주소값
	public static ServiceNyamDAO getInstance() {
		if (instance == null) {
			instance = new ServiceNyamDAO();
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
	
	//아이디에 해당하는 전체 문의 수를 확인하는 메소드
	public int getServiceCount(String id) {
		int count = 0;
		
		try {
			openCon();
			
			sql = "select count(*) from service_nyam where service_name = ?";
		
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
	
	//테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메소드
	public List<ServiceNyamDTO> getServiceList(int page, int rowsize, String id) {
		List<ServiceNyamDTO> list = new ArrayList<ServiceNyamDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 끝번호
		int endNo = (page * rowsize);
		
		try {
			openCon();
			
			sql = "select * from (select row_number() over(order by service_num desc) rnum, b.* from service_nyam b) where (rnum >= ? and rnum <= ?) and service_name = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			pstmt.setString(3, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ServiceNyamDTO dto = new ServiceNyamDTO();
				
				dto.setService_num(rs.getInt("service_num"));
				dto.setService_name(rs.getString("service_name"));
				dto.setService_pwd(rs.getString("service_pwd"));
				dto.setService_title(rs.getString("service_title"));
				dto.setService_cont(rs.getString("service_cont"));
				dto.setService_code(rs.getString("service_code"));
				dto.setService_check(rs.getString("service_check"));
				dto.setService_group(rs.getInt("service_group"));
				dto.setService_step(rs.getInt("service_step"));
				dto.setService_indent(rs.getInt("service_indent"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}	

	//고객센터에 문의글 작성
	public int insertService(ServiceNyamDTO dto) {
		int result = 0, count = 0;
		
		try {
			openCon();
		
			sql = "select max(service_num) from service_nyam";
		
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into service_nyam values(?, ?, ?, ?, ?, '사장님문의', '확인중', ?, 0, 0)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getService_name());
			pstmt.setString(3, dto.getService_pwd());
			pstmt.setString(4, dto.getService_title());
			pstmt.setString(5, dto.getService_cont());
			pstmt.setInt(6, count);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result; 
	}
	public int insertService2(ServiceNyamDTO dto) {
		int result = 0, count = 0;
		
		try {
			openCon();
		
			sql = "select max(service_num) from service_nyam";
		
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into service_nyam values(?, ?, ?, ?, ?, '회원 문의', '확인중', ?, 0, 0)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getService_name());
			pstmt.setString(3, dto.getService_pwd());
			pstmt.setString(4, dto.getService_title());
			pstmt.setString(5, dto.getService_cont());
			pstmt.setInt(6, count);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result; 
	}
	
	//글 내용 가져오는 메소드
	public ServiceNyamDTO getCont(int no) {
		ServiceNyamDTO dto = new ServiceNyamDTO();
		
		try {
			openCon();
		
			sql = "select * from service_nyam where service_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setService_num(rs.getInt("service_num"));
				dto.setService_name(rs.getString("service_name"));
				dto.setService_pwd(rs.getString("service_pwd"));
				dto.setService_title(rs.getString("service_title"));
				dto.setService_cont(rs.getString("service_cont"));
				dto.setService_code(rs.getString("service_code"));
				dto.setService_check(rs.getString("service_check"));
				dto.setService_group(rs.getInt("service_group"));
				dto.setService_step(rs.getInt("service_step"));
				dto.setService_indent(rs.getInt("service_indent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	// 검색어에 해당하는 게시물의 수를 조회하는 메소드
	public int searchListCount(String field, String keyword) {
		int count = 0;
		
		openCon();
		
		if (field.equals("title")) {
			try {
				sql = "select count(*) from service_nyam where service_title like ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");

				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (field.equals("content")) {
			try {
				sql = "select count(*) from service_nyam where service_cont like ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");

				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				sql = "select count(*) from service_nyam where service_title like ? or service_cont like ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		} 
		return count;
	}	//searchListCount() 끝
	
	//테이블에서 검색한 내용을 가지고 페이징 처리를 하는 메소드
	public List<ServiceNyamDTO> searchBoardList(String field, String keyword, int page, int rowsize) {
		List<ServiceNyamDTO> list = new ArrayList<ServiceNyamDTO>();
		
		//해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		//해당 페이지에서 마지막번호
		int endNo = (page * rowsize);
		
		openCon();
		
		if (field.equals("title")) {
			try {
				sql = "select * from (select row_number() over(order by service_num desc) rnum, b.* from service_nyam b where service_title like ?) where rnum >= ? and rnum <= ?";
			
				pstmt = con.prepareStatement(sql);
			
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ServiceNyamDTO dto = new ServiceNyamDTO();
					
					dto.setService_num(rs.getInt("service_num"));
					dto.setService_name(rs.getString("service_name"));
					dto.setService_pwd(rs.getString("service_pwd"));
					dto.setService_title(rs.getString("service_title"));
					dto.setService_cont(rs.getString("service_cont"));
					dto.setService_code(rs.getString("service_code"));
					dto.setService_check(rs.getString("service_check"));
					dto.setService_group(rs.getInt("service_group"));
					dto.setService_step(rs.getInt("service_step"));
					dto.setService_indent(rs.getInt("service_indent"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (field.equals("content")) {
			try {
				sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_cont like ?) where rnum >= ? and rnum <= ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ServiceNyamDTO dto = new ServiceNyamDTO();
					
					dto.setService_num(rs.getInt("service_num"));
					dto.setService_name(rs.getString("service_name"));
					dto.setService_pwd(rs.getString("service_pwd"));
					dto.setService_title(rs.getString("service_title"));
					dto.setService_cont(rs.getString("service_cont"));
					dto.setService_code(rs.getString("service_code"));
					dto.setService_check(rs.getString("service_check"));
					dto.setService_group(rs.getInt("service_group"));
					dto.setService_step(rs.getInt("service_step"));
					dto.setService_indent(rs.getInt("service_indent"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ? or board_cont like ?) where rnum >= ? and rnum <= ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ServiceNyamDTO dto = new ServiceNyamDTO();
					
					dto.setService_num(rs.getInt("service_num"));
					dto.setService_name(rs.getString("service_name"));
					dto.setService_pwd(rs.getString("service_pwd"));
					dto.setService_title(rs.getString("service_title"));
					dto.setService_cont(rs.getString("service_cont"));
					dto.setService_code(rs.getString("service_code"));
					dto.setService_check(rs.getString("service_check"));
					dto.setService_group(rs.getInt("service_group"));
					dto.setService_step(rs.getInt("service_step"));
					dto.setService_indent(rs.getInt("service_indent"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}
		return list;
	}	//searchBoardList() 끝
	
	//문의글 수정 메소드
	public int updateService(int no, String title, String cont) {
		int result = 0;
		
		try {
			openCon();
		
			sql = "update service_nyam set service_title = ?, service_cont = ? where service_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, cont);
			pstmt.setInt(3, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	//문의글 삭제 메소드
	public int deleteService(int no) {
		int result = 0;
		
		try {
			openCon();
		
			sql = "delete from service_nyam where service_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			sql = "update service_nyam set service_num = service_num - 1 where service_num > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	//최신 글번호 가져오는 메소드
		public int countService() {
			int count = 0;
			
			try {
				openCon();
			
				sql = "select max(service_num) from service_nyam";
			
				pstmt = con.prepareStatement(sql);

				pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return count; 
		}
		
}
