package com.github.melpis.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.fileupload.manager.FileManager;
import com.github.melpis.fileupload.vo.AttachfileVO;

/**
 * Servlet implementation class FileListServlet
 */
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String resultViewName = null;
    private String error = null;
    private FileManager fileManager = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.resultViewName = config.getInitParameter("resultViewName");
		this.error  = config.getServletContext().getInitParameter("error");
		this.fileManager = new FileManager();
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AttachfileVO> paramList = null;
		
		paramList=this.fileManager.fileList();
		
		request.setAttribute("paramList", paramList);
		request.getRequestDispatcher(this.resultViewName).forward(request, response);
		
		
	}

}
