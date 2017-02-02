import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	public static final String SERVER_ROOT = "www";

	private static final int SERVER_PORT = 8080;

	public static void main(String[] args)  {
		//1. 서버 자원 초기화
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}

		
		while(true) {
			//2. 대기
			Socket clientSocket = null;
			InputStream is = null;
			OutputStream os = null;
			
			try {
				clientSocket = serverSocket.accept();
				is = clientSocket.getInputStream();
				os = clientSocket.getOutputStream();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			
			
			//3. 접속 수락 후 처리
			//3.1. 자원 선언
			Request request = null;
			Response response = null;
			Resource resource = null;
			try {
				request = new Request(is);
				if (request.getRequestURI().equals("/EXIT")) {
					break;
				}
				response = new Response(os);
				resource = new Resource();
			    resource.service(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
				break;
			}finally{
				//3.5. 자원 해제
				try {
					if(os!=null){os.close();}
					if(is!=null){is.close();}
					if(clientSocket!=null){clientSocket.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}


		//4. 자원 해제
		try {
			if(serverSocket!=null){	serverSocket.close();}
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}






