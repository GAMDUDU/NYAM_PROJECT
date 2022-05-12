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


public class AdminDAO {
	


	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static AdminDAO instance = null;
	
	
	private AdminDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static AdminDAO getInstance() {
		
		if(instance == null) {
			instance = new AdminDAO();
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
	
	//이거 멤버에서 함...
	// 전체 회원 목록을 가져오는 메서드
	public List<MemberDTO> getMemberList(){
		
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	

	
	try {
		openConn();
		
		sql= "select * from member_nyam order by member_num desc";
		
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			MemberDTO dto = new MemberDTO();
			
			dto.setMember_num(rs.getInt("member_num"));
			dto.setMember_name(rs.getString("member_name"));
			dto.setMember_id(rs.getString("member_id"));
			dto.setMember_pwd(rs.getString("member_pwd"));
			dto.setMember_phone(rs.getString("member_phone"));
			dto.setMember_mail(rs.getString("member_mail"));
			dto.setMember_date(rs.getString("member_date").substring(0,10));
			dto.setMember_nickname(rs.getString("mamber_nickname"));
			
			
			list.add(dto);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeConn(rs, pstmt, con);
	}return list;
	
		
		
	}//getMemberList()메서드의 end

}
