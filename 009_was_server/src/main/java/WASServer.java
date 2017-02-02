

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class WASServer extends Thread {
    public static final String SERVER_ROOT = "www";
    private static final int SERVER_PORT = 7070;

    private static Map<String, String> pages = null;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        CreateSAXParser parser = new CreateSAXParser("resource/web.xml");

        pages = parser.getServletMap();


        while (true) {
            //2. 대기
            Socket clientSocket = null;
            InputStream is = null;
            OutputStream os = null;
            try {
                clientSocket = serverSocket.accept();
                //3. 접속 수락 후 처리
                is = clientSocket.getInputStream();
                os = clientSocket.getOutputStream();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
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
                //? ext
                if (request.getRequestURI().lastIndexOf(".jsp") > -1) {
                    Servlet servlet = null;
                    servlet = (Servlet) Class.forName(
                            pages.get(request.getRequestURI())).newInstance();
                    servlet.service(request, response);
                } else {
                    resource = new Resource();
                    resource.service(request, response);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                break;
            } finally {
                //3.5. 자원 해제

                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {

                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {

                }
                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                } catch (IOException e) {

                }
            }
        }
        //4. 자원 해제
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {

        }
    }


}






