package com.github.melpis.board.servlet;

import com.github.melpis.attachfile.manager.AttachfileManager;
import com.github.melpis.attachfile.vo.AttachfileVO;
import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;
import net.bit.lecture.commons.fileupload.MultipartRequest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class BoardRegistServlet
 */
public class BoardRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String formViewName = null;
    private String resultViewName = null;
    private String errorViewName = null;
    private String sessionUserId = null;
    private BoardManager boardManager = null;
    private AttachfileManager attachfileManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.sessionUserId = config.getServletContext().getInitParameter("sessionUserId");
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");
        this.formViewName = config.getInitParameter("formViewName");
        this.resultViewName = config.getInitParameter("resultViewName");
        this.boardManager = new BoardManager();
        this.attachfileManager = new AttachfileManager();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(this.sessionUserId) == null) {
            try {
                throw new Exception("로그인이 필요해요");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }

        }


        // TODO Auto-generated method stub
        String sPage = request.getParameter("page");

        BoardVO paramBoard = new BoardVO();
        paramBoard.setPage(Integer.parseInt(sPage));

        request.getRequestDispatcher(this.formViewName).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("EUC-KR");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sessionUserId") == null) {
            try {
                throw new Exception("로그인이 필요해요");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }

        }


        MultipartRequest mRequest = null;
        try {
            mRequest = new MultipartRequest(request, "C:/upload/");
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }
        BoardVO board = new BoardVO();
        String userId = (String) session.getAttribute("sessionUserId");
        board.getMember().setUserId(userId);


        String title = mRequest.getParamters("title");
        board.setTitle(title);
        String content = mRequest.getParamters("content");
        board.setContent(content);


        List<AttachfileVO> attachfileList = new ArrayList<AttachfileVO>();
        AttachfileVO file1 = mRequest.getfiles("file1");
        AttachfileVO file2 = mRequest.getfiles("file2");
        attachfileList.add(file1);
        attachfileList.add(file2);


        if (board.getMember().getUserId() == null) {

        }

        if (board.getTitle() == null || board.getTitle().length() < 2 || board.getTitle().length() > 200) {

        }

        if (board.getContent() == null || board.getContent().length() < 2 || board.getContent().length() > 4000) {

        }
        try {
            this.boardManager.registBoard(board);
            this.attachfileManager.registAttachfileList(board.getSeq(), attachfileList);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }


        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
