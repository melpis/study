package com.github.melpis.comment.manager;

import com.github.melpis.comment.vo.CommentVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private String jdbcDriver = "org.postgresql.Driver";
    private String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private String jdbcUserId = "postgres";
    private String jdbcUserPw = "postgres";

    public void registComment(String userId, int boardSeq, CommentVO commentVO) {


        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " insert into TBL_COMMENT( CONTENT, REGIST_DATE, USER_ID, BOARD_SEQ)";
            sql += " values( ?, now(), ?, ?) ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, commentVO.getContent());
            pstmt.setString(2, userId);
            pstmt.setInt(3, boardSeq);
            pstmt.executeQuery();


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


    public void removeComment(int seq) {

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " delete ";
            sql += " from TBL_COMMENT ";
            sql += " where SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, seq);
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

    public void removeComments(int boardSeq) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " delete ";
            sql += " from TBL_COMMENT ";
            sql += " where BOARD_SEQ = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardSeq);
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

    public List<CommentVO> listCommentByBoardSeq(int boardSeq, CommentVO paramcComment) {
        List<CommentVO> returnCommentList = null;

        //1. 데이터 추출
        //2. 유효성 검사
        //3. 처리


        int totalCount = 0;
        totalCount = getTotalCount(boardSeq);
        paramcComment.setTotalCount(totalCount);

        returnCommentList = getCommentList(boardSeq);


        //4. 결과 출력
        return returnCommentList;
    }

    private int getTotalCount(int boardSeq) {
        int returnSeq = 0;
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " select COUNT(SEQ) CS";
            sql += " from TBL_COMMENT ";
            sql += " where BOARD_SEQ = ? ";


            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardSeq);
            rs = pstmt.executeQuery();


            if (rs.next()) {
                returnSeq = rs.getInt("CS");
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
        return returnSeq;
    }

    private List<CommentVO> getCommentList(int boardSeq) {
        List<CommentVO> returnCommentList = null;

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            String sql = "";
            sql += " select SEQ, CONTENT, REGIST_DATE, USER_ID ";
            sql += " from TBL_COMMENT ";
            sql += " where BOARD_SEQ = ? ";


            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardSeq);
            rs = pstmt.executeQuery();

            returnCommentList = new ArrayList<CommentVO>();

            while (rs.next()) {
                CommentVO commentVO = new CommentVO();
                commentVO.setSeq(rs.getInt("SEQ"));
                commentVO.setContent(rs.getString("CONTENT"));
                commentVO.setRegistDate(rs.getDate("REGIST_DATE"));
                commentVO.getMember().setUserId(rs.getString("USER_ID"));
                returnCommentList.add(commentVO);
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
        return returnCommentList;
    }


}
