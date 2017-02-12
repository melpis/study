package com.github.melpis.board.manager;

import com.github.melpis.board.vo.BoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private String jdbcDriver = "org.postgresql.Driver";
    private String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private String jdbcUserId = "postgres";
    private String jdbcUserPw = "postgres";


    public void registBoard(BoardVO board) throws Exception {

        //1. 데이터 추출
        if (board == null) {
            throw new Exception("데이터가 없음");
        }
        //2. 유효성 검사
        if (board.getTitle() == null || board.getTitle().length() > 200) {
            throw new Exception("제목이 부적합니다 ");
        }
        if (board.getContent() == null || board.getContent().length() > 4000) {
            throw new Exception("내용이 부적합니다 ");
        }
        if (board.getMember().getUserId() == null || board.getMember().getUserId().length() > 20) {
            throw new Exception("아이디가  부적합니다 ");
        }
        //3. 처리
        insertBoard(board);


        //4. 결과 출력


    }

    private int insertBoard(BoardVO board) {
        int returnSeq = 0;

        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. sql create
            String sql = "";
            sql += " insert into ";
            sql += " TBL_BOARD ";
            sql += " (USER_ID, TITLE, CONTENT, REGIST_DATE, READ_COUNT ) ";
            sql += " values ";
            sql += " (?, ? , ? , now(), 0) ";

            //4. PreparedStatemnet craete
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            pstmt.setString(1, board.getMember().getUserId());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());

            //6. execute

            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("열 안들어갔음");
            }
            returnSeq = board.getSeq();
            //7. resource release
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
        return returnSeq;
    }

    public void editBoard(BoardVO boardVO) {
        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1. driver loading

            //2. connection craete
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. sql create
            String sql = "";
            sql += " update  ";
            sql += " TBL_BOARD ";
            sql += " set ";
            sql += " TITLE = ? ,";
            sql += " CONTENT = ? ,";
            sql += " REGIST_DATE = now() ";
            sql += " where SEQ = ? ";

            //4. preparedstatement create
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            pstmt.setString(1, boardVO.getTitle());
            pstmt.setString(2, boardVO.getContent());
            pstmt.setInt(3, boardVO.getSeq());

            //6. execute
            int affectRowCount = pstmt.executeUpdate();
            if (affectRowCount < 1) {
                throw new Exception("열이 안들어 갔음");
            }
            //7. resource release
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

    public void removeBoard(int seq) {
        //3. 처리


        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1. driver loading
            //2. connection create
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. sql craete
            String sql = "";
            sql += " delete ";
            sql += " from ";
            sql += " TBL_BOARD ";
            sql += " where SEQ = ? ";

            //4. preparedStateMent create
            pstmt = conn.prepareStatement(sql);
            //5. parameter setting
            pstmt.setInt(1, seq);
            //6. execute
            int affectRowCount = pstmt.executeUpdate();

            if (affectRowCount < 1) {
                throw new Exception("열 안들어갔음");
            }
            //7. resoure release

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

    public BoardVO readBoard(int seq) throws Exception {
        BoardVO returnBoardVO = null;
        if (seq < 1) {
            throw new Exception("폐이지 번호 에러임 .");
        }


        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);

        updateReadCount(boardVO);
        returnBoardVO = getBoard(boardVO);
        return returnBoardVO;
    }

    public void updateReadCount(BoardVO boardVO) {
        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        //3.1 조회수 증가
        try {
            //2
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            String sql = "";

            sql += " update ";
            sql += " TBL_BOARD ";
            sql += " set ";
            sql += " READ_COUNT = READ_COUNT+1 ";
            sql += " where SEQ = ? ";

            //4

            pstmt = conn.prepareStatement(sql);
            //5
            pstmt.setInt(1, boardVO.getSeq());

            //6
            pstmt.executeUpdate();
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

    public BoardVO getBoard(BoardVO boardVO) {
        BoardVO returnBoardVO = null;
        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.1 게시물 한건 가져오기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //1

        try {
            //2
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3
            String sql = "";

            sql += " select ";
            sql += " SEQ, USER_ID,TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
            sql += " from ";
            sql += " TBL_BOARD ";
            sql += " where SEQ = ? ";

            //4

            pstmt = conn.prepareStatement(sql);
            //5
            pstmt.setInt(1, boardVO.getSeq());
            //6
            rs = pstmt.executeQuery();
            //7

            if (rs.next()) {
                returnBoardVO = new BoardVO();

                returnBoardVO.setSeq(rs.getInt("SEQ"));
                returnBoardVO.getMember().setUserId(rs.getString("USER_ID"));
                returnBoardVO.setTitle(rs.getString("TITLE"));
                returnBoardVO.setContent(rs.getString("CONTENT"));
                returnBoardVO.setRegistDate(rs.getDate("REGIST_DATE"));
                returnBoardVO.setReadCount(rs.getInt("READ_COUNT"));

            }
            //8
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
        return returnBoardVO;
    }

    public List<BoardVO> boardList(BoardVO paramBoard) {
        List<BoardVO> retrunBoardList = null;

        int totalCount = 0;
        totalCount = selectTotalpage();
        paramBoard.setTotalCount(totalCount);

        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.2
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3.3
            String sql = "";
            sql += " select ";
            sql += "  SEQ, USER_ID, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
            sql += " from ";
            sql += "		TBL_BOARD ";
            sql += "	offset ? limit ? ";
            //3.4
            pstmt = conn.prepareStatement(sql);
            //3.5
            pstmt.setInt(1, paramBoard.getPage() * paramBoard.getCountPerPage());
            pstmt.setInt(2, paramBoard.getCountPerPage());
            //3.6
            rs = pstmt.executeQuery();
            //3.7
            retrunBoardList = new ArrayList<BoardVO>();
            while (rs.next()) {
                BoardVO boardVO = new BoardVO();
                boardVO.setSeq(rs.getInt("SEQ"));
                boardVO.setTitle(rs.getString("TITLE"));
                boardVO.getMember().setUserId(rs.getString("USER_ID"));
                boardVO.setContent(rs.getString("CONTENT"));
                boardVO.setRegistDate(rs.getDate("REGIST_DATE"));
                boardVO.setReadCount(rs.getInt("READ_COUNT"));
                retrunBoardList.add(boardVO);

            }
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


        return retrunBoardList;

    }

    private int selectTotalpage() {
        int returnpage = 0;

        try {
            Class.forName(jdbcDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.2
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3.3
            String sql = "";
            sql += " select ";
            sql += " COUNT(SEQ) TP";
            sql += " from ";
            sql += "	TBL_BOARD ";
            //3.4
            pstmt = conn.prepareStatement(sql);
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
