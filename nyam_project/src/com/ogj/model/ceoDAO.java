package com.ogj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ceoDAO {
	
	

	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null; // SQL문을 저장할 객체.

	// ceoDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : ceoDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static ceoDAO instance = null;

	private ceoDAO() {
	} // 기본생성자.

	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	// 메서드를 만들어서 여기에 접근하게 해야 함.
	public static ceoDAO getInstance() {

		if (instance == null) {
			instance = new ceoDAO();
		}
		return instance;

	} // getInstance() 메서드 end

	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();

			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
			

			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // openConn() 메서드 end

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int join (ceoDTO dto) {
		int result = 0;
		int count = 0;
		

		
		try {
			openConn();
			sql = "select max(ceo_num) from ceo_nyam";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "select * from ceo_nyam";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("ceo_id").equals(dto.getCeo_id())) {
					result = -1; // 아이디 중복
				}else if (rs.getString("ceo_mail").equals(dto.getCeo_mail())){
					result = -2; // 이메일 중복
				}
			}
			
			 if (result == 0){
				sql = "insert into ceo_nyam values(?,?,?,?,?,'','','','','',?,?,sysdate,'','')";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, count);
				pstmt.setString(2, dto.getCeo_id());
				pstmt.setString(3, dto.getCeo_name());
				pstmt.setString(4, dto.getCeo_pwd());
				pstmt.setString(5, dto.getCeo_phone());
				pstmt.setString(6, dto.getCeo_conum());
				pstmt.setString(7, dto.getCeo_mail());
				
				result = pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return result;
	}
	
	public String searchCeoId(String name , String email) {
		String id = "false";
		System.out.println(name);
		System.out.println(email);
		

		try {
			openConn();
			sql = "select * from  ceo_nyam where ceo_name = ? and ceo_mail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("ceo_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return id; 
	}
	public String searchCeoPwd(String id , String email) {
		String pwd = "false";
		System.out.println(id);
		System.out.println("pwd"+email);
		

		try {
			openConn();
			sql = "select * from  ceo_nyam where ceo_id = ? and ceo_mail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("ceo_pwd");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return pwd; 
	}
	
	public int logincheck(String id, String pwd){
		int result = 0; //아이디 틀림
		

		try {
			openConn();
			sql = "select * from ceo_nyam where ceo_id = ?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result = -1; //비밀번호 틀림
				
				if(rs.getString("ceo_pwd").equals(pwd)) {
					result = 1; //성공
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return result;
	}
	
	public int confirmPwd(String pwd, String id) {
		int result = 0;

		try {
			openConn();
			sql = "select * from ceo_nyam where ceo_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
			if(rs.getString("ceo_pwd").equals(pwd)) {
				result = 1;  // 아이디 맞음
			}else {
				result = -1;
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
	
	public ceoDTO getInfo (String id) {
		ceoDTO dto = new ceoDTO();
		
		System.out.println("getinfo 접근");
		try {
			openConn();
			sql = "select * from ceo_nyam where ceo_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dto.setCeo_id(rs.getString("ceo_id"));
				dto.setCeo_name(rs.getString("ceo_name"));
				dto.setCeo_conum(rs.getString("ceo_conum"));
				dto.setCeo_mail(rs.getString("ceo_mail"));
				dto.setCeo_phone(rs.getString("ceo_phone"));
				dto.setCeo_num(rs.getInt("ceo_num"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return dto;
	}
	
	public int updateInfo(String id , String pwd, String phone , String conum, String name) {
		int result = 0;

		try {
			
			openConn();
			
			sql="update ceo_nyam set ceo_pwd = ? , ceo_phone = ?, ceo_conum = ? , ceo_name = ?  where ceo_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, phone);
			pstmt.setString(3, conum);
			pstmt.setString(4, name);
			pstmt.setString(5, id);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return result;
	}


}
