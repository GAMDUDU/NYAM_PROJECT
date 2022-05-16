package com.review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public int getCeoCount() {
		int count=0;
		
		
		try {
			openCon();
			sql="select count(*) from ceo_nyam";
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
	
	
	
	
	public int insertCeo(Ceo_NyamDTO dto) {
		int result=0, count = 0;
		
		
		try {
			openCon();
			sql="select max(ceo_num) from ceo_nyam";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1)+1;
			}
			
			sql="insert into ceo_nyam values(?,?,?,?,?,?,?,?,?,1,?,'',sysdate,'','')";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getCeo_id());
			pstmt.setString(3, dto.getCeo_name());
			pstmt.setString(4, dto.getCeo_pwd());
			pstmt.setString(5, dto.getCeo_phone());
			pstmt.setString(6, dto.getCeo_cont());
			pstmt.setString(7, dto.getCeo_addr());
			pstmt.setString(8, dto.getCeo_image());
			pstmt.setString(9, dto.getCeo_car());
			pstmt.setString(10, dto.getCeo_conum());
			
			result=pstmt.executeUpdate();
			
			

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
		
		
	}
	
	
	public List<Ceo_NyamDTO> getCeoList(int page, int rowsize){
		List<Ceo_NyamDTO> list = new ArrayList<Ceo_NyamDTO>();
		int startNo=(page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		
		
		
		try {
			openCon();
			sql="select*from(select row_number() over(order by ceo_avgrate desc) rnum, b.* from ceo_nyam b) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Ceo_NyamDTO dto=new Ceo_NyamDTO();
				dto.setCeo_num(rs.getInt("ceo_num"));
				dto.setCeo_id(rs.getString("ceo_id"));
				dto.setCeo_name(rs.getString("ceo_name"));
				dto.setCeo_pwd(rs.getString("ceo_pwd"));
				dto.setCeo_phone(rs.getString("ceo_phone"));
				dto.setCeo_cont(rs.getString("ceo_cont"));
				dto.setCeo_addr(rs.getString("ceo_addr"));
				dto.setCeo_image(rs.getString("ceo_image"));
				dto.setCeo_car(rs.getString("ceo_car"));
				dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
				dto.setCeo_conum(rs.getString("ceo_conum"));
				dto.setCeo_mail(rs.getString("ceo_mail"));
				dto.setCeo_date(rs.getString("ceo_date"));
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
		
		return list;
	}
		
public List<Ceo_NyamDTO> getCeoList(){// ceo 전체 리스트 출력 7개만
			List<Ceo_NyamDTO> list = new ArrayList<Ceo_NyamDTO>();
			
			
			
			try {
				openCon();
				sql="select * from ceo_nyam where ROWNUM <= 7 order by ceo_num desc";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Ceo_NyamDTO dto=new Ceo_NyamDTO();
					dto.setCeo_num(rs.getInt("ceo_num"));
					dto.setCeo_id(rs.getString("ceo_id"));
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_pwd(rs.getString("ceo_pwd"));
					dto.setCeo_phone(rs.getString("ceo_phone"));
					dto.setCeo_cont(rs.getString("ceo_cont"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_image(rs.getString("ceo_image"));
					dto.setCeo_car(rs.getString("ceo_car"));
					dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date"));
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
			
			return list;
			
			
		}


public List<Ceo_NyamDTO> getCeoList2(){// ceo 별점 높은순 리스트 출력 7개만
	List<Integer> list = new ArrayList<Integer>();
	List<Ceo_NyamDTO> list2 = new ArrayList<Ceo_NyamDTO>();
	
	
	
	try {
		openCon();
		
		sql = "select rate_ceo_num from rate_nyam group by rate_ceo_num order by sum(rate_star) desc";
		pstmt=con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
				list.add(rs.getInt("rate_ceo_num"));
			}
		
		
		for (int i =0 ; i< list.size(); i++ ) {
			sql="select * from ceo_nyam  where ceo_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, list.get(i));
			System.out.println(list.get(i));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Ceo_NyamDTO dto=new Ceo_NyamDTO();
				dto.setCeo_num(rs.getInt("ceo_num"));
				dto.setCeo_id(rs.getString("ceo_id"));
				dto.setCeo_name(rs.getString("ceo_name"));
				dto.setCeo_pwd(rs.getString("ceo_pwd"));
				dto.setCeo_phone(rs.getString("ceo_phone"));
				dto.setCeo_cont(rs.getString("ceo_cont"));
				dto.setCeo_addr(rs.getString("ceo_addr"));
				dto.setCeo_image(rs.getString("ceo_image"));
				dto.setCeo_car(rs.getString("ceo_car"));
				dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
				dto.setCeo_conum(rs.getString("ceo_conum"));
				dto.setCeo_mail(rs.getString("ceo_mail"));
				dto.setCeo_date(rs.getString("ceo_date"));
				dto.setCeo_lat(rs.getDouble("ceo_lat"));
				dto.setCeo_lng(rs.getDouble("ceo_lng"));
				
				list2.add(dto);
				
			}
		}

		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeConn(rs, pstmt, con);
	}
	
	return list2;
	
	
}
	
	
	
	public int searchListCount(String field, String keyword) {
		int count=0;
		if(field.equals("content")) {
			
			
			try {
				sql="select count(*) from ceo_nyam where ceo_cont like ?";
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
		}else if(field.equals("rate")) {
			try {
				sql="select count(*) from ceo_nyam where ceo_avgrate>=?";
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
			
			
		}
		else if(field.equals("name")) {
			try {
				sql="select count(*) from ceo_nyam where ceo_name like ?";
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
		}else if(field.equals("address")) {
			try {
				sql="select count(*) from ceo_nyam where ceo_addr like ?";
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
	
	public List<Ceo_NyamDTO> searchCeoList(String field, String keyword, int page, int rowsize){
		List<Ceo_NyamDTO> list=new ArrayList<Ceo_NyamDTO>();
		
		int startNo=(page*rowsize)-(rowsize-1);
		int endNo=(page*rowsize);
		openCon();
		
		if(field.equals("content")) {
			
			
			try {
				sql="select *from(select row_number() over(order by ceo_num desc)"
						+"rnum, b.* from ceo_nyam b where ceo_cont like ?)where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				

				pstmt.setString(1, "%"+keyword+"%");
				
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Ceo_NyamDTO dto=new Ceo_NyamDTO();
					dto.setCeo_num(rs.getInt("ceo_num"));
					dto.setCeo_id(rs.getString("ceo_id"));
					dto.setCeo_name(rs.getString("ceo_name"));
					dto.setCeo_pwd(rs.getString("ceo_pwd"));
					dto.setCeo_phone(rs.getString("ceo_phone"));
					dto.setCeo_cont(rs.getString("ceo_cont"));
					dto.setCeo_addr(rs.getString("ceo_addr"));
					dto.setCeo_image(rs.getString("ceo_image"));
					dto.setCeo_car(rs.getString("ceo_car"));
					dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date"));
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
			} else if(field.equals("name")) {
				
				
				try {
					sql="select *from(select row_number() over(order by ceo_num desc)"
							+"rnum, b.* from ceo_nyam b where ceo_name like ?)where rnum>=? and rnum<=?";
					pstmt=con.prepareStatement(sql);
					

					pstmt.setString(1, "%"+keyword+"%");
					
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);
					
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Ceo_NyamDTO dto=new Ceo_NyamDTO();
						dto.setCeo_num(rs.getInt("ceo_num"));
						dto.setCeo_id(rs.getString("ceo_id"));
						dto.setCeo_name(rs.getString("ceo_name"));
						dto.setCeo_pwd(rs.getString("ceo_pwd"));
						dto.setCeo_phone(rs.getString("ceo_phone"));
						dto.setCeo_cont(rs.getString("ceo_cont"));
						dto.setCeo_addr(rs.getString("ceo_addr"));
						dto.setCeo_image(rs.getString("ceo_image"));
						dto.setCeo_car(rs.getString("ceo_car"));
						dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
						dto.setCeo_conum(rs.getString("ceo_conum"));
						dto.setCeo_mail(rs.getString("ceo_mail"));
						dto.setCeo_date(rs.getString("ceo_date"));
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
				} else if(field.equals("adress")) {
					
					
					try {
						sql="select *from(select row_number() over(order by ceo_num desc)"
								+"rnum, b.* from ceo_nyam b where ceo_addr like ?)where rnum>=? and rnum<=?";
						pstmt=con.prepareStatement(sql);
						

						pstmt.setString(1, "%"+keyword+"%");
						
						pstmt.setInt(2, startNo);
						pstmt.setInt(3, endNo);
						
						rs=pstmt.executeQuery();
						
						while(rs.next()) {
							Ceo_NyamDTO dto=new Ceo_NyamDTO();
							dto.setCeo_num(rs.getInt("ceo_num"));
							dto.setCeo_id(rs.getString("ceo_id"));
							dto.setCeo_name(rs.getString("ceo_name"));
							dto.setCeo_pwd(rs.getString("ceo_pwd"));
							dto.setCeo_phone(rs.getString("ceo_phone"));
							dto.setCeo_cont(rs.getString("ceo_cont"));
							dto.setCeo_addr(rs.getString("ceo_addr"));
							dto.setCeo_image(rs.getString("ceo_image"));
							dto.setCeo_car(rs.getString("ceo_car"));
							dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
							dto.setCeo_conum(rs.getString("ceo_conum"));
							dto.setCeo_mail(rs.getString("ceo_mail"));
							dto.setCeo_date(rs.getString("ceo_date"));
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
					} else if(field.equals("rate")) {
						
						
						try {
							sql="select *from(select row_number() over(order by ceo_num desc)"
									+"rnum, b.* from ceo_nyam b where ceo_avgrate>=?)where rnum>=? and rnum<=?";
							pstmt=con.prepareStatement(sql);
							

							pstmt.setInt(1, Integer.parseInt(keyword));
							
							pstmt.setInt(2, startNo);
							pstmt.setInt(3, endNo);
							
							rs=pstmt.executeQuery();
							
							while(rs.next()) {
								Ceo_NyamDTO dto=new Ceo_NyamDTO();
								dto.setCeo_num(rs.getInt("ceo_num"));
								dto.setCeo_id(rs.getString("ceo_id"));
								dto.setCeo_name(rs.getString("ceo_name"));
								dto.setCeo_pwd(rs.getString("ceo_pwd"));
								dto.setCeo_phone(rs.getString("ceo_phone"));
								dto.setCeo_cont(rs.getString("ceo_cont"));
								dto.setCeo_addr(rs.getString("ceo_addr"));
								dto.setCeo_image(rs.getString("ceo_image"));
								dto.setCeo_car(rs.getString("ceo_car"));
								dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
								dto.setCeo_conum(rs.getString("ceo_conum"));
								dto.setCeo_mail(rs.getString("ceo_mail"));
								dto.setCeo_date(rs.getString("ceo_date"));
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
						} 
		
		return list;
		
	}
	
	
	public Ceo_NyamDTO ceoContent(int no) {
		Ceo_NyamDTO dto = new Ceo_NyamDTO();
		
		
			
			try {
				openCon();
				
				sql="select* from ceo_nyam where ceo_num=?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				rs=pstmt.executeQuery();
				
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
					dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
					dto.setCeo_conum(rs.getString("ceo_conum"));
					dto.setCeo_mail(rs.getString("ceo_mail"));
					dto.setCeo_date(rs.getString("ceo_date"));
					dto.setCeo_lat(rs.getDouble("ceo_lat"));
					dto.setCeo_lng(rs.getDouble("ceo_lng"));
					
					
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
			
		
		return dto;
		
	}
	
	
	
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
				dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
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
				dto.setCeo_avgrate(rs.getDouble("ceo_avgrate"));
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
					sql = "update ceo_nyam set ceo_cont = ?, ceo_phone = ?, ceo_addr = ?, ceo_car = ? where ceo_num = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getCeo_cont());
					pstmt.setString(2, dto.getCeo_phone());
					pstmt.setString(3, dto.getCeo_addr());
					pstmt.setString(4, dto.getCeo_car());
					pstmt.setInt(5, dto.getCeo_num());
					
					result = pstmt.executeUpdate();
				}else {	//가게이름이 다른경우
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
	
	
	public void getInsertAvg(String rate, int cnum) {
		
		try {
			openCon();
			sql="update ceo_nyam set ceo_avgrate =? where ceo_num=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rate);
			pstmt.setInt(2, cnum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		
		
	}
	
	
}