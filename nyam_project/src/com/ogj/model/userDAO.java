package com.ogj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class userDAO {

	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null; // SQL문을 저장할 객체.

	// userDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : userDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static userDAO instance = null;

	private userDAO() {
	} // 기본생성자.

	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	// 메서드를 만들어서 여기에 접근하게 해야 함.
	public static userDAO getInstance() {

		if (instance == null) {
			instance = new userDAO();
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
	
	public int signUp(userDTO dto) {  //회원 가입
		int result = 0 , count =0;

		try {
			openConn();
			
			sql="select max(member_num) from member_nyam";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
				
			}
			
			sql="insert into member_nyam values(?,?,?,?,?,?,sysdate,'d','n')";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMember_name());
			pstmt.setString(3, dto.getMember_id());
			pstmt.setString(4, dto.getMember_pwd());
			pstmt.setString(5, dto.getMember_phone());
			pstmt.setString(6, dto.getMember_mail());
			
			result=pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return result;
	}

	public int logincheck(String id, String pwd){
		int result = 0; //아이디 틀림
		

		try {
			openConn();
			sql = "select * from member_nyam where member_id = ?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result = -1; //비밀번호 틀림
				
				if(rs.getString("member_pwd").equals(pwd)) {
					result = 1; //성공 첫로그인 (닉네임 생성 X)
					
					if(!rs.getString("member_nickname").equals("d")) {
						result = 2; // 성공 닉네임 생성 O
					}
					
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
	
	public int join(userDTO dto) {
		int result = 0 , count =0;

		try {
			openConn();
			
			sql="select max(member_num) from member_nyam";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
				 
			}
			
			sql = "select member_id from member_nyam";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("member_id").equals(dto.getMember_id())) {
					result = -1; // 중복된 아이디 있음.
				}
			}
			if (result == 0) {
				//중복된 아이디 없음 회원등록 시작
				sql="insert into member_nyam values(?,?,?,?,?,?,sysdate,'d','')";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, count);
				pstmt.setString(2, dto.getMember_name());
				pstmt.setString(3, dto.getMember_id());
				pstmt.setString(4, dto.getMember_pwd());
				pstmt.setString(5, dto.getMember_phone());
				pstmt.setString(6, dto.getMember_mail());
				
				result=pstmt.executeUpdate();
				
				
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return result;
		
		
		
	}
	
	public String searchUserId(String name , String email) {
		String id = "false";
		System.out.println(name);
		System.out.println(email);
		

		try {
			openConn();
			sql = "select * from  member_nyam where member_name = ? and member_mail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("member_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
			
		}
		
		
		
		return id; 
	}
	public String searchUserPwd(String id , String email) {
		String pwd = "false";
		System.out.println(id);
		System.out.println("pwd"+email);
		

		try {
			openConn();
			sql = "select * from  member_nyam where member_id = ? and member_mail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("member_pwd");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return pwd; 
	}
	
	public int nicknamecheck(String nickname, String id) {
		int result = 0;// 실패
		
		System.out.println(nickname);
		System.out.println(id);
		

		try {
			openConn();
			sql = "select * from member_nyam";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
			if(rs.getString("member_nickname").equals(nickname)) {
				result = -1; // 닉네임 중복
			}
			}
			
			if (result == 0) {
				sql = "update member_nyam set member_nickname = ? where member_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, nickname);
				pstmt.setString(2, id);
				
				result = pstmt.executeUpdate(); // 닉네임 중복아님  닉네임 반영 성공
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		return result;
	}
	
	public userDTO getInfo (String id) {
		userDTO dto = new userDTO();
		
		try {
			openConn();
			sql = "select * from member_nyam where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pwd(rs.getString("member_pwd"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_phone(rs.getString("member_phone"));
				dto.setMember_nickname(rs.getString("member_nickname"));
				dto.setMember_mail(rs.getString("member_mail"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return dto;
	}
	
	public int confirmPwd(String pwd, String id) {
		int result = 0;

		try {
			openConn();
			sql = "select * from member_nyam where member_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
			if(rs.getString("member_pwd").equals(pwd)) {
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
	
	public int updateInfo(String id , String pwd, String phone) {
		int result = 0;

		try {
			
			openConn();
			
			sql="update member_nyam set member_pwd = ? , member_phone = ? where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, phone);
			pstmt.setString(3, id);
			
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
