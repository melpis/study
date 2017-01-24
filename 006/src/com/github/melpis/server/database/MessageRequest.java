package com.github.melpis.server.database;

import java.io.IOException;
import java.io.InputStream;

public class MessageRequest {
	private InputStream is = null;
	private String userRequest = null;
	private String userMessage= null;
	
	public MessageRequest(InputStream is) throws IOException {
		this.is= is;
		readAndParse();
	}
	
	private void readAndParse() throws IOException {
		byte[] buffer = new byte[4096];
		is.read(buffer);
		
		String message = new String(buffer);
	
		String tempUserRequest = message.substring(0, 7);
		this.userRequest= tempUserRequest.trim();
		
		this.userMessage= message.substring(7);
		
	 }

	public String getUserRequest(){
		return this.userRequest.toLowerCase();
	}
	
	public String getUserMessage(){
		return this.userMessage;
	}
}
