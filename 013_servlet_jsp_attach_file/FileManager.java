package net.bit.lecture.fileupload.manager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.bit.lecture.fileupload.vo.AttachfileVO;

public class FileManager {
	//������ ����
	private final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";
	private final String DB_URI="jdbc:oracle:thin:@192.168.0.31:1521:inter";
	private final String DB_USER_ID="user00";
	private final String DB_USER_PW="user00";
	
	//����
	public FileManager() {
	
	}
	
	//ó��
	private Connection getConnection()
	{
		Connection returnConn = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			returnConn = DriverManager.getConnection(DB_URI,DB_USER_ID,DB_USER_PW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnConn;
	}
	
	public void regist(AttachfileVO attachfileVO)throws Exception{
		//������ ����
		if(attachfileVO == null)
		{
			throw new NullPointerException("���� �߸� �Ǿ���ϴ�");
		}
		//��ȿ�� �˻�
		//ó��
		int seq=0;
		seq=getSeq();
		attachfileVO.setSeq(seq);
		insertFile(attachfileVO);
		
		
		//��� ���
		
		
		
		
		
		
	}
	
	
	public List<AttachfileVO> fileList(){
		List<AttachfileVO> returnList  = null;
	
		returnList = getList(); 
		return returnList;
		
		
	}
	
	public void removeFile(int seq, String realPath)throws Exception
	{
		if(seq < 1 )
		{
			throw new Exception("��ȣ �߸� ���� ");
		}
		
		if(realPath == null){
			throw new Exception("���� ���� �ʴ� ��� ");
		}
		String tempName= null;
		tempName=getFile();
		
		deletefileDir(tempName,realPath);
		deletefileDB(seq);
		
	}
	
	//getter and setter

	private void deletefileDB(int seq) {
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		
		try{
			conn = getConnection();
			String sql= "";
			
			sql += " delete from ";
			sql += " TB_FILE ";
			sql += " where";
			sql += " SEQ = ? ";
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			int affectRowCount = pstmt.executeUpdate();
			
			if(affectRowCount < 1)
			{
				throw new Exception("���� ���� �ȵƳ�?");
			}
			pstmt = null;
			conn = null;
			
			
		}catch (Exception e) {
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

	private void deletefileDir(String tempName, String realPath)throws Exception {
		File file = new File(realPath,tempName);
		
		if(!file.exists())
		{
			throw new Exception("���� ����");
		}
		
		if(!file.delete())
		{
			throw new Exception("���� ���� ���� �ʽ��ϴ�");
		}
		
		
	}

	private String getFile() {
		String returnTempName= null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		try{
			conn = getConnection();
			String sql="";
			sql+= " select ";
			sql+= " TEMP_NAME ";
			sql+= " from ";
			sql+= " TB_FILE ";
			
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				returnTempName=rs.getString("TEMP_NAME");
			}
			rs  = null;
			pstmt = null;
			conn = null;
		}catch (Exception e) {
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
		
		
		return returnTempName;
	}

	private List<AttachfileVO> getList() {
		List<AttachfileVO> returnList  = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			String sql="";
			sql+= " select ";
			sql+= " SEQ, FILE_NAME, FILE_SIZE, CONTENT_TYPE, REGIST_DATE, DOWNLOAD_COUNT ";
			sql+= " from ";
			sql+= " TB_FILE ";
			
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			returnList = new ArrayList<AttachfileVO>();
			while(rs.next()){
				AttachfileVO attachfileVO = new AttachfileVO();
				attachfileVO.setSeq(rs.getInt("SEQ"));
				attachfileVO.setFileName(rs.getString("FILE_NAME"));
				attachfileVO.setFileSize(rs.getLong("FILE_SIZE"));
				attachfileVO.setContentType(rs.getString("CONTENT_TYPE"));
				attachfileVO.setRegistDate(rs.getDate("REGIST_DATE"));
				attachfileVO.setDownloadCount(rs.getInt("DOWNLOAD_COUNT"));
				returnList.add(attachfileVO);
				
			}
			rs  = null;
			pstmt = null;
			conn = null;
		}catch (Exception e) {
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
		
		
		return returnList;
	}

	private void insertFile(AttachfileVO attachfileVO) {
		
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		
		try{
			conn = getConnection();
			String sql= "";
			
			sql += " insert  into";
			sql += " TB_FILE ";
			sql += " (SEQ, FILE_NAME, FILE_SIZE, CONTENT_TYPE, TEMP_NAME, REGIST_DATE, DOWNLOAD_COUNT)";
			sql += " values ";
			sql += " (?, ?, ?, ?, ?, now(), 0) ";
			 
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, attachfileVO.getSeq());
			pstmt.setString(2, attachfileVO.getFileName());
			pstmt.setLong(3, attachfileVO.getFileSize());
			pstmt.setString(4, attachfileVO.getContentType());
			pstmt.setString(5, attachfileVO.getTempName());
			
			int affectRowCount = pstmt.executeUpdate();
			
			if(affectRowCount < 1)
			{
				throw new Exception("���� �ȵ���?");
			}
			pstmt = null;
			conn = null;
			
			
		}catch (Exception e) {
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

	private int getSeq() {
		int returnSeq = 0;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			String sql="";
			sql+= " select SEQ_TB_FILE.NEXTVAL SE ";
			sql+= " from dual ";
			
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				returnSeq = rs.getInt("SE");
			}
			rs  = null;
			pstmt = null;
			conn = null;
		}catch (Exception e) {
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
		
		
		return returnSeq;
	}

	public AttachfileVO downloadFile(int seq) {
		AttachfileVO returnAttachfileVO = null;
		
		updateDownloadCount(seq);
		returnAttachfileVO = selectFileinfo(seq);
		
		
		
		return returnAttachfileVO;
	}

	private AttachfileVO selectFileinfo(int seq) {
		AttachfileVO returnAttachfileVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			conn= getConnection();
			String sql= "";
			sql +=" select  ";
			sql +=" FILE_NAME, FILE_SIZE, CONTENT_TYPE, TEMP_NAME";
			sql +=" from ";
			sql +=" TB_FILE ";
			sql +=" where SEQ = ? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				returnAttachfileVO= new AttachfileVO();
				returnAttachfileVO.setFileName(rs.getString("FILE_NAME"));
				returnAttachfileVO.setFileSize(rs.getLong("FILE_SIZE"));
				returnAttachfileVO.setContentType(rs.getString("CONTENT_TYPE"));
				returnAttachfileVO.setTempName(rs.getString("TEMP_NAME"));
			}
			
			rs = null;
			pstmt = null;
			conn = null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null)
				rs.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				if(pstmt != null)
				pstmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return returnAttachfileVO;
	}

	private void updateDownloadCount(int seq) {
		Connection conn  = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			String sql ="";
			sql+=" update ";
			sql+="  TB_FILE ";
			sql+=" set ";
			sql+=" DOWNLOAD_COUNT = DOWNLOAD_COUNT +1 ";
			sql+=" where SEQ = ? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			int affectRowCount = pstmt.executeUpdate();
			
			if(affectRowCount < 1 ){
				throw new Exception("������Ʈ ����");
			}
			pstmt = null;
			conn = null;
		}catch (Exception e) {
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
}
