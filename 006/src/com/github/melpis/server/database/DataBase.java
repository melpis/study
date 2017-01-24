package com.github.melpis.server.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataBase {
	//경로
	private final static String PATH="c:/db/";

	private final static String SEQ_FILE="seq";

	// 명령어
	private final static String INSERT="insert";
	private final static String MODIFY="modify";
	private final static String REMOVE="remove";
	private final static String SEARCH="search";
	private final static String GETSEQ="getseq";
	
	private final static String SYSTEM_DATE="sysdate";
	
	// 구분자
	private final static String COLUMN_SEPARAROR="|"; 
	
	public DataBase() {
		
	}
	
	//seq파일
	
	private String getSystemSeq(){
		// 결과값 반환 준비
		String returnSeq = null;
		//1. 파일 읽기
		File seqDataFile = new File(PATH,SEQ_FILE);
		//2. 파일 존재 여부 판단
		if(!seqDataFile.exists()){
			try {
				//3. 없다면 파일생성
				seqDataFile.createNewFile();
				int seq= 1;
				//4.기록
				FileOutputStream fos = new FileOutputStream(seqDataFile);
				fos.write(seq);
				fos.flush();
				//5. 닫기
				fos.close();
				returnSeq = String.valueOf(seq);
				return returnSeq;
			} catch (IOException e) {
				e.printStackTrace();
				return "파일 없음";
			}catch (NumberFormatException e) {
				return "오류";
			}
		}
		
		try {
			//3. 있다면 파일읽기
			FileInputStream fis= new FileInputStream(seqDataFile);
			int seq=fis.read();
			fis.close();
			//4. 번호 증가후 기록
			seq= seq+1;
			FileOutputStream fos = new FileOutputStream(seqDataFile);
			fos.write(seq);
			fos.flush();
			//5. 닫기
			fos.close();
			returnSeq = String.valueOf(seq);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NumberFormatException e) {
			return "오류";
		}
		return returnSeq;
	}
		
	//등록 (insert)
	private String insert(String userMessage){
		String systemMessage = null;
		
		String[] messageLineSeparator =userMessage.split("\n");
		String[] userInputColumns=messageLineSeparator[0].split(",");
		String fromData=messageLineSeparator[1].substring(5);
		String[] userInputValues=messageLineSeparator[2].substring(7).split(",");

		if(userInputColumns.length!=userInputValues.length){
			systemMessage="입력할 데이터와 입력한 데이터에 갯수가 맞지 않습니다.";
			return systemMessage;
		}
		
		if(fromData==null){
			systemMessage ="데이터의 위치를 지정하세요.";
			return systemMessage; 
		}
		// 입력한 값들중에 sysdate가 있으면 현재 시간을 넣어줌 
		for(int indexI=0;indexI<userInputValues.length;indexI++){
			if(SYSTEM_DATE.equals(userInputValues[indexI].trim())){
				userInputValues[indexI]=DataBaseUtil.getSystemDate();
			}
		}
		
		// 4. 등록
		File dataFile = new File(PATH,fromData);
		if(!dataFile.exists()){
			try {
				//5. 파일이 존재 하지 않으면 생성
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				systemMessage="에러발생";
				return systemMessage;
			}
		}
		FileWriter fw=null;
		try {
			//5. 존재하면 이어쓰기
			fw = new FileWriter(dataFile,true);
			for(int indexI=0;indexI<userInputColumns.length;indexI++){
				fw.write(userInputColumns[indexI].trim()+COLUMN_SEPARAROR+userInputValues[indexI].trim());
				if(indexI==userInputColumns.length-1){
					fw.write("\n");
				}else{
					fw.write(",");
				}
				
			}
			fw.flush();
			fw.close();
			systemMessage="성공적으로 데이터가 들어갔습니다.";
		} catch (IOException e) {
			e.printStackTrace();
			systemMessage="에러 발생";
			return systemMessage;
		}
		
		return systemMessage;
	}
	
	// 한건 조회 (select)
	private String select(String userMessage){
		//결과값 반환 준비
		String returnResult= null;
		String[] messageLineSeparator = userMessage.split("\n");
		String[] userInputColumns=messageLineSeparator[0].split(",");
		String fromData=messageLineSeparator[1].substring(5);
		// 여러건 조회로 넘김
		if(messageLineSeparator.length<3){
			return selectList(userInputColumns,fromData);
		}
			String[] userInputWhereData=messageLineSeparator[2].substring(5).split("=");
	
			String userInputWhereColumn=userInputWhereData[0];
			String userInputWhereValue=userInputWhereData[1];
		
	
		File file = new File(PATH,fromData);
		try {
			//1. 파일 읽기
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String readLine = null;
			String[] resultRow=null;
			//2. 줄단위로 읽기
			read:while((readLine=br.readLine()) != null){
				String[] readRow=readLine.split(",");
				for(String readColumn:readRow){
					String[] readColumnAndValue = readColumn.split("\\|");
					if(readColumnAndValue[0].equals(userInputWhereColumn.trim())&&readColumnAndValue[1].equals(userInputWhereValue.trim())){
						resultRow=readRow;
						break read;
					}
				}
				
			}
			//4. 닫기
			br.close();
			fileReader.close();
			
			StringBuffer stringBuffer = new StringBuffer();
			for(int indexI=0;indexI<userInputColumns.length;indexI++){
				String column=userInputColumns[indexI].trim();
				for(String resultColumn:resultRow){
					String[] resultColumnAndValue=resultColumn.split("\\|");
					if(column.equals(resultColumnAndValue[0])){
						stringBuffer.append(userInputColumns[indexI].trim());
						stringBuffer.append(COLUMN_SEPARAROR);
						stringBuffer.append(resultColumnAndValue[1]);
					}
					
				}
				if(indexI != userInputColumns.length-1){
					stringBuffer.append(",");
				}
			}
			returnResult= new String(stringBuffer);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "파일 존재 하지 않음";
		} catch (IOException e) {
			e.printStackTrace();
			return "오류입니다";
		} 
		
		return returnResult;
	}
	
	//여러건 조회 selectList
	private String selectList(String[] userInputColumns, String fromData){
		//결과값 반환준비
		String returnResult = null;
		//1. 파일읽기
		File dataFile = new File(PATH,fromData);
		FileReader fr = null;
		try {
			fr = new FileReader(dataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			String readLine= null;
			//2. 줄단위로 읽기
			StringBuffer stringBuffer = new StringBuffer();
			while((readLine=br.readLine())!=null){
				String[] readColumns=readLine.split(",");
				
				for(int indexI=0;indexI<userInputColumns.length;indexI++){
					String column=userInputColumns[indexI].trim();
					for(String readColumn:readColumns){
						String[] readColumnAndValue=readColumn.split("\\|");
						if(column.equals(readColumnAndValue[0])){
							stringBuffer.append(userInputColumns[indexI].trim());
							stringBuffer.append(COLUMN_SEPARAROR);
							stringBuffer.append(readColumnAndValue[1]);
						}
						
					}
					if(indexI!=userInputColumns.length-1){
						stringBuffer.append(",");
					}else{
						stringBuffer.append("\n");
					}
				}
				returnResult= new String(stringBuffer);
				
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
	private String delete(String userMessage){
		String returnResult= null;
		String[] messageLineSeparator = userMessage.split("\n");
		String fromData=messageLineSeparator[1].substring(5);
		String[] userInpuWhereData=messageLineSeparator[2].substring(5).split("=");

		String userInputWhereColumn=userInpuWhereData[0];
		String userInputWhereValue=userInpuWhereData[1];

		
		//1. 원래 파일 준비
		File dataFile = new File(PATH,fromData);
		//2. 임시 파일 준비
		File tempFile = new File(PATH,"temp");
		
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
				String[] readColunms=readLine.split(",");
				String removeTargetData= null;
				for(String readColunm:readColunms){
					String[] readColumnAndValue = readColunm.split("\\|");
					if(readColumnAndValue[0].equals(userInputWhereColumn.trim())&&readColumnAndValue[1].equals(userInputWhereValue.trim())){
						removeTargetData= readLine;
						break;
					}
				}
				if(!readLine.equals(removeTargetData)){
					fw.write(readLine+"\n");
				}
			}
			//6. 닫기
			br.close();
			fileReader.close();
			bw.close();
			fw.close();
			returnResult="성공적으로 삭제 하였습니다";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "파일을 찾을수 없습니다";
		} catch (IOException e) {
			e.printStackTrace();
		}
		//7. 기존 파일삭제
		dataFile.delete();
		//8. 임시 파일->기존파일 이름으로 변경
		tempFile.renameTo(dataFile);
		return returnResult;
	}
	//수정 (update)
	private String update(String userMessage){
		String returnResult= null;
		String[] messageLineSeparator = userMessage.split("\n");
		String[] userInputColumns=messageLineSeparator[0].split(",");
		String fromData=messageLineSeparator[1].substring(5);
		
		String[] userInputWhereData=messageLineSeparator[2].substring(5).split("=");

		String userInputWhereColumn=userInputWhereData[0];
		String userInputWhereValue=userInputWhereData[1];
		
		
		
		
		
		//1. 원래 파일 준비
		File dataFile = new File(PATH,fromData.trim());
		//2. 임시 파일 준비
		File tempFile = new File(PATH,"temp");
		
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
				String[] readRows=readLine.split(",");
				String editTargetData= null;
				for(String readColunm:readRows){
					String[] columnAndValue = readColunm.split("\\|");
					if(columnAndValue[0].equals(userInputWhereColumn.trim())&&columnAndValue[1].equals(userInputWhereValue.trim())){
						editTargetData= readLine;
						break;
					}
				}
				if(!readLine.equals(editTargetData)){
					fw.write(readLine+"\n");
				}else{
					String[] readColumns=editTargetData.split(",");
					for(int indexI=0;indexI<readColumns.length;indexI++){
						String[] readColumnAndValue=readColumns[indexI].split("\\|");
						boolean checkUpdate= true;
						for(int indexJ=0;indexJ<userInputColumns.length;indexJ++){
							String[] userInputColumnAndValue=userInputColumns[indexJ].split("=");
							if(readColumnAndValue[0].equals(userInputColumnAndValue[0].trim())){
								if(SYSTEM_DATE.equals(userInputColumnAndValue[1].trim())){
									userInputColumnAndValue[1]=DataBaseUtil.getSystemDate();
								}
								fw.write(readColumnAndValue[0]+COLUMN_SEPARAROR+userInputColumnAndValue[1].trim());
								checkUpdate=false;
							}
						}
						if(checkUpdate){
							fw.write(readColumnAndValue[indexI]);
						}
						if(indexI!=readColumnAndValue.length-1){
							fw.write(",");
						}
						
					}
					fw.write("\n");
				}
			}
			fw.flush();
			
			//8. 닫기
			br.close();
			fileReader.close();
			bw.close();
			fw.close();
			returnResult="수정이 완료되었습니다";
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//9. 기존 파일 삭제
		dataFile.delete();
		//10. 임시 파일->기존파일 이름으로 변경
		tempFile.renameTo(dataFile);
		return returnResult;
	}

	
	public void service(MessageRequest messageRequest,
			ResultResponse resultResponse) throws IOException {
		String returnMessage = null;
		if (INSERT.equals(messageRequest.getUserRequest())) {
			returnMessage = insert(messageRequest.getUserMessage());
		}else if(MODIFY.equals(messageRequest.getUserRequest())){
			returnMessage = update(messageRequest.getUserMessage());
		}else if(REMOVE.equals(messageRequest.getUserRequest())){
			returnMessage = delete(messageRequest.getUserMessage());
		}else if(SEARCH.equals(messageRequest.getUserRequest())){
			returnMessage = select(messageRequest.getUserMessage());
		}else if(GETSEQ.equals(messageRequest.getUserRequest())){
			returnMessage = getSystemSeq();
		}else {
			returnMessage="잘못 되었습니다";
		}
		resultResponse.write(returnMessage);
		System.out.println(returnMessage);
	}
}
