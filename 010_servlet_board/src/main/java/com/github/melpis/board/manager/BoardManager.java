package com.github.melpis.board.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.melpis.board.vo.BoardVO;

public class BoardManager {
	//데이터 정의
	private final String DRIVER_NAME="org.postgresql.Driver";
	private final String DB_URI="jdbc:postgresql://127.0.0.1:5432/postgres";
	private final String DB_USER_NAME="postgres";
	private final String DB_USER_PASSWORE="postgres";
	//생성자
	//처리
	public BoardManager()
	{
		
	}
	
	/**
	 * select boardList
	 * @param paramBoard
	 * @return
	 */
	private List<BoardVO> selectBoardList(BoardVO paramBoard){
		List<BoardVO> returnBoardList= null;
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.2
		Connection conn= null;
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
		try{
		 conn = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
		//3.3
		String sql="";
		sql+=" select "; 
		sql+=" RN, SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
		sql+=" from " ; 
		sql+=" (select " ;
		sql+="	ROWNUM RN, SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
		sql+=" from ";
		sql+="		TB_BOARD ";
		sql+=" where " ;
		sql+="	ROWNUM <= ?)temp ";
		sql+="	where temp.RN >= ? ";
		//3.4
		 pstmt= conn.prepareStatement(sql);
		//3.5
		pstmt.setInt(1, paramBoard.getEndRowNum());
		pstmt.setInt(2, paramBoard.getStartRowNum());
		//3.6
		 rs=pstmt.executeQuery();
		//3.7
		returnBoardList = new ArrayList<BoardVO>();
		while(rs.next()){
			BoardVO boardVO=new BoardVO();
			boardVO.setSeq(rs.getInt("SEQ"));
			boardVO.setTitle(rs.getString("TITLE"));
			boardVO.setContent(rs.getString("CONTENT"));
			boardVO.setRegistDate(rs.getDate("REGIST_DATE"));
			boardVO.setReadCount(rs.getInt("READ_COUNT"));
			returnBoardList.add(boardVO);
			
		}
		rs = null;
		pstmt = null;
		conn = null;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs !=null)
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(pstmt != null)
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
		return returnBoardList;
	}
	

	/**
	 * insert board
	 * @param boardVO
	 */
	private void insertBoard(BoardVO boardVO){
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
	
		try{
		conn= DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
		//3. sql create
		String sql="";
		sql+=" insert into ";
		sql+=" TB_BOARD ";
		sql+=" (TITLE, CONTENT, REGIST_DATE, READ_COUNT ) ";
		sql+=" values ";
		sql+=" ( ? , ? , now(), ?) ";
		
		//4. PreparedStatemnet craete
		 pstmt= conn.prepareStatement(sql);
		//5. parameter setting 
		 
		pstmt.setString(1, boardVO.getTitle());
		pstmt.setString(2, boardVO.getContent());
		pstmt.setInt(3, boardVO.getReadCount());
		//6. execute
		
		int affectRowCount=pstmt.executeUpdate();
		
		//7. resource release
		pstmt=null;
		conn=null;
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * delete board
	 * @param seq
	 */
	private void deleteBoard(int seq){
		//3. 처리

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
		//1. driver loading
		//2. connection create
		conn = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_USER_PASSWORE);
		//3. sql craete
		String sql="";
		sql+=" delete ";
		sql+=" from ";
		sql+=" TB_BOARD ";
		sql+=" where SEQ = ? ";
		
		//4. preparedStateMent create
		 pstmt= conn.prepareStatement(sql);
		//5. parameter setting
		pstmt.setInt(1, seq);
		//6. execute
		int affectRowCount= pstmt.executeUpdate();
		//7. resoure release
		
		pstmt = null;
		conn = null;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
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
	
	
	/**
	 * update readCount
	 * @param seq
	 */
	private void updateReadCount(int seq){
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt  = null;
		//3.1 조회수 증가
		try{
			//2
			conn = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
			String sql="";
			
			sql+=" update ";
			sql+=" TB_BOARD ";
			sql+=" set ";
			sql+=" READ_COUNT = READ_COUNT+1 ";
			sql+=" where SEQ = ? ";
			
			//4
			
			pstmt= conn.prepareStatement(sql);
			//5
			pstmt.setInt(1, seq);
			
			//6
			pstmt.executeUpdate();
			pstmt = null;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			try {
				if(pstmt!=null)
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * select board
	 * @param seq
	 * @return
	 */
	private BoardVO selectBoard(int seq){
		BoardVO returnBoard = null;
		//3.1 게시물 한건 가져오기
		Connection conn = null;
		PreparedStatement pstmt  = null;
		ResultSet rs=null;
		//1
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try{
		//2
		conn = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
		//3
		String sql="";
		
		sql+=" select ";
		sql+=" SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
		sql+=" from ";
		sql+=" TB_BOARD ";
		sql+=" where SEQ = ? ";
		
		//4
		
		pstmt= conn.prepareStatement(sql);
		//5
		pstmt.setInt(1, seq);
		//6
		 rs = pstmt.executeQuery();
		//7
		
		if(rs.next()){
			returnBoard = new BoardVO();
			
			returnBoard.setSeq(rs.getInt("SEQ"));
			returnBoard.setTitle(rs.getString("TITLE"));
			returnBoard.setContent(rs.getString("CONTENT"));
			returnBoard.setRegistDate(rs.getDate("REGIST_DATE"));
			returnBoard.setReadCount(rs.getInt("READ_COUNT"));
			
		}
		//8
		rs = null;
		pstmt = null;
		conn = null;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(rs != null)
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					if(pstmt != null)
					pstmt.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return returnBoard;
	}
	
	/**
	 * update board
	 * @param boardVO
	 */
	private void updateBoard(BoardVO boardVO){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
		//1. driver loading
		
		//2. connection craete
		conn = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
		//3. sql create
		String sql="";
		sql+=" update  ";
		sql+=" TB_BOARD ";
		sql+=" set ";
		sql+=" TITLE = ? ,";
		sql+=" CONTENT = ? ,";
		sql+=" REGIST_DATE = SYSDATE ,";
		sql+=" READ_COUNT = READ_COUNT + 1 ";
		sql+=" where SEQ = ? ";
		
		//4. preparedstatement create
		pstmt= conn.prepareStatement(sql);
		//5. parameter setting
		pstmt.setString(1, boardVO.getTitle());
		pstmt.setString(2, boardVO.getContent());
		pstmt.setInt(3, boardVO.getSeq());
		
		//6. execute
		int affectRowCount = pstmt.executeUpdate();
		//7. resource release
		pstmt  = null;
		conn  = null;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null)
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
	
	public List<BoardVO> boardList(int page) {
		List<BoardVO> returnBoardList= null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(page < 1)
		{
			page=1;
		}
		//3. 처리
		//페이징 계산	
		//3.1 
		BoardVO boardVO = new BoardVO();
		boardVO.setPage(page);
		boardVO.setCountPerPage(10);
		returnBoardList=selectBoardList(boardVO);
		//4. 결과 출력
		return returnBoardList;
	}


	public void registerBoard(BoardVO boardVO) {
		//1. 데이터 추출
		if(boardVO == null){
			
		}
		//2. 유효성 검사
		if(boardVO.getTitle() == null || boardVO.getTitle().length() == 0 || boardVO.getTitle().length() > 100){
			// 예외처리
		}if(boardVO.getContent() == null || boardVO.getContent().length() == 0 || boardVO.getContent().length() > 4000){
			// 예외처리
		}
		//3. 처리

		//3.2. insert 실행
		insertBoard(boardVO);
		
		//4. 결과 출력
		
		
	}



	public void removeBoard(int seq) {
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){
			
		}
		deleteBoard(seq);
		
		//4. 결과 출력
		
		
	}



	public BoardVO readBoard(int seq) {
		BoardVO returnBoard = null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){
			
		}
		//3. 처리
		updateReadCount(seq);
		returnBoard = getBoard(seq);
		
		
		//4. 결과 출력
		return returnBoard;
	}



	public BoardVO getBoard(int seq) {
		BoardVO returnBoard = null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){
			//예외처리
		}
		//3. 처리
		returnBoard=selectBoard(seq);
		//4. 결과 출력
		return returnBoard;
	}



	public void editBoard(BoardVO boardVO) {
		//1. 데이터 추출
		if(boardVO == null){
			//예외처리
		}
		//2. 유효성검사
		if(boardVO.getSeq() < 1){
			//예외 처리
		}
		if(boardVO.getTitle() == null || boardVO.getTitle().length() == 0 || boardVO.getTitle().length() > 100){
			// 예외처리
		}if(boardVO.getContent() == null || boardVO.getContent().length() == 0 || boardVO.getContent().length() > 4000){
			// 예외처리
		}
		//3. 처리
		//3.1 update 실행
		updateBoard(boardVO);
		
	}
	
	//getter and setter

}
