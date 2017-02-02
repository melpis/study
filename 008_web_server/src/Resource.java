import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Resource {
	public Resource() {
		
	}

	public void service(Request request, Response response){
		//1. 데이터 추출
		String requestURI = request.getRequestURI();
		//2. 유효성 검사
		File file = new File(WebServer.SERVER_ROOT, requestURI);
		if (!file.exists()) {
			// 예외 처리
		}
		if (!file.canRead()) {
			// 예외 처리
		}
		//3. 처리
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
			int readCount = 0;
			byte[] buffer = new byte[4096];
			
			while((readCount = fis.read(buffer)) > 0) {
				response.write(buffer);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if(fis!=null)fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		//System.out.println(returnBuffer);
		//4. 결과 출력
		
		//response.write(returnBuffer);
	}
}









