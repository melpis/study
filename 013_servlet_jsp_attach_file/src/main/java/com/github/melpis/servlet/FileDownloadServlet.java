package com.github.melpis.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.commons.file.FileUtil;
import com.github.melpis.fileupload.manager.FileManager;
import com.github.melpis.fileupload.vo.AttachfileVO;

/**
 * Servlet implementation class FileDownloadServlet
 */
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String error = null;   
    private String realPath = null;
    private FileManager fileManager = null;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.error= config.getServletContext().getInitParameter("error");
		this.realPath= config.getServletContext().getInitParameter("realPath");
	
		this.fileManager= new FileManager();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sSeq = request.getParameter("seq");
		
		int seq  = 0;
		try{
			seq = Integer.parseInt(sSeq);
		}catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher(this.error).forward(request, response);
			return;
		}
		
		
		AttachfileVO attachfileVO= this.fileManager.downloadFile(seq);
		FileUtil.downloadFile(response, this.realPath, attachfileVO);
		
		
		
	}

}
