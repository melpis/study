import java.io.IOException;
import java.util.Scanner;

public class BoardExecuter {

	
	private final static String HELP_MESSAGE="본 시스템은 게시물 관리 시스템입니다.\n" +
												"아직 초기 버전이라 불편한 점이 아주 많이 있습니다.\n" +
												"참기 힘드신 분은 빨간 버튼을 눌러 주세요\n" +
												"사용예:\n" +
												"목록: LT|\n" +
												"상세 조회: VW|3\n" +
												"삭제: DT|3\n" +
												"수정폼: EF|3\n" +
												"수정 처리: EP|3|제목|내용\n" +
												"등록폼: RF|\n" +
												"등록 처리: RP|제목|내용\n" ;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

		Board board = new Board("TB_BOARD");

		Scanner scanner = null;

		try {

			while(true) {
                scanner = new Scanner(System.in);
                IOUtil.print(HELP_MESSAGE);
                String userInput = scanner.nextLine();
                String[] aryUserInput = userInput.split("\\|");
                String request = aryUserInput[0];

                if (request.equals("LT")) {
                    board.list();
                } else if (request.equals("VW")) {
                    board.view(aryUserInput);
                } else if (request.equals("DT")) {
                    board.delete(aryUserInput);
                } else if (request.equals("EF")) {
                    board.editRequest( aryUserInput);
                } else if (request.equals("EP")) {
                    board.edit(aryUserInput);
                } else if (request.equals("RF")) {
                    board.registerRequest();
                } else if (request.equals("RP")) {
                    board.register(aryUserInput);
                }else {
                    break;
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		}

	}
}