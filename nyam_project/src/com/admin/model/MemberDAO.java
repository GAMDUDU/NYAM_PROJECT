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

public class MemberDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static MemberDAO instance = null;
	
	private MemberDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static MemberDAO getInstance() {
		
		if(instance == null) {
			instance = new MemberDAO();
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
	
	
	// member_nyam 테이블의 전체 게시물의 수를 확인하는 메서드.
	public int getMemberCount() {
		
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from member_nyam order by member_num desc";
			
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
		
	} //getBoardCount() 메서드의 end
	
	
	// 전체 회원 목록을 가져오는 메서드
	public List<MemberDTO> getMemberList(int page, int rowsize){
		
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	
	int startNo = (page*rowsize) - (rowsize-1);
	
	int endNo = (page*rowsize);

	
	try {
		openConn();
		//매개변수 넘겨서 로우값
		
		sql = "select * from "
				+ " (select row_number() "
				+ " over(order by member_num desc) rnum, "
				+ " b.* from member_nyam b) "
				+ " where rnum >= ? and rnum <= ?";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, startNo);
		
		pstmt.setInt(2, endNo);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			MemberDTO dto = new MemberDTO();
			
			dto.setMember_num(rs.getInt("member_num"));
			dto.setMember_name(rs.getString("member_name"));
			dto.setMember_id(rs.getString("member_id"));
			dto.setMember_pwd(rs.getString("member_pwd"));
			dto.setMember_phone(rs.getString("member_phone"));
			dto.setMember_mail(rs.getString("member_mail"));
			dto.setMember_date(rs.getString("member_date").substring(0, 10));
			dto.setMember_nickname(rs.getString("member_nickname"));
			dto.setMember_block(rs.getString("member_block"));
			
			list.add(dto);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeConn(rs, pstmt, con);
	}return list;
	
	}//getMemberList()메서드의 end
	
	
	
	//회원정보 상세내역 메서드
	public MemberDTO getMemberCont(int no) {
		
		MemberDTO dto = new MemberDTO();
		
		try {
			
			openConn();
			
			sql = "select * from member_nyam where member_num = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setMember_num(rs.getInt("member_num"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pwd(rs.getString("member_pwd"));
				dto.setMember_phone(rs.getString("member_phone"));
				dto.setMember_mail(rs.getString("member_mail"));
				dto.setMember_date(rs.getString("member_date").substring(0, 10));
				dto.setMember_nickname(rs.getString("member_nickname"));
				dto.setMember_block(rs.getString("member_block"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
		
	}// getMemberCont() 메서드의 end
	
	
	//회원 정보를 수정하는 메서드
	public int MemberUpdate(MemberDTO dto) {
		
		int result = 0;
		

		
		try {
			openConn();
			
			sql="update member_nyam set member_phone=?, "
					+ " member_mail = ?, member_nickname=?, "
					+ " member_block = ? "
					+ " where member_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMember_phone());
			pstmt.setString(2, dto.getMember_mail());
			pstmt.setString(3, dto.getMember_nickname());
			pstmt.setString(4, dto.getMember_block());
			pstmt.setInt(5, dto.getMember_num());

			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return result;
		
		
		
	}//MemberUpdate() 메서드의 end
	
	//회원정보를 삭제하는 비지니스 로직
	public int MemberDelete(int no) {
		
		int result = 0;
		try {
			openConn();
			sql="delete from member_nyam where member_num = ?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			sql="update member_nyam set member_num=member_num-1 "
					+ " where member_num > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}return result;
		
		
	}// MemberDelete() 메서드의 end
	
	
	//검색어에 해당하는 게시물의 수를 구하는 메서드
	public int getSearchMemberCount(String field, String keyword) {
		
		int count = 0;
		openConn();
		
		if(field.equals("nickname")) {
			
			try {
				
				
				sql = "select count(*) from member_nyam "
						+ " where member_nickname like ?";
				
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
			
		}else if(field.equals("name")) {
			try {
				
				
				sql = "select count(*) from member_nyam "
						+ " where member_name like ?";
				
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
		}else if(field.equals("id")){
			try {
				
				sql = "select count(*) from member_nyam "
						+ " where member_id like ?";
				
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
		
		
	}//getSearchMemberCount()메서드의 end
	
	
	
	//검색메서드
	public List<MemberDTO> getSearchMemberList(String field, String keyword,int page, int rowsize){
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		int startNo = (page*rowsize) - (rowsize-1);
		
		int endNo = (page*rowsize);
		
		openConn();
		
		if(field.equals("name")) {
			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by member_num desc) rnum, "
						+ " b.* from member_nyam b where member_name like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs =pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					
					dto.setMember_num(rs.getInt("member_num"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setMember_pwd(rs.getString("member_pwd"));
					dto.setMember_phone(rs.getString("member_phone"));
					dto.setMember_mail(rs.getString("member_mail"));
					dto.setMember_date(rs.getString("member_date").substring(0, 10));
					dto.setMember_nickname(rs.getString("member_nickname"));
					
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
						+ " b.* from member_nyam b where member_id like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					
					dto.setMember_num(rs.getInt("member_num"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setMember_pwd(rs.getString("member_pwd"));
					dto.setMember_phone(rs.getString("member_phone"));
					dto.setMember_mail(rs.getString("member_mail"));
					dto.setMember_date(rs.getString("member_date").substring(0, 10));
					dto.setMember_nickname(rs.getString("member_nickname"));
					
					list.add(dto);
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		}else if(field.equals("nickname")) {

			try {
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by member_num desc) rnum, "
						+ " b.* from member_nyam b where member_nickname like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					
					dto.setMember_num(rs.getInt("member_num"));
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_id(rs.getString("member_id"));
					dto.setMember_pwd(rs.getString("member_pwd"));
					dto.setMember_phone(rs.getString("member_phone"));
					dto.setMember_mail(rs.getString("member_mail"));
					dto.setMember_date(rs.getString("member_date").substring(0, 10));
					dto.setMember_nickname(rs.getString("member_nickname"));
					
					list.add(dto);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		}return list;
	}//getSearchMemberList()메서드의 end

}


