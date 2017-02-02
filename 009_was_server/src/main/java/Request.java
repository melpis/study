

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {
	private InputStream inputStream;
	private String requestURI;
	private String method;
	private String httpVersion;
	private Map<String, String> parameters;
	private Map<String, String> headers;

	public Request(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		readAndParse();
	}
	
	private void readAndParse() throws IOException {
		//1. 데이터 추출(Http header)

		int readCount = 0;
		byte[] buffer = new byte[4096];
		readCount = this.inputStream.read(buffer);
		System.out.println("##" + new String(buffer, 0, readCount) + "##");
		//2. 유효성 검사(null || http validate)
		if (readCount == 0) {
			
		}
		//3. 처리 (http 규약에 따른 의미 부여)
		String header = new String(buffer, 0, readCount);
		String[] aryHeader = header.split(System.getProperty("line.separator"));
		
		parseFirst(aryHeader[0]);
		parseHeader(aryHeader);
		parsePostParameter(aryHeader);
		
		//4. 결과 출력
	}
	private void parsePostParameter(String[] aryHeader) {
		if (!this.method.equals("POST")) {
			return;
		}
		String postParameter = aryHeader[aryHeader.length - 1];
		parseParameter(postParameter);
	}	

	private void parseHeader(String[] aryHeader) {
		if (aryHeader.length < 2) {
			return;
		}
		this.headers = new HashMap<String, String>();
		for (int indexI = 1; indexI < aryHeader.length; indexI++) {
			int startIndex = aryHeader[indexI].indexOf(':');
			if (startIndex > -1) {
				String key = aryHeader[indexI].substring(0, startIndex);
				String value = aryHeader[indexI].substring(startIndex + 1);
				
				this.headers.put(key, value);
			}
		}
	}

	private void parseFirst(String first) {
		String[] aryFirst = first.split(" ");
		
		if (aryFirst.length != 3) {
			// 예외 처리
			return;
		}
		this.method = aryFirst[0];
		this.httpVersion = aryFirst[2];
		int qIndex = aryFirst[1].indexOf('?');
		if (qIndex > -1) {
			this.requestURI = aryFirst[1].substring(0, qIndex);
			parseParameter(aryFirst[1].substring(qIndex + 1));
		} else {
			this.requestURI = aryFirst[1];
		}
	}
	
	private void parseParameter(String param) {
		String[] items = param.split("&");
		if (items.length == 0) {
			return;
		}
		this.parameters = new HashMap<String, String>();
		for (int indexI = 0; indexI < items.length; indexI++) {
			String[] item = items[indexI].split("=");
			if (item.length == 2) {
				this.parameters.put(item[0], item[1]);
			}
		}
	}
	
	public String getRequestURI() {
		return requestURI;
	}
	
	public String getParameter(String key) {
		String returnValue = null;
		//1.데이터 추출
		//2.유효성 검사
		if (key == null || key.trim().equals("")) {
			return returnValue;
		}
		//3.처리
		if (this.parameters.containsKey(key)) {
			returnValue = this.parameters.get(key);
		}
		//4.결과 출력
		return returnValue;
	}

	public String getHeader(String key) {
		String returnValue = null;
		//1.데이터 추출
		//2.유효성 검사
		if (key == null || key.trim().equals("")) {
			return returnValue;
		}
		//3.처리
		if (this.headers.containsKey(key)) {
			returnValue = this.headers.get(key);
		}
		//4.결과 출력
		return returnValue;
	}
	
}














