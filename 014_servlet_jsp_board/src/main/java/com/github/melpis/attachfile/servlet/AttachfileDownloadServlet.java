package com.github.melpis.attachfile.servlet;

import com.github.melpis.attachfile.manager.AttachfileManager;
import com.github.melpis.attachfile.vo.AttachfileVO;
import com.github.melpis.commons.file.FileUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AttachfileDownloadServlet
 */
public class AttachfileDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AttachfileManager attachfileManager = null;
    private String errorViewName = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttachfileDownloadServlet() {
        super();

    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");
        this.attachfileManager = new AttachfileManager();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String sSeq = request.getParameter("seq");

        int seq = 0;
        try {
            seq = Integer.parseInt(sSeq);
        } catch (Exception e) {
            request.setAttribute("errorViewName", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }


        AttachfileVO attachfileVO = this.attachfileManager.downloadAttachfile(seq);
        FileUtil.downloadFile(response, "C:/upload/", attachfileVO);


    }

}
