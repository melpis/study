package com.github.melpis.member.menager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.melpis.member.vo.MemberVO;

public class MemberManager {
	// 데이터 정의
	private final String DB_DRIVER ="org.postgresql.Driver";
	private final String DB_URI="jdbc:postgresql://127.0.0.1:5432/postgres";
	private final String DB_USER_ID="postgres";
	private final String DB_USER_PW="postgres";
	// 생성자
	public MemberManager(){
		
	}
	
	
	private Connection getConnection()
	{
		Connection conn = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URI,DB_USER_ID,DB_USER_PW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//처리
	public MemberVO registMemeber(MemberVO paramMember){
		MemberVO returnMember = null;
		//1. 데이터 추출
		if(paramMember == null)
		{ 
			
		}
		//2. 유효성 검사
		if(paramMember.getUserId() == null)
		{
			
		}
		if(paramMember.getUserPw() == null){
			
		}
		if(paramMember.getUserName() == null){
			
		}
		//3. 처리
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn= getConnection();
			
			String sql="";
			sql += " select  ";
			sql += " USER_ID ";
			sql += " from ";
			sql += " TB_MEMBER ";
			sql += " where ";
			sql += " USER_ID = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMember.getUserId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				paramMember.getUserId().equals(rs.getString("USER_ID"));
				
			}
			
			rs = null;
			pstmt = null;
			conn  = null;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		try{
			conn= getConnection();
			
			String sql="";
			sql += " insert  ";
			sql += " into ";
			sql += " TB_MEMBER ";
			sql += " (USER_ID, USER_PW, USER_NAME, REGIST_DATE, LAST_VISITED_DATE ) ";
			sql += " valuse ";
			sql += " (?,?,?, now(),now()) ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMember.getUserId());
			pstmt.setString(2, paramMember.getUserPw());
			pstmt.setString(3, paramMember.getUserName());
			
			int affectRowCount = pstmt.executeUpdate();
			
			System.out.println("r :"+affectRowCount);
			pstmt = null;
			conn  = null;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		returnMember = getMember(paramMember.getUserId());
		
		
		//4. 결과 출력
		return returnMember;
	}
	public void checkId(MemberVO memberVO) throws Exception {
		//1. 데이터 추출
		if(memberVO == null){
			throw new NullPointerException("memberVO null");
		}
		//2. 유효성 검사
		if(memberVO.getUserId().length() > 12){
			throw new Exception("아이디가 잘못되었습니다");
		}
		if(memberVO.getUserPw().length() > 12){
			throw new Exception("패스워드가 잘못되었습니다");
		}
		//3. 처리
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userPw= "";
		try{
			conn = getConnection();
			
			String sql ="";
			sql += " select USER_PW ";
			sql += " from ";
			sql += " TB_MEMBER ";
			sql += " where ";
			sql += " USER_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getUserId());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				userPw=rs.getString("USER_PW");
			}
			rs = null;
			pstmt = null;
			conn  = null;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!userPw.equals(memberVO.getUserPw())){
			throw new Exception("패스워드가 일치 하지 않아요 ");
		}
		
		
		//4. 결과 출력
		
		
	}


	public MemberVO getMember(String sessionUserId) {
		MemberVO returnMember = null;
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try{
			conn= getConnection();
			
			String sql="";
			sql += " select  ";
			sql += " USER_ID, USER_PW, USER_NAME, REGIST_DATE, LAST_VISITED_DATE  ";
			sql += " from ";
			sql += " TB_MEMBER ";
			sql += " where ";
			sql += " USER_ID = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionUserId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				returnMember = new MemberVO();
				returnMember.setUserId(rs.getString("USER_ID"));
				returnMember.setUserPw(rs.getString("USER_PW"));
				returnMember.setUserName(rs.getString("USER_NAME"));
				returnMember.setRegistDate(rs.getDate("REGIST_DATE"));
				returnMember.setLastVisitedDate(rs.getDate("LAST_VISITED_DATE"));
			}
			
			rs = null;
			pstmt = null;
			conn  = null;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnMember;
	}


	public void MemberLogin(MemberVO memberVO)throws Exception {
		checkId(memberVO);
		updateVisited(memberVO);
		
	}


	private void updateVisited(MemberVO memberVO) {
		
		
	
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try{
				conn= getConnection();
				
				String sql="";
				sql += " update  ";
				sql += " TB_MEMBER   ";
				sql += " set ";
				sql += " LAST_VISITED_DATE = now() ";
				sql += " where ";
				sql += " USER_ID = ? ";
				
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberVO.getUserId());
				
				pstmt.executeUpdate();
				
			
				pstmt = null;
				conn  = null;
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn != null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}


	public MemberVO editMember(MemberVO paramMember) {
		MemberVO returnMember = null;
		
		if(paramMember == null){
			
		}

			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try{
				conn= getConnection();
				
				String sql="";
				sql += " update  ";
				sql += " TB_MEMBER   ";
				sql += " set ";
				sql += " USER_PW = ? ";
				sql += " where ";
				sql += " USER_ID = ? ";
				
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, paramMember.getUserPw());
				pstmt.setString(2, paramMember.getUserId());
				
				pstmt.executeUpdate();
				
			
				pstmt = null;
				conn  = null;
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn != null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		returnMember = getMember(paramMember.getUserId()); 
	
		return returnMember;
	}
	
}
