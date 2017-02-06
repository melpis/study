package com.github.melpis.board.manager;

import java.util.List;
import java.util.Map;

import com.github.melpis.board.dao.BoardDAO;
import com.github.melpis.board.vo.BoardVO;

public class BoardManager {
	//데이터 정의

	private BoardDAO boardDAO = null;

	public BoardManager(Map<String,String> dbInfo)
	{
		if(this.boardDAO == null){
			this.boardDAO = new BoardDAO(dbInfo);
		}
	}


	public List<BoardVO> boardList(BoardVO paramBoard) {
		List<BoardVO> returnBoardList= null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(paramBoard.getPage() < 1)
		{
			paramBoard.setPage(1);
		}
		//3. 처리
		//페이징 계산
		//3.1
		returnBoardList=this.boardDAO.selectBoardList(paramBoard);
		//4. 결과 출력
		return returnBoardList;
	}


	public int registerBoard(BoardVO boardVO) {
		int returnSeq = 0;
		//1. 데이터 추출
		if(boardVO == null){

		}
		//2. 유효성 검사
		if(boardVO.getTitle() == null || boardVO.getTitle().length() == 0 || boardVO.getTitle().length() > 100){
			// 예외처리
		}if(boardVO.getContent() == null || boardVO.getContent().length() == 0 || boardVO.getContent().length() > 4000){
			// 예외처리
		}
		//3. 처리

		boardVO=this.boardDAO.selectSeq(boardVO);
		//3.2. insert 실행
		returnSeq=this.boardDAO.insertBoard(boardVO);

		//4. 결과 출력
		return returnSeq;

	}



	public boolean removeBoard(int seq) {
		boolean returnResult=false;
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){

		}
		returnResult=this.boardDAO.deleteBoard(seq);

		//4. 결과 출력

		return returnResult;
	}



	public BoardVO readBoard(int seq) {
		BoardVO returnBoard = null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){

		}
		//3. 처리
		this.boardDAO.updateReadCount(seq);
		returnBoard = getBoard(seq);


		//4. 결과 출력
		return returnBoard;
	}



	public BoardVO getBoard(int seq) {
		BoardVO returnBoard = null;
		//1. 데이터 추출
		//2. 유효성 검사
		if(seq < 1){
			//예외처리
		}
		//3. 처리
		returnBoard=this.boardDAO.selectBoard(seq);
		//4. 결과 출력
		return returnBoard;
	}



	public void editBoard(BoardVO boardVO) {
		//1. 데이터 추출
		if(boardVO == null){
			//예외처리
		}
		//2. 유효성검사
		if(boardVO.getSeq() < 1){
			//예외 처리
		}
		if(boardVO.getTitle() == null || boardVO.getTitle().length() == 0 || boardVO.getTitle().length() > 100){
			// 예외처리
		}if(boardVO.getContent() == null || boardVO.getContent().length() == 0 || boardVO.getContent().length() > 4000){
			// 예외처리
		}
		//3. 처리
		//3.1 update 실행
		this.boardDAO.updateBoard(boardVO);

	}

	public int totalPageCount(BoardVO paramBoard){
		int returnpage=0;

		returnpage=this.boardDAO.selectTotalpage(paramBoard);
		return returnpage;
	}

	//getter and setter

}
