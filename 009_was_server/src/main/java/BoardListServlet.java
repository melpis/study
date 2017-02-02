

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BoardListServlet extends Servlet {
    @Override
    public void service(Request request, Response response) {
        List<BoardVO> boardList = null;

        BoardVO boardVO = null;


        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_USER_PASSWORE);
            String sql = "";
            sql += " select ";
            sql += " SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
            sql += " from ";
            sql += " TB_BOARD ";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            boardList = new ArrayList<BoardVO>();

            while (rs.next()) {
                boardVO = new BoardVO();
                int resultSeq = rs.getInt("SEQ");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Date registDate = rs.getDate("REGIST_DATE");
                int readCount = rs.getInt("READ_COUNT");

                boardVO.setSeq(resultSeq);
                boardVO.setTitle(title);
                boardVO.setContent(content);
                boardVO.setRegistDate(registDate);
                boardVO.setReadCount(readCount);
                boardList.add(boardVO);
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        String html = "";
        html += "<html>";
        html += "<head>";
        html += "</head>";
        html += "<body>";
        html += " <table border=" + "1" + ">";
        html += "<tr>";

        html += "<td>";
        html += "번호";
        html += "</td>";

        html += "<td>";
        html += "제목";
        html += "</td>";

        html += "<td>";
        html += "내용";
        html += "</td>";

        html += "<td>";
        html += "등록일";
        html += "</td>";

        html += "<td>";
        html += "조회수";
        html += "</td>";

        html += "</tr>";

        for (int indexI = 0; indexI < boardList.size(); indexI++) {
            html += "<tr>";

            html += "<td>" + boardList.get(indexI).getSeq();
            html += "</td>";

            html += "<td>" + "<a href=" + "/board/read.jsp?" + "seq=" + boardList.get(indexI).getSeq() + ">" + boardList.get(indexI).getTitle() + "</a>";
            html += "</td>";

            html += "<td>" + boardList.get(indexI).getContent();
            html += "</td>";

            html += "<td>" + boardList.get(indexI).getRegistDate();
            html += "</td>";

            html += "<td>" + boardList.get(indexI).getReadCount();
            html += "</td>";

            html += "</tr>";
        }
        html += " </table> ";
        html += "</body>";
        html += "</html>";

        response.write(html);
    }

}
