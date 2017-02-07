package com.github.melpis.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.melpis.board.vo.BoardVO;


public class BoardDAO {
    /**
     *
     */
    private String dbURI = "";
    private String dbUserId = "";
    private String dbUserPw = "";
    private String dbDriver = "";

    //생성자
    public BoardDAO(Map<String, String> dbInfo) {
        this.dbDriver = dbInfo.get("dbDriver");
        this.dbURI = dbInfo.get("dbURI");
        this.dbUserId = dbInfo.get("dbUserId");
        this.dbUserPw = dbInfo.get("dbUserPw");

    }


    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(this.dbDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(this.dbURI, this.dbUserId, this.dbUserPw);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }


    //처리

    /**
     * select boardList
     *
     * @param paramBoard
     * @return
     */
    public List<BoardVO> selectBoardList(BoardVO paramBoard) {
        List<BoardVO> returnBoardList = null;

        //3.2
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            //3.3
            String sql = "";
            sql += " select ";
            sql += "	ROWNUM RN, SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
            sql += " from ";
            sql += "		TB_BOARD ";
            sql += "	offset ? limit ? ";
            //3.4
            pstmt = conn.prepareStatement(sql);
            //3.5
            pstmt.setInt(1, paramBoard.getPage() * paramBoard.getCountPerPage());
            pstmt.setInt(2, paramBoard.getCountPerPage());
            //3.6
            rs = pstmt.executeQuery();
            //3.7
            returnBoardList = new ArrayList<BoardVO>();
            while (rs.next()) {
                BoardVO boardVO = new BoardVO();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
        return returnBoardList;
    }

    /**
     * insert board
     *
     * @param boardVO
     */
    public int insertBoard(BoardVO boardVO) {
        int returnSeq = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            //3. sql create
            String sql = "";
            sql += " insert into ";
            sql += " TB_BOARD ";
            sql += " (SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ) ";
            sql += " values ";
            sql += " (?, ? , ? , SYSDATE, ?) ";

            //4. PreparedStatemnet craete
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            returnSeq = boardVO.getSeq();
            pstmt.setInt(1, returnSeq);
            pstmt.setString(2, boardVO.getTitle());
            pstmt.setString(3, boardVO.getContent());
            pstmt.setInt(4, boardVO.getReadCount());
            //6. execute

            int affectRowCount = pstmt.executeUpdate();

            //7. resource release
            pstmt = null;
            conn = null;
        } catch (SQLException e) {
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
        return returnSeq;
    }

    /**
     * delete board
     *
     * @param seq
     */
    public boolean deleteBoard(int seq) {
        boolean returnResult = false;
        //3. 처리


        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1. driver loading
            //2. connection create
            conn = getConnection();
            //3. sql craete
            String sql = "";
            sql += " delete ";
            sql += " from ";
            sql += " TB_BOARD ";
            sql += " where SEQ = ? ";

            //4. preparedStateMent create
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            pstmt.setInt(1, seq);
            //6. execute
            int affectRowCount = pstmt.executeUpdate();
            //7. resoure release

            pstmt = null;
            conn = null;
            returnResult = true;
        } catch (SQLException e) {
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
        return returnResult;
    }


    /**
     * update readCount
     *
     * @param seq
     */
    public void updateReadCount(int seq) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        //3.1 조회수 증가
        try {
            //2
            conn = getConnection();
            String sql = "";

            sql += " update ";
            sql += " TB_BOARD ";
            sql += " set ";
            sql += " READ_COUNT = READ_COUNT+1 ";
            sql += " where SEQ = ? ";

            //4

            pstmt = conn.prepareStatement(sql);
            //5
            pstmt.setInt(1, seq);

            //6
            pstmt.executeUpdate();
            pstmt = null;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * select board
     *
     * @param seq
     * @return
     */
    public BoardVO selectBoard(int seq) {
        BoardVO returnBoard = null;
        //3.1 게시물 한건 가져오기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //1

        try {
            //2
            conn = getConnection();
            //3
            String sql = "";

            sql += " select ";
            sql += " SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
            sql += " from ";
            sql += " TB_BOARD ";
            sql += " where SEQ = ? ";

            //4

            pstmt = conn.prepareStatement(sql);
            //5
            pstmt.setInt(1, seq);
            //6
            rs = pstmt.executeQuery();
            //7

            if (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
        return returnBoard;
    }

    /**
     * update board
     *
     * @param boardVO
     */
    public void updateBoard(BoardVO boardVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1. driver loading

            //2. connection craete
            conn = getConnection();
            //3. sql create
            String sql = "";
            sql += " update  ";
            sql += " TB_BOARD ";
            sql += " set ";
            sql += " TITLE = ? ,";
            sql += " CONTENT = ? ,";
            sql += " REGIST_DATE = SYSDATE ,";
            sql += " READ_COUNT = READ_COUNT + 1 ";
            sql += " where SEQ = ? ";

            //4. preparedstatement create
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            pstmt.setString(1, boardVO.getTitle());
            pstmt.setString(2, boardVO.getContent());
            pstmt.setInt(3, boardVO.getSeq());

            //6. execute
            int affectRowCount = pstmt.executeUpdate();
            //7. resource release
            pstmt = null;
            conn = null;
        } catch (SQLException e) {
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

    public int selectTotalpage(BoardVO paramBoard) {
        int returnpage = 0;


        //3.2
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            //3.3
            String sql = "";
            sql += " select ";
            sql += " COUNT(SEQ) TP";
            sql += " from ";
            sql += "	TB_BOARD ";


            if (paramBoard.getSearchColum() != null || paramBoard.getSearchWord() != null) {
                sql += " where ";
                sql += " " + paramBoard.getSearchColum() + " ";
                sql += " like'%'||?||'%' ";


            }
            //3.4
            pstmt = conn.prepareStatement(sql);

            if (paramBoard.getSearchColum() != null || paramBoard.getSearchWord() != null) {
                pstmt.setString(1, paramBoard.getSearchWord());
            }


            rs = pstmt.executeQuery();
            //3.7
            if (rs.next()) {
                returnpage = rs.getInt("TP");
            }
            rs = null;
            pstmt = null;
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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

        return returnpage;
    }
}
