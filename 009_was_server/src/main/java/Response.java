

import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private OutputStream outputStream;
	
	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void write(String data) {
		write(data.getBytes());
	}

	public void write(byte[] data) {
		try {
			this.outputStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}












