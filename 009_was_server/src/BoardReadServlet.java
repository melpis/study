import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BoardReadServlet extends Servlet {
	@Override
 public void service(Request request, Response response) {
	
		
		
		BoardVO boardVO = null;
	
		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.6:1521:orcl","user00","user00");
			String sql="";
			sql+=" select ";
			sql+=" SEQ, TITLE, CONTENT, REGIST_DATE, READ_COUNT ";
			sql+=" from ";
			sql+=" TB_BOARD ";
			sql+=" where SEQ = ?";			
			 
			pstmt= conn.prepareStatement(sql);
			
			String seq=request.getParameter("seq");
			
			pstmt.setInt(1, Integer.valueOf(seq));
			rs=pstmt.executeQuery();
		
			
			while(rs.next()){
				boardVO= new BoardVO();
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
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		String html = "";
		html += "<html>";
		html += "<head>";
		html += "</head>";
		html += "<body>";
		html += " <table border="+ "1" +">";
		html += "<tr>";
	
		html += "<td>";
		html += "��ȣ";
		html += "</td>";
		
		html += "<td>";
		html += "����";
		html += "</td>";
		
		html += "<td>";
		html += "����";
		html += "</td>";
		
		html += "<td>";
		html += "��¥";
		html += "</td>";
	
		html += "<td>";
		html += "��ȸ��";
		html += "</td>";
		html += "</tr>";
		
		html += "<tr>";
		
		html +="<td>";
		html +=boardVO.getSeq();
		html +="</td>";
		
		html +="<td>";
		html +=boardVO.getTitle();
		html +="</td>";
		
		html +="<td>";
		html +=boardVO.getContent();
		html +="</td>";
		
		html +="<td>";
		html +=boardVO.getRegistDate();
		html +="</td>";
		
		html +="<td>";
		html +=boardVO.getReadCount();
		html +="</td>";
		
		
		
		
		html += "</tr>";
		html += " </table> ";
		html += "</body>";
		html += "</html>";
		response.write(html);
		
		
	
	}
}
