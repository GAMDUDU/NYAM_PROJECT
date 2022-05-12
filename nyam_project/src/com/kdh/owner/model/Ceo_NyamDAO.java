package com.kdh.owner.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Ceo_NyamDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	//싱글턴 방식
	//instance : 참조변수(주소값)
	private static Ceo_NyamDAO instance;
	
	public Ceo_NyamDAO() {
	}

	//기본생성자 대신 싱글턴 객체를 return해주는 getInstance()라는 메소드를 만들어서 그 메소드에 외부에서 접근할수 있도록 함
	//어디서 생성하든 항상 동일한 주소값
	public static Ceo_NyamDAO getInstance() {
		if (instance == null) {
			instance = new Ceo_NyamDAO();
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
	
	//아이디로 DB접근
	public Ceo_NyamDTO getCeo(String id) {
		
		Ceo_NyamDTO dto = new Ceo_NyamDTO();
		
		try {
			openCon();
			
			sql = "select * from ceo_nyam where ceo_id = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
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
				dto.setCeo_date(rs.getString("ceo_date"));
				dto.setCeo_lat(rs.getDouble("ceo_lat"));
				dto.setCeo_lng(rs.getDouble("ceo_lng"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	//NUM으로 DB접근
	public Ceo_NyamDTO getCeo(int no) {
		Ceo_NyamDTO dto = new Ceo_NyamDTO();
		
		try {
			openCon();
			
			sql = "select * from ceo_nyam where ceo_num = ?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
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
				dto.setCeo_date(rs.getString("ceo_date"));
				dto.setCeo_lat(rs.getDouble("ceo_lat"));
				dto.setCeo_lng(rs.getDouble("ceo_lng"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	//update하는 메소드(사장님 가게글 작성)
	public int updateCeo(Ceo_NyamDTO dto) {
		int result = 0;
		
		try {
			openCon();
			
			sql = "select * from ceo_nyam where ceo_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getCeo_num());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (dto.getCeo_num() == (rs.getInt("ceo_num"))) {
					sql = "update ceo_nyam set ceo_cont = ?, ceo_phone = ?, ceo_addr = ?, ceo_image = ?, ceo_car = ?, ceo_lat = ?, ceo_lng = ? where ceo_num = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getCeo_cont());
					pstmt.setString(2, dto.getCeo_phone());
					pstmt.setString(3, dto.getCeo_addr());
					pstmt.setString(4, dto.getCeo_image());
					pstmt.setString(5, dto.getCeo_car());
					pstmt.setDouble(6, dto.getCeo_lat());
					pstmt.setDouble(7, dto.getCeo_lng());
					pstmt.setInt(8, dto.getCeo_num());
					
					result = pstmt.executeUpdate();
				}else {	//가게번호가 다른경우
					result = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
}
