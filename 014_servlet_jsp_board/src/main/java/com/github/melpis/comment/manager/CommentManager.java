package com.github.melpis.comment.manager;

import com.github.melpis.comment.vo.CommentVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommentManager {
	private final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";
	private final String DB_URI ="jdbc:oracle:thin:@192.168.0.6:1521:orcl";
	private final String DB_USER ="user00";
	private final String DB_PASS ="user00";
	
	
	public List<CommentVO> listComment(){
		List<CommentVO> returnCommentList = null;
		
		//1. 데이터 추출
		//2. 유효성 검사
		//3. 처리
		returnCommentList= getCommentList();
		//4. 결과 출력
		
		
	
		return returnCommentList;
	}
	public void registComment(CommentVO paramVO){
		int seq = 0;
		 
		
		seq = getSeqComment();
		paramVO.setSeq(seq);
		
		insertCommnet(paramVO);
		
		
	}
	private void insertCommnet(CommentVO paramVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			
			String sql="";
			sql+=" insert into TB_COMMENT(SEQ, CONTENT, REGIST_DATE)"; 
			sql+=" values( ?, ?, sysdate) "; 
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, paramVO.getSeq());
			pstmt.setString(2, paramVO.getContent());
			pstmt.executeQuery();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(pstmt !=null)
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
	private int getSeqComment() {
		int returnSeq=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			
			String sql="";
			sql+=" select SEQ_TB_COMMENT.NEXTVAL TS "; 
			sql+=" from dual "; 
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				returnSeq=rs.getInt("TS");
			}
			rs  = null;
			pstmt = null;
			conn = null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs !=null)
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(pstmt !=null)
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
		
		return returnSeq;
		
	}
	private List<CommentVO> getCommentList() {
		List<CommentVO> returnCommentList = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			
			String sql="";
			sql+=" select SEQ, CONTENT, REGIST_DATE ";
			sql+=" from TB_COMMENT ";
			
			
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			returnCommentList = new ArrayList<CommentVO>();
			
			while(rs.next()){
				CommentVO commentVO = new CommentVO();
				
				commentVO.setSeq(rs.getInt("SEQ"));
				commentVO.setContent(rs.getString("CONTENT"));
				commentVO.setRegistDate(rs.getDate("REGIST_DATE"));
				returnCommentList.add(commentVO);
			}
			
			rs = null;
			pstmt = null;
			conn = null;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs !=null)
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(pstmt !=null)
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
		
		
		
		
		return returnCommentList;
	}
	private Connection getConnection(){
		Connection returnConnection= null;
		try{
			Class.forName(this.DB_DRIVER);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			returnConnection= DriverManager.getConnection(this.DB_URI,this.DB_USER,this.DB_PASS);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return returnConnection;
	}
	public void removeComment(int seq) {
		deleteComment(seq);
		
	}
	private void deleteComment(int seq) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try{
			conn = getConnection();
			
			String sql="";
			sql+=" delete ";
			sql+=" from TB_COMMENT ";
			sql+=" where SEQ = ? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			
		
			
			
			pstmt = null;
			conn = null;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt !=null)
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
	
}
