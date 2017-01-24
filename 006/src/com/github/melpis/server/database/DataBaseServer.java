package com.github.melpis.server.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DataBaseServer{
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1521);
		
		while(true){
			Socket clientSocket=serverSocket.accept();
			InputStream  is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			MessageRequest messageRequest = new MessageRequest(is);
			
			ResultResponse resultResponse = new ResultResponse(os);
			
			DataBase dataBase = new DataBase();
			
			dataBase.service(messageRequest,resultResponse);
			os.close();
			is.close();
			clientSocket.close();
			
			
		}
		
		
	}
}
