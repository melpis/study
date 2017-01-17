import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BoardDataBase {
	//경로
	private final static String path="/Users/hongsung-won/IdeaProjects/study/out/production/005";
	//seq파일
	private final static String seqFile="seq";
	//db파일
	private final static String dbFile="db";

	public BoardDataBase() {
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdir();
		}
	}
	
	private int getSystemSeq(){
		// 결과값 반환 준비
		int returnSeq = 0;
		//1. 파일 읽기
		File seqDataFile = new File(path,seqFile);
		//2. 파일 존재 여부 판단
		if(!seqDataFile.exists()){
			try {
				//3. 없다면 파일생성
				seqDataFile.createNewFile();
				returnSeq= returnSeq+1;
				//4.기록
				FileOutputStream fos = new FileOutputStream(seqDataFile);
				fos.write(returnSeq);
				fos.flush();
				//5. 닫기
				fos.close();
				return returnSeq;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//3. 있다면 파일읽기
			FileInputStream fis= new FileInputStream(seqDataFile);
			returnSeq=fis.read();
			fis.close();
			//4. 번호 증가후 기록
			returnSeq= returnSeq+1;
			FileOutputStream fos = new FileOutputStream(seqDataFile);
			fos.write(returnSeq);
			fos.flush();
			//5. 닫기
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnSeq;
	}
		
	//등록 (insert)
	public int insert(String title, String content){
		
		// 1. 문서 번호 가져오기
		int systemSeq = getSystemSeq();
		// 2. 등록일 가져오기
		Date systemDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		String sysDate = dateFormat.format(systemDate);
		// 3. 조회수 입력
		int configHit = 0;
		
		// 4. 등록
		File dataFile = new File(path,dbFile);
		if(!dataFile.exists()){
			try {
				//5. 파일이 존재 하지 않으면 생성
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw=null;
		try {
			//5. 존재하면 이어쓰기
			fw = new FileWriter(dataFile,true);
			fw.write("SEQ|"+String.valueOf(systemSeq)+",");
			fw.write("TITLE|"+title+",");
			fw.write("CONTENT|"+content+",");
			fw.write("REGIST_DATE|"+sysDate+",");
			fw.write("READ_COUNT|"+String.valueOf(configHit));
			fw.write("\n");
			fw.flush();
			//6. 닫기
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		
	return systemSeq;
	}
	
	// 한건 조회 (select)
	public Map<String,String> select(int seq){
		//결과값 반환 준비
		Map<String, String> returnResult = null;

		File file = new File(path,dbFile);
		try {
			//1. 파일 읽기
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String readLine = null;
			//2. 줄단위로 읽기
			while((readLine=br.readLine()) != null){
				String[] rows=readLine.split(",");
				String[] colums=rows[0].split("\\|");
				//3. seq가 일치하면 map에 담기
				if(colums[0].equals("SEQ") && colums[1].equals(String.valueOf(seq))){
					returnResult = new HashMap<String, String>();
					for(String resultColums:rows){
						String []resultColum=resultColums.split("\\|");
						returnResult.put(resultColum[0], resultColum[1]);
					}	
					break;
				}
			}
			//4. 닫기
			br.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	//여러건 조회 selectList
	public List<Map<String, String>> selectList(){
		//결과값 반환준비
		List<Map<String, String>> returnResult =  new ArrayList<Map<String,String>>();
		//1. 파일읽기
		File dataFile = new File(path,dbFile);
		FileReader fr = null;
		try {
			fr = new FileReader(dataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			String readLine= null;
			Map<String, String> result=null;
			//2. 줄단위로 읽기
			while((readLine=br.readLine())!=null){
				String[] rows=readLine.split(",");
				result=new HashMap<String, String>();
				//3. map에 담기
				for(String resultColums:rows){
					String []colums=resultColums.split("\\|");
					result.put(colums[0], colums[1]);
				}
				//4. 결과 값 list에 담기
				returnResult.add(result);
			}
			//5. 닫기
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	//삭제 (delete)
	public void delete(int seq){
		//1. 원래 파일 준비
		File dataFile = new File(path,dbFile);
		//2. 임시 파일 준비
		File tempFile = new File(path,"temp");
		
		FileWriter fw = null;
		BufferedWriter bw= null;
		//3. 임시 파일 생성
		if(!tempFile.exists()){
			try {
				tempFile.createNewFile();
				fw = new FileWriter(tempFile);
				bw = new BufferedWriter(fw);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileReader fileReader = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fileReader);
			String readLine = null;
			//4. 원래 파일 줄단위로 읽기
			while((readLine=br.readLine()) != null){
				String[] rows=readLine.split(",");
				String[] colums=rows[0].split("\\|");
				//5. seq 일치 하지 않으면 temp파일에 쓰기
				if(!colums[1].equals(String.valueOf(seq))){
					bw.write(readLine);
					bw.newLine();
					bw.flush();
				}
				
			}
			//6. 닫기
			br.close();
			fileReader.close();
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//7. 기존 파일삭제
		dataFile.delete();
		//8. 임시 파일->기존파일 이름으로 변경
		tempFile.renameTo(dataFile);
		
	}
	//수정 (update)
	public void update(int seq,String title,String content){
		//1. 원래 파일 준비
		File dataFile = new File(path,dbFile);
		//2. 임시 파일 준비
		File tempFile = new File(path,"temp");
		//3. 시스템 시간 
		Date systemDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		String sysDate = dateFormat.format(systemDate);
		//4. 임시파일 생성
		FileWriter fw = null;
		BufferedWriter bw= null;
		if(!tempFile.exists()){
			try {
				tempFile.createNewFile();
				fw = new FileWriter(tempFile);
				bw = new BufferedWriter(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileReader fileReader = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fileReader);
			String readLine = null;
			//5. 줄단위로 읽기
			while((readLine=br.readLine()) != null){
				String[] rows=readLine.split(",");
				String[] colums=rows[0].split("\\|");
				//6. seq가 일치 하지 않을경우 임시파일에 기록 
				if(!colums[1].equals(String.valueOf(seq))){
					bw.write(readLine);
					bw.newLine();
					bw.flush();
				}else{
				//7. seq가 일치 할경우 새로운 내용으로 임시 파일에 기록
					String hit=rows[4].split("\\|")[1];
					bw.write("SEQ|"+String.valueOf(seq)+",");
					bw.write("TITLE|"+title+",");
					bw.write("CONTENT|"+content+",");
					bw.write("REGIST_DATE|"+sysDate+",");
					bw.write("READ_COUNT|"+hit);
					bw.newLine();
					bw.flush();
				}
				
			}
			//8. 닫기
			br.close();
			fileReader.close();
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//9. 기존 파일 삭제
		dataFile.delete();
		//10. 임시 파일->기존파일 이름으로 변경
		tempFile.renameTo(dataFile);
	}
}
