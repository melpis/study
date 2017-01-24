package com.github.melpis.board;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class BoardManager {
	private BoardDataBaseAccess boardDataBase = null;
	
	public BoardManager(){
		this.boardDataBase = new BoardDataBaseAccess();
	}
	
	// 문서 등록 폼
	public void registDoumentForm() {

		// 1. 등록 폼출력
		System.out.println("제목: ");
		System.out.println("내용: ");

	}
	// 문서 등록
	public void registDocument(String[] outputData) {
		// 1.1 제목 추출
		String userInputTitle = outputData[1];
		// 1.2 내용 추출
		String userInputContent = outputData[2];
		// 2. 유효성 검사
		if (userInputTitle.length() < 1 && userInputTitle.length() > 600) {
			return;
		}
		if (userInputContent.length() < 1 && userInputContent.length() > 2000) {
			return;
		}
		
		// 3. 등록 
		try {
			// 3.1 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 3.2 문서 관리 번호 가져오는 명령
		String statement="getseq";
		String resultSeq = null;
		try {
			// 3.3 실행
			this.boardDataBase.executeQuery(statement);
			// 3.4 결과 
			resultSeq = this.boardDataBase.getResult();
			// 3.5 접속 
			this.boardDataBase.connetion();
			// 3.6 데이터를 등록하는 명령어
			statement="insert SEQ, TITLE, CONTENT, REGIST_DATE, HIT \n" +
			"from db \n" +
			"values "+resultSeq+","+userInputTitle+","+userInputContent+","+"sysdate"+","+"0";
			// 3.7 실행
			this.boardDataBase.execute(statement);
			// 3.8 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 4. 결과 출력
		System.out.println("등록 완료");

	}
	
	// 문서 상세 보기 
	public void viewDocument(String[] outputData){
		//1. 사용자가 입력한 문서 번호 가져오기
		String userInputSeq = outputData[1];
		//1.1 변환
		int parameterSeq = Integer.parseInt(userInputSeq);
		
		try {
			//2.1 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2.2 데이터를 가져오는 명령어 
		String statement ="search SEQ, TITLE, CONTENT, REGIST_DATE, HIT \n" +
		"from db\n" +
		"where SEQ="+parameterSeq;
		
		try {
			//2.3 실행
			this.boardDataBase.executeQuery(statement);
			//2.4 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 3.1 결과값 받고 출력
		if(this.boardDataBase.hasNext()){
			System.out.println("글번호 : " +this.boardDataBase.getResult("SEQ"));
			System.out.println("제목  : " +this.boardDataBase.getResult("TITLE"));
			System.out.println("등록일 : " +this.boardDataBase.getResult("REGIST_DATE"));
			System.out.println("조회수 : " +this.boardDataBase.getResult("HIT"));
			System.out.println("내용 : " +this.boardDataBase.getResult("CONTENT"));
		
		}
	}
	

	public void viewDocumentList(){
		
		try {
			//1. 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//2. 데이터를 가져오는 명령어 
		String statement ="search SEQ, TITLE, CONTENT, REGIST_DATE, HIT \n" +
		"from db";
		
		try {
			//3. 실행
			this.boardDataBase.executeQuery(statement);
			//4. 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//5. 결과 값 출력
		while(this.boardDataBase.hasNext()){
			System.out.println("글번호 : " +this.boardDataBase.getResult("SEQ"));
			System.out.println("제목  : " +this.boardDataBase.getResult("TITLE"));
			System.out.println("등록일 : " +this.boardDataBase.getResult("REGIST_DATE"));
			System.out.println("조회수 : " +this.boardDataBase.getResult("HIT"));
			System.out.println("내용 : " +this.boardDataBase.getResult("CONTENT"));
		
		}

	}

	public void deleteDocument(String[] outputData) {
		//1. 사용자가 입력한 문서 번호 가져오기
		String userInputSeq = outputData[1];
		//1.1 변환
		int parameterSeq = Integer.parseInt(userInputSeq);
		
		try {
			//2. 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//3. 데이터를 삭제하기 위한 명령어
		String statement ="remove \n" +
		"from db\n" +
		"where SEQ="+parameterSeq;
		try {
			//4. 실행
			this.boardDataBase.execute(statement);
			//5. 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("삭제 되었습니다");

	}

	public void editDocumentForm(String[] outputData) throws ParseException {
		//1. 사용자가 입력한 문서 번호 가져오기
		String userInputSeq = outputData[1];
		//1.1 변환
		int parameterSeq = Integer.parseInt(userInputSeq);
		
		try {
			//2.1 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2.2 데이터를 가져오는 명령어 
		String statement ="search SEQ, TITLE, CONTENT, REGIST_DATE, HIT \n" +
		"from db\n" +
		"where SEQ="+parameterSeq;
		
		try {
			//2.3 실행
			this.boardDataBase.executeQuery(statement);
			//2.4 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 3.1 결과값 받고 출력
		if(this.boardDataBase.hasNext()){
			System.out.println("글번호 : " +this.boardDataBase.getResult("SEQ"));
			System.out.println("제목  : " +this.boardDataBase.getResult("TITLE"));
			System.out.println("등록일 : " +this.boardDataBase.getResult("REGIST_DATE"));
			System.out.println("조회수 : " +this.boardDataBase.getResult("HIT"));
			System.out.println("내용 : " +this.boardDataBase.getResult("CONTENT"));
		}
	}

	public void editDocument(String[] outputData) {
		//1.1 번호 추출
		String userInputSeq = outputData[1];
		//1.2 제목 추출  
		String userInputTitle = outputData[2];
		//1.3 내용 추출
		String userInputContent = outputData[3];
		//1.4 변환
		int parameterSeq = Integer.parseInt(userInputSeq);
		
		try {
			//2. 접속
			this.boardDataBase.connetion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//3. 데이터를 수정하기 위한 명령어
		String statement="modify TITLE="+userInputTitle+","+"CONTENT="
						+userInputContent+","+"REGIST_DATE=sysdate \n" +
						"from db \n" +
						"where SEQ="+parameterSeq;
		try {
			//4. 실행
			this.boardDataBase.execute(statement);
			//5. 닫기
			this.boardDataBase.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("수정완료 하였습니다");

	}
	
}
