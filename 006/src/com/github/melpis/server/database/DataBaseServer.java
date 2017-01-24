package com.github.melpis.server.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DataBaseServer{
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			serverSocket = new ServerSocket(1521);
			DataBase dataBase = new DataBase();
			while(true){
                clientSocket = serverSocket.accept();
                is = clientSocket.getInputStream();
                os = clientSocket.getOutputStream();
                MessageRequest messageRequest = new MessageRequest(is);
                ResultResponse resultResponse = new ResultResponse(os);
                dataBase.service(messageRequest,resultResponse);
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null) os.close();
			if(is != null) is.close();
			if(clientSocket != null) clientSocket.close();
			if(serverSocket != null) serverSocket.close();
		}


	}
}
