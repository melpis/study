package com.github.melpis.attachfile.manager;

import com.github.melpis.attachfile.vo.AttachfileVO;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttachfileManager {

    private String jdbcDriver = "org.postgresql.Driver";
    private String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private String jdbcUserId = "postgres";
    private String jdbcUserPw = "postgres";

    public int registAttachfileList(int boardSeq, List<AttachfileVO> attachfileList) {
        int returnCount = 0;
        //1. 데이터 추출

        //2. 유효성 검사
        int seq = 0;

        Connection conn = null;
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            conn.setAutoCommit(false);

            for (int indexI = 0; indexI < attachfileList.size(); indexI++) {
                if (attachfileList.get(indexI).getFileSize() == 0) {
                    break;
                }

                insertAttachfile(boardSeq, attachfileList.get(indexI), conn);
                returnCount++;
                conn.commit();
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


        //3. 처리
        //4. 결과 출력
        return returnCount;
    }

    public int removeAttachfileByBoardSeq(int boardSeq) throws Exception {
        int returnCount = 0;


        List<AttachfileVO> attachfileList = null;

        attachfileList = getAttachfileListByBoardSeq(boardSeq);

        deleteFile(attachfileList);

        returnCount = deleteDB(boardSeq);

        //3. 처리
        //4. 결과 출력
        return returnCount;
    }

    public List<AttachfileVO> getAttachfileListByBoardSeq(int boardSeq) {
        List<AttachfileVO> returnAttachfileList = null;
        //1. 데이터 추출
        //2. 유효성 검사
        //3. 처리
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " select ";
            sql += " SEQ, FILE_NAME, FILE_SIZE, CONTENT_TYPE, REGIST_DATE, DOWNLOAD_COUNT ";
            sql += " from ";
            sql += " TBL_ATTACHFILE ";
            sql += " where BOARD_SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardSeq);
            rs = pstmt.executeQuery();
            returnAttachfileList = new ArrayList<AttachfileVO>();
            while (rs.next()) {
                AttachfileVO attachfileVO = new AttachfileVO();
                attachfileVO.setSeq(rs.getInt("SEQ"));
                attachfileVO.setFileName(rs.getString("FILE_NAME"));
                attachfileVO.setFileSize(rs.getLong("FILE_SIZE"));
                attachfileVO.setContentType(rs.getString("CONTENT_TYPE"));
                attachfileVO.setRegistDate(rs.getDate("REGIST_DATE"));
                attachfileVO.setDownloadCount(rs.getInt("DOWNLOAD_COUNT"));
                returnAttachfileList.add(attachfileVO);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //4. 결과 출력
        return returnAttachfileList;
    }

    public AttachfileVO downloadAttachfile(int seq) {
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

        try {

            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            String sql = "";
            sql += " select  ";
            sql += " FILE_NAME, FILE_SIZE, CONTENT_TYPE, TEMP_NAME";
            sql += " from ";
            sql += " TBL_ATTACHFILE ";
            sql += " where SEQ = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seq);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                returnAttachfileVO = new AttachfileVO();
                returnAttachfileVO.setFileName(rs.getString("FILE_NAME"));
                returnAttachfileVO.setFileSize(rs.getLong("FILE_SIZE"));
                returnAttachfileVO.setContentType(rs.getString("CONTENT_TYPE"));
                returnAttachfileVO.setTempName(rs.getString("TEMP_NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


        return returnAttachfileVO;
    }

    private void updateDownloadCount(int seq) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " update ";
            sql += "  TBL_ATTACHFILE ";
            sql += " set ";
            sql += " DOWNLOAD_COUNT = DOWNLOAD_COUNT +1 ";
            sql += " where SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seq);
            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("업데이트 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void insertAttachfile(int boardSeq, AttachfileVO attachfileVO, Connection conn) {


        PreparedStatement pstmt = null;

        try {

            String sql = "";
            sql += " insert into ";
            sql += " TBL_ATTACHFILE(FILE_NAME, FILE_SIZE, CONTENT_TYPE,TEMP_NAME, REGIST_DATE, DOWNLOAD_COUNT,BOARD_SEQ)";
            sql += " values( ?, ?, ?, ?, now(), 0, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attachfileVO.getFileName());
            pstmt.setLong(2, attachfileVO.getFileSize());
            pstmt.setString(3, attachfileVO.getContentType());
            pstmt.setString(4, attachfileVO.getTempName());
            pstmt.setInt(5, boardSeq);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private void deleteFile(List<AttachfileVO> attachfileList) throws Exception {

        for (int indexI = 0; indexI < attachfileList.size(); indexI++) {

            File file = new File("C:/upload/", attachfileList.get(indexI).getTempName());

            if (!file.exists()) {
                throw new Exception("파일 없음");
            }

            if (!file.delete()) {
                throw new Exception("파일 삭제 되지 않습니다");
            }

        }
    }

    private int deleteDB(int boardSeq) {
        int returnCount = 0;
        //1. 데이터 추출
        //2. 유효성 검사
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " delete from TBL_ATTACHFILE ";
            sql += " where BOARD_SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardSeq);

            returnCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return returnCount;
    }
}
