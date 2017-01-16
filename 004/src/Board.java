import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private final static String LIST_HEADER="번호\t제목\t\t\t\t내용\t\t\t\t\t\t\t\t등록일\t\t\t\t조회수\n";


	private String tableName;
	private DB bitDB;
	
	
	public Board(String tableName)
	{
		this.tableName = tableName;
		this.bitDB = new DB();
		this.bitDB.createTable(this.tableName);
		
		
	}
	
	
	
	public  void list()
	{
		//1. 목록 요청(LT|)
		//2. 목록 데이터 생성
		List<Map<String, String>> boardList = this.bitDB.get(tableName);
		//3. 목록 출력
		if (boardList != null) {
			IOUtil.print(LIST_HEADER);
			for (int indexI = 0; indexI < boardList.size(); indexI++) {
				Map<String, String> board = boardList.get(indexI);
				String seq = board.get("SEQ");
				String title = board.get("TITLE");
				String content = board.get("CONTENT");
				String registDate = board.get("REGIST_DATE");
				String readCount = board.get("READ_COUNT");
				System.out.println(""+seq+"\t"+title+"\t\t\t\t"+content+"\t\t\t\t\t\t\t\t"+registDate+"\t\t\t"+readCount+"");
			}
		}
	}
	
	public  void view(String [] aryUserInput)
	{
		//4. 상세 조회 요청(VW|3)
		//5. 해당 게시물 데이터 생성
		//5.1. 추출
		String seq = aryUserInput[1];
		//5.2. 유효성 검사
		if (seq != null && !seq.equals("")) {
			
		}
		//5.3. 처리
		List<Map<String, String>> boardList = this.bitDB.get(tableName,"SEQ",seq);
		Map<String,String> board = null;
		
		if(boardList.size() == 1){
			board=boardList.get(0);
		}
		
	
		//조회수 증가
		Integer readCount=Integer.parseInt(board.get("READ_COUNT"))+1;
		
		board.put("READ_COUNT",readCount.toString());
		//6. 상세 조회 출력
		
		if (board != null) {
			System.out.println("번호: " + board.get("SEQ"));
			System.out.println("제목: " + board.get("TITLE"));
			System.out.println("내용: " + board.get("CONTENT"));
			System.out.println("등록일: " + board.get("REGIST_DATE"));
			System.out.println("조회수: " + board.get("READ_COUNT"));
		}
		
	}
		
	public  void delete(String[] aryUserInput){
		//7. 삭제 요청(DT|3)
		//1. 추출
		String seq = aryUserInput[1];
		//2. 유효성 검사
		if (seq != null && !seq.equals("")) {
			
		}
		//3. 삭제 처리
		this.bitDB.remove(tableName, "SEQ",seq);
		//9. 삭제 결과 출력
		System.out.println("삭제 되었습니다");
	}
	
	public  void editRequest(String[] aryUserInput){
		//10. 수정 요청(EF|3)
		//1. 추출
		String seq = aryUserInput[1];
		//2. 유효성 검사
		if (seq != null && !seq.equals("")) {
			
		}
		//3. 처리
		List<Map<String, String>> boardList = this.bitDB.get(tableName,"SEQ",seq);
		Map<String, String> board = null;

		//조회수 증가
		Integer readCount=Integer.parseInt(board.get("READ_COUNT"))+1;
		
		board.put("READ_COUNT",readCount.toString());
		
		//11. 해당 게시물 데이터 생성
		//12. 수정 폼 출력
		if (board != null) {
			System.out.println("번호: " + board.get("SEQ"));
			System.out.println("제목: " + board.get("TITLE"));
			System.out.println("내용: " + board.get("CONTENT"));
			System.out.println("등록일: " + board.get("REGIST_DATE"));
			System.out.println("조회수: " + board.get("READ_COUNT"));
		}

		
	}
	public  void registerRequest(){
		//17. 등록 요청(RF|)
		//18. 등록 폼 출력
		System.out.println("제목 : ");
		System.out.println("내용 : \n\n");
	}
	
	public  void register(String[] aryUserInput)
	{
		//19. 입력(RP|제목|내용)
		//20. 등록 처리 요청			
		//21. 등록 처리
		//1.추출
		String title= aryUserInput[1];
		String content= aryUserInput[2];
		 //2.유효성검사
		if(title != null && content !=null && !title.equals("") && !content.equals("")){
				
		}
		Map<String, String> board = new HashMap<String, String>();
		Integer seq=this.bitDB.get(tableName).size();
		
		board.put("SEQ",seq.toString());
		board.put("TITLE", title);
		board.put("CONTENT", content);
		String cal= IOUtil.date();
		board.put("REGIST_DATE", cal);
		board.put("READ_COUNT","0");
		//3. 처리
		this.bitDB.add(tableName,board);
//		//22. 등록 처리 결과 출력
		if (board != null) {
			System.out.println("번호: " + board.get("SEQ"));
			System.out.println("제목: " + board.get("TITLE"));
			System.out.println("내용: " + board.get("CONTENT"));
			System.out.println("등록일: " + board.get("REGIST_DATE"));
			System.out.println("조회수: " + board.get("READ_COUNT"));
		}
	}
	public  void edit(String[] aryUserInput)
	{
		//13. 입력(EP|3|제목|내용)
		//14. 수정 처리 요청
		//15. 수정 처리
		//1. 추출
		String seq = aryUserInput[1];
		String title = aryUserInput[2];
		String content = aryUserInput[3];
		//2.유효성검사
		if(seq != null && title != null && content !=null && !seq.equals("") && !title.equals("") && !content.equals("")){
			
		}
		//3. 처리
		Map<String, String> board = new HashMap<String, String>();
		board.put("SEQ", seq);
		board.put("TITLE",title);
		board.put("CONTENT",content);
		String cal= IOUtil.date();
		board.put("REGIST_DATE", cal);
		//조회수 증가
		Integer readCount=Integer.parseInt(board.get("READ_COUNT"))+1;
		
		board.put("READ_COUNT",readCount.toString());
		this.bitDB.set(tableName,board,"SEQ",seq);
		
		//16. 수정 처리 결과 출력
		if (board != null) {
			System.out.println("번호: " + board.get("SEQ"));
			System.out.println("제목: " + board.get("TITLE"));
			System.out.println("내용: " + board.get("CONTENT"));
			System.out.println("등록일: " + board.get("REGIST_DATE"));
			System.out.println("조회수: " + board.get("READ_COUNT"));
		}
	}
}
