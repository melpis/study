import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.*;

public class BoardExecuter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// DB 준비
		Map<String, List<Map<String, String>>> db = null;
		db = new HashMap<String, List<Map<String, String>>>();

		List<Map<String, String>> dbBoardList = new ArrayList<>();
		List<Map<String, String>> dbMemberList = new ArrayList<>();
		// 테이블 생성
		db.put("TB_BOARD", dbBoardList);
		db.put("TB_MEMBER", dbMemberList);

		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			//등록일
			String calendar;
			Calendar cal = Calendar.getInstance();
			calendar = cal.get(cal.YEAR) + "년" + (cal.get(cal.MONTH) + 1) + "월" + cal.get(cal.DATE) + "일" + cal.get(cal.HOUR) + ":"
					+ cal.get(cal.MINUTE) + ":" + cal.get(cal.SECOND);

			System.out.println("본 시스템은 게시물 관리 시스템입니다.");
			System.out.println("아직 초기 버전이라 불편한 점이 아주 많이 있습니다.");
			System.out.println("참기 힘드신 분은 빨간 버튼을 눌러 주세요.");
			System.out.println("사용예:");
			System.out.println("목록: LT|");
			System.out.println("상세 조회: VW|3");
			System.out.println("삭제: DT|3");
			System.out.println("수정폼: EF|3");
			System.out.println("수정 처리: EP|3|제목|내용");
			System.out.println("등록폼: RF|");
			System.out.println("등록 처리: RP|제목|내용");

			while (true) {

				String userInput = scanner.nextLine();
				String[] aryUserInput = userInput.split("\\|");
				String request = aryUserInput[0];


				if (request.equals("LT")) {
					//1. 목록 요청(LT|)
					//2. 목록 데이터 생성
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					//3. 목록 출력
					if (boardList != null) {
						System.out.println("번호\t제목\t\t\t\t내용\t\t\t\t\t\t\t\t등록일\t\t\t\t조회수");
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> board = boardList.get(indexI);
							String seq = board.get("SEQ");
							String title = board.get("TITLE");
							String content = board.get("CONTENT");
							String registDate = board.get("REGIST_DATE");
							String readCount = board.get("READ_COUNT");
							System.out.println("" + seq + "\t" + title + "\t\t\t\t" + content + "\t\t\t\t\t\t\t\t" + registDate + "\t\t\t" + readCount + "");
						}
					}
				} else if (request.equals("VW")) {
					//4. 상세 조회 요청(VW|3)
					//5. 해당 게시물 데이터 생성
					//1. 추출
					String seq = aryUserInput[1];
					//2. 유효성 검사
					if (seq != null && !seq.equals("")) {

					}
					//3. 처리
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					Map<String, String> board = null;

					if (boardList != null) {
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> temp = boardList.get(indexI);
							if (temp.get("SEQ").equals(seq)) {
								board = temp;
								break;
							}
						}
					}
					//조회수 증가
					Integer readCount = Integer.parseInt(board.get("READ_COUNT")) + 1;

					board.put("READ_COUNT", readCount.toString());
					//6. 상세 조회 출력

					if (board != null) {
						System.out.println("번호: " + board.get("SEQ"));
						System.out.println("제목: " + board.get("TITLE"));
						System.out.println("내용: " + board.get("CONTENT"));
						System.out.println("등록일: " + board.get("REGIST_DATE"));
						System.out.println("조회수: " + board.get("READ_COUNT"));
					}
				} else if (request.equals("DT")) {
					//7. 삭제 요청(DT|3)
					//1. 추출
					String seq = aryUserInput[1];
					//2. 유효성 검사
					if (seq != null && !seq.equals("")) {

					}
					//3. 삭제 처리
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					Map<String, String> board = null;
					if (boardList != null) {
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> temp = boardList.get(indexI);
							if (temp.get("SEQ").equals(seq)) {
								board = temp;
								boardList.remove(indexI);
								break;
							}
						}
					}
					//9. 삭제 결과 출력
					if (board != null) {
						System.out.println("번호: " + board.get("SEQ"));
						System.out.println("제목: " + board.get("TITLE"));
						System.out.println("내용: " + board.get("CONTENT"));
						System.out.println("등록일: " + board.get("REGIST_DATE"));
						System.out.println("조회수: " + board.get("READ_COUNT"));
					}
				} else if (request.equals("EF")) {
					//10. 수정 요청(EF|3)
					//1. 추출
					String seq = aryUserInput[1];
					//2. 유효성 검사
					if (seq != null && !seq.equals("")) {

					}
					//3. 처리
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					Map<String, String> board = null;

					if (boardList != null) {
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> temp = boardList.get(indexI);
							if (temp.get("SEQ").equals(seq)) {
								board = temp;
								break;
							}
						}
					}
					//조회수 증가
					Integer readCount = Integer.parseInt(board.get("READ_COUNT")) + 1;

					board.put("READ_COUNT", readCount.toString());

					//11. 해당 게시물 데이터 생성
					//12. 수정 폼 출력
					if (board != null) {
						System.out.println("번호: " + board.get("SEQ"));
						System.out.println("제목: " + board.get("TITLE"));
						System.out.println("내용: " + board.get("CONTENT"));
						System.out.println("등록일: " + board.get("REGIST_DATE"));
						System.out.println("조회수: " + board.get("READ_COUNT"));
					}
				} else if (request.equals("EP")) {
					//13. 입력(EP|3|제목|내용)
					//14. 수정 처리 요청
					//15. 수정 처리
					//1. 추출
					String seq = aryUserInput[1];
					String title = aryUserInput[2];
					String content = aryUserInput[3];
					//2.유효성검사
					if (seq != null && title != null && content != null && !seq.equals("") && !title.equals("") && !content.equals("")) {

					}
					//3. 처리
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					Map<String, String> board = null;

					if (boardList != null) {
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> temp = boardList.get(indexI);
							if (temp.get("SEQ").equals(seq)) {
								board = temp;
								break;
							}
						}
					}
					board.put("SEQ", seq);
					board.put("TITLE", title);
					board.put("CONTENT", content);
					board.put("REGIST_DATE", calendar);
					//16. 수정 처리 결과 출력
					if (board != null) {
						System.out.println("번호: " + board.get("SEQ"));
						System.out.println("제목: " + board.get("TITLE"));
						System.out.println("내용: " + board.get("CONTENT"));
						System.out.println("등록일: " + board.get("REGIST_DATE"));
						System.out.println("조회수: " + board.get("READ_COUNT"));
					}
				} else if (request.equals("RF")) {
					//17. 등록 요청(RF|)
					//18. 등록 폼 출력
					System.out.println("제목 : ");
					System.out.println("내용 : \n\n");
				} else if (request.equals("RP")) {
					//19. 입력(RP|제목|내용)
					//20. 등록 처리 요청
					//21. 등록 처리
					//1.추출
					String title = aryUserInput[1];
					String content = aryUserInput[2];
					//2.유효성검사
					if (title != null && content != null && !title.equals("") && !content.equals("")) {

					}

					//3. 처리
					List<Map<String, String>> boardList = db.get("TB_BOARD");
					Map<String, String> board = new HashMap<String, String>();
					Integer max = 0;
					if (boardList != null) {
						for (int indexI = 0; indexI < boardList.size(); indexI++) {
							Map<String, String> temp = boardList.get(indexI);
							max = Integer.parseInt(temp.get("SEQ")) < max ? max : Integer.parseInt(temp.get("SEQ"));
						}
						max += 1;

					}

					board.put("SEQ", max.toString());
					board.put("TITLE", title);
					board.put("CONTENT", content);
					board.put("REGIST_DATE", calendar);
					board.put("READ_COUNT", "0");
					boardList.add(board);
					//22. 등록 처리 결과 출력
					if (board != null) {
						System.out.println("번호: " + board.get("SEQ"));
						System.out.println("제목: " + board.get("TITLE"));
						System.out.println("내용: " + board.get("CONTENT"));
						System.out.println("등록일: " + board.get("REGIST_DATE"));
						System.out.println("조회수: " + board.get("READ_COUNT"));
					}
				} else {
					break;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(scanner != null) scanner.close();
		}

	}
}