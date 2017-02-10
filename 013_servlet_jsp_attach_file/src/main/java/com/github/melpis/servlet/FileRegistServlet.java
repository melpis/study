package com.github.melpis.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.commons.fileupload.MultipartRequest;
import com.github.melpis.fileupload.manager.FileManager;
import com.github.melpis.fileupload.vo.AttachfileVO;

/**
 * Servlet implementation class FileRegistServlet
 */
public class FileRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String formViewName = null;
    private String resultViewName = null;
    private String error = null;
    private String realPath = null;
    private FileManager fileManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.formViewName = config.getInitParameter("formViewName");
        this.resultViewName = config.getInitParameter("resultViewName");
        this.error = config.getServletContext().getInitParameter("error");
        this.realPath = config.getServletContext().getInitParameter("realPath");
        this.fileManager = new FileManager();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher(this.formViewName).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MultipartRequest mRequest = null;
        AttachfileVO attachfileVO = null;
        try {
            mRequest = new MultipartRequest(request, this.realPath);
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher(this.error).forward(request, response);
            return;
        }
        attachfileVO = mRequest.getfiles("file1");

        try {
            this.fileManager.regist(attachfileVO);
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher(this.error).forward(request, response);
            return;
        }


        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
