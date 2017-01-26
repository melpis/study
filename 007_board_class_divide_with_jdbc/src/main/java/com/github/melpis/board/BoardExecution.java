package com.github.melpis.board;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class BoardExecution {


	public static void main(String[] args) throws IOException, ParseException {

		// 문서 등록 폼 Register Document Form
		// 문서 등록 Register Document
		// 문서 상세 보기 view Document
		// 문서 목록 보기 view Document List
		// 문서 삭제 Delete Document
		// 문서 수정 폼 Edit Document Form
		// 문서 수정 Edit Document

		// 1. 사용법 출력
		System.out.println("문서 등록 폼: ex) RF");
		System.out.println("문서 등록: ex) RD:제목:내용 ");
		System.out.println("문서 상세 보기: ex) VD:문서번호 ");
		System.out.println("문서 목록 보기: ex) VL ");
		System.out.println("문서 삭제: ex) DD:문서번호 ");
		System.out.println("문서 수정폼: ex) EF:문서번호 ");
		System.out.println("문서 수정: ex) ED:문서번호:제목:내용 ");

		Scanner scanner = null;
		BoardManager boardManager = new BoardManager();

		try {
			scanner = new Scanner(System.in);

			while (true) {
				// 2. 사용자 입력
				String userInputData = scanner.nextLine();

				// 3. 데이터 추출
				// 3.1 데이터 구분하기
				String outputData[] = userInputData.split("\\:");

				// 3.2 사용자의 의도 알아 내기
				String userAction = outputData[0].toUpperCase();


				if (userAction.equals("RF")) {
					boardManager.registDoumentForm();
				} else if (userAction.equals("RD")) {
					boardManager.registDocument(outputData);
				} else if (userAction.equals("VD")) {
					boardManager.viewDocument(outputData);
				} else if (userAction.equals("VL")) {
					boardManager.viewDocumentList();
				} else if (userAction.equals("DD")) {
					boardManager.deleteDocument(outputData );
				} else if (userAction.equals("EF")) {
					boardManager.editDocumentForm(outputData);
				} else if (userAction.equals("ED")) {
					boardManager.editDocument(outputData);
				} else {
					System.out.println("잘못 입력하셨습니다");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		}

	}
	
	
	



}
