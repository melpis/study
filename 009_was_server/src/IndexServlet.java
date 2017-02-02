

public class IndexServlet extends Servlet {

	@Override
	public void service(Request request, Response response) {
//1. 데이터 추출
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		//2. 유효성 검사
		
		//3. 처리
		
		//4. 결과 처리
		
		
		
		String html="";
		html+=" <html>  ";
		html+=" <head> ";
		html+=" </head> ";
		html+=" <body> ";
		html+=" userId : "+userId +"<br/>\n";
		html+=" userPw : "+userPw +"<br/>\n";
		html+=" </body> ";
		html+=" </html> ";
		response.write(html);
	}

}
