package com.github.melpis.fileupload.manager;

import com.github.melpis.fileupload.vo.AttachfileVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FileManager {
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_URI = "jdbc:oracle:thin:@192.168.0.31:1521:inter";
    private final String DB_USER_ID = "postgres";
    private final String DB_USER_PW = "postgres";

    public FileManager() {

    }

    private Connection getConnection() {
        Connection returnConn = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            returnConn = DriverManager.getConnection(DB_URI, DB_USER_ID, DB_USER_PW);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnConn;
    }

    public void regist(AttachfileVO attachfileVO) throws Exception {

        if (attachfileVO == null) {
            throw new NullPointerException();
        }
        insertFile(attachfileVO);

    }


    public List<AttachfileVO> fileList() {
        List<AttachfileVO> returnList = null;

        returnList = getList();
        return returnList;


    }

    public void removeFile(int seq, String realPath) throws Exception {
        if (seq < 1) {
            throw new Exception("not seq");
        }

        if (realPath == null) {
            throw new Exception("not found path ");
        }
        String tempName = null;
        tempName = getFile();

        deletefileDir(tempName, realPath);
        deletefileDB(seq);

    }

    //getter and setter

    private void deletefileDB(int seq) {

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = getConnection();
            String sql = "";

            sql += " delete from ";
            sql += " TB_FILE ";
            sql += " where";
            sql += " SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seq);

            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("no data");
            }
            pstmt = null;
            conn = null;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deletefileDir(String tempName, String realPath) throws Exception {
        File file = new File(realPath, tempName);

        if (!file.exists()) {
            throw new Exception("not found file");
        }

        if (!file.delete()) {
            throw new Exception("no file");
        }


    }

    private String getFile() {
        String returnTempName = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            conn = getConnection();
            String sql = "";
            sql += " select ";
            sql += " TEMP_NAME ";
            sql += " from ";
            sql += " TB_FILE ";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                returnTempName = rs.getString("TEMP_NAME");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return returnTempName;
    }

    private List<AttachfileVO> getList() {
        List<AttachfileVO> returnList = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "";
            sql += " select ";
            sql += " SEQ, FILE_NAME, FILE_SIZE, CONTENT_TYPE, REGIST_DATE, DOWNLOAD_COUNT ";
            sql += " from ";
            sql += " TB_FILE ";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            returnList = new ArrayList<AttachfileVO>();
            while (rs.next()) {
                AttachfileVO attachfileVO = new AttachfileVO();
                attachfileVO.setSeq(rs.getInt("SEQ"));
                attachfileVO.setFileName(rs.getString("FILE_NAME"));
                attachfileVO.setFileSize(rs.getLong("FILE_SIZE"));
                attachfileVO.setContentType(rs.getString("CONTENT_TYPE"));
                attachfileVO.setRegistDate(rs.getDate("REGIST_DATE"));
                attachfileVO.setDownloadCount(rs.getInt("DOWNLOAD_COUNT"));
                returnList.add(attachfileVO);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return returnList;
    }

    private void insertFile(AttachfileVO attachfileVO) {


        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = getConnection();
            String sql = "";

            sql += " insert  into";
            sql += " TB_FILE ";
            sql += " (FILE_NAME, FILE_SIZE, CONTENT_TYPE, TEMP_NAME, REGIST_DATE, DOWNLOAD_COUNT)";
            sql += " values ";
            sql += " ( ?, ?, ?, ?,  now(), 0) ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attachfileVO.getFileName());
            pstmt.setLong(2, attachfileVO.getFileSize());
            pstmt.setString(3, attachfileVO.getContentType());
            pstmt.setString(4, attachfileVO.getTempName());

            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("no data");
            }
            pstmt = null;
            conn = null;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public AttachfileVO downloadFile(int seq) {
        AttachfileVO returnAttachfileVO;

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

            conn = getConnection();
            String sql = "";
            sql += " select  ";
            sql += " FILE_NAME, FILE_SIZE, CONTENT_TYPE, TEMP_NAME";
            sql += " from ";
            sql += " TB_FILE ";
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
                e2.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return returnAttachfileVO;
    }

    private void updateDownloadCount(int seq) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "";
            sql += " update ";
            sql += "  TB_FILE ";
            sql += " set ";
            sql += " DOWNLOAD_COUNT = DOWNLOAD_COUNT +1 ";
            sql += " where SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seq);
            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("no data");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
