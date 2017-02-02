import java.io.IOException;
import java.io.InputStream;

public class Request {
	private InputStream inputStream;
	private String URI;
	
	public Request(InputStream inputStream) throws IOException
	{
	
		this.inputStream  = inputStream;
		readAndParse();
		
	}
	
	
	private void readAndParse() throws IOException{
		//1. 데이터추출(HTTP Header)
		byte [] buf=new byte[4096];
		this.inputStream.read(buf);
		
		String http = new String(buf);
		//2. 유효성검사(null or 유효한 http header )
		if(http==null)
		{
		}
		//3. 처리(http 규약에 따른 의미 부여 )
		System.out.println(new String(buf));
		String[] httpHead=http.split("/n");
		httpHead=httpHead[0].split(" ");
		//4. 결과 출력
		URI=httpHead[1];
	}


	public String getRequestURI() {
		// TODO Auto-generated method stub
		return URI;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
