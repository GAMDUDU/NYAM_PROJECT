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

public class ServiceDAO {

	


	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// BbsDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static ServiceDAO instance = null;
	
	
	private ServiceDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static ServiceDAO getInstance() {
		
		if(instance == null) {
			instance = new ServiceDAO();
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
	
	
	// service_nyam 테이블의 전체 게시물의 수를 확인하는 메서드.
		public int getServiceCount() {
			int count = 0;
			
			try {
				openConn();
				
				sql = "select count(*) from service_nyam order by service_num desc";
				
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
		
		
		//service_nyam 테이블의 전체 게시물을 확인하는 메서드
		public List<ServiceDTO> getServiceList(int page, int rowsize){
			
			List<ServiceDTO> list = new ArrayList<ServiceDTO>();
			
			int startNo = (page*rowsize) - (rowsize-1);
			
			int endNo = (page*rowsize);

			
			try {
				openConn();
				sql = "select * from "
						+ " (select row_number() "
						+ " over(order by service_group desc, service_step) rnum, "
						+ " b.* from service_nyam b) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					ServiceDTO dto = new ServiceDTO();
					
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return list;
			
			
			
		} //getServiceList() 메서드의 end
	
		
		//문의글 상세내역 확인메서드
		public ServiceDTO getServiceCont(int no) {
			
			ServiceDTO dto = new ServiceDTO();

			try {
				
				openConn();
				
				sql = "select * from service_nyam where service_num = ?";
				
				pstmt =con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto.setService_num(rs.getInt("service_num"));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return dto;
			
		}
		
		// 테이블 게시판 답변글의 step을 하나증가시키는 메서드.
		public void replyUpdate(int group, int step) {
			

			
			try {
				openConn();
				
				sql = "update service_nyam set "
						+ " service_step = service_step +1 "
						+ " where service_group=? and service_step > ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, group);
				
				pstmt.setInt(2, step);
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
		}//replyUpdate() 메서드의 end
		
		
		//답변글을 DB에 저장하는 메서드 호출
		public int replyService(ServiceDTO dto) {
			
			int result = 0, count = 0;

			
			try {
				
				openConn();
				
				sql = "select max(service_num) from service_nyam";
				
				pstmt =con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1)+1;
				} //글번호 증가
				
				sql="insert into service_nyam "
						+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				//글번호,작성자,비밀번호,제목,내용,코드,상태,그룹,스텝,인덴트
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, count);
				pstmt.setString(2, "관리자");
				pstmt.setString(3, dto.getService_pwd());
				pstmt.setString(4, dto.getService_title());
				pstmt.setString(5, dto.getService_cont());
				pstmt.setString(6, dto.getService_code());
				pstmt.setString(7, "답변완료");
				pstmt.setInt(8, dto.getService_group());
				pstmt.setInt(9, dto.getService_step()+1);
				pstmt.setInt(10, dto.getService_indent()+1);
				
				result = pstmt.executeUpdate();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return result;
			
		}//replyService() 메서드의 end
		
		
		//검색 메서드 게시물 수를 구하는 메서드
		public int getSearchServiceCount(String field, String keyword){
			
			int count = 0;
			openConn();
			
			if(field.equals("name")) {
				
				try {
					
					
					sql = "select count(*) from service_nyam "
							+ " where service_name like ?";
					
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
				
			}else if(field.equals("member")) {
				try {
					
					
					sql = "select count(*) from service_nyam "
							+ " where service_code like ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, "%"+"사용자"+"%");
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
					}
					rs.close(); pstmt.close(); con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(field.equals("ceo")){
				try {
					
					sql = "select count(*) from service_nyam "
							+ " where service_code like ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, "%"+"사장님"+"%");
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
					}
					rs.close(); pstmt.close(); con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			 }
			}else if(field.equals("review")){
				try {
					
					sql = "select count(*) from service_nyam "
							+ " where service_code like ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, "%"+"리뷰"+"%");
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1); // 첫번째 컬럼의 데이터를 가져온당
					}
					rs.close(); pstmt.close(); con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
			 }
			}else if(field.equals("etc")){
				try {
					
					sql = "select count(*) from service_nyam "
							+ " where service_code like ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, "%"+"기타"+"%");
					
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
			
		}//getSearchServiceCount() 메서드의 end

		//검색메서드
		public List<ServiceDTO> getSearchServiceList(String field, String keyword,int page, int rowsize){
			
			List<ServiceDTO> list = new ArrayList<ServiceDTO>();
			
			int startNo = (page*rowsize) - (rowsize-1);
			
			int endNo = (page*rowsize);
			
			openConn();
			
			if(field.equals("name")) {
				try {
					sql = "select * from "
							+ " (select row_number() "
							+ " over(order by service_num desc) rnum, "
							+ " b.* from service_nyam b where service_name like ?) "
							+ " where rnum >= ? and rnum <= ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					
					rs =pstmt.executeQuery();
					
					while(rs.next()) {
						ServiceDTO dto = new ServiceDTO();
						
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
			}else if(field.equals("member")){
				

				try {
					sql = "select * from "
							+ " (select row_number() "
							+ " over(order by service_group desc, service_step) rnum, "
							+ " b.* from service_nyam b where service_code like ?) "
							+ " where rnum >= ? and rnum <= ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+"사용자"+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						ServiceDTO dto = new ServiceDTO();
						
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
				
			}else if(field.equals("ceo")) {

				try {
					sql = "select * from "
							+ " (select row_number() "
							+ " over(order by service_group desc, service_step) rnum, "
							+ " b.* from service_nyam b where service_code like ?) "
							+ " where rnum >= ? and rnum <= ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+"사장님"+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						ServiceDTO dto = new ServiceDTO();
						
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeConn(rs, pstmt, con);
				}
				}else if(field.equals("review")) {

					try {
						sql = "select * from "
								+ " (select row_number() "
								+ " over(order by service_group desc, service_step) rnum, "
								+ " b.* from service_nyam b where service_code like ?) "
								+ " where rnum >= ? and rnum <= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+"리뷰"+"%");
						pstmt.setInt(2, startNo);
						pstmt.setInt(3, endNo);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							ServiceDTO dto = new ServiceDTO();
							
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						closeConn(rs, pstmt, con);
					}
				}else if(field.equals("etc")) {

					try {
						sql = "select * from "
								+ " (select row_number() "
								+ " over(order by service_group desc, service_step) rnum, "
								+ " b.* from service_nyam b where service_code like ?) "
								+ " where rnum >= ? and rnum <= ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%"+"기타"+"%");
						pstmt.setInt(2, startNo);
						pstmt.setInt(3, endNo);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							ServiceDTO dto = new ServiceDTO();
							
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						closeConn(rs, pstmt, con);
					}
				
				
			}return list;
			
		}//getSearchServiceList()메서드의 end
		
		
		//문의글 삭제 비지니스 로직
		public int ServiceDelete(int no) {
			
			int result = 0;
			try {
				openConn();
				sql="delete from service_nyam where service_num = ?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				
				sql="update service_nyam set service_num=service_num-1 "
						+ " where service_num > ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}return result;
			
			
		}//ServiceDelete()메서드의 end
		
}
