package com.github.melpis.board;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BoardDataBaseAccess {
    //소켓
    private Socket client = null;
    //서버에 데이터 기록 및 읽기
    private InputStream serverDataRead = null;
    private OutputStream serverDataWrite = null;
    //결과 값
    private String result = null;
    Iterator<Map<String, String>> results = null;
    Map<String, String> resultData = null;

    public void connetion() throws UnknownHostException, IOException {
        this.client = new Socket("localhost", 1521);
        this.serverDataRead = this.client.getInputStream();
        this.serverDataWrite = this.client.getOutputStream();
    }

    public void executeQuery(String statement) throws IOException {
        byte[] buffer = new byte[4096];
        this.serverDataWrite.write(statement.getBytes());
        this.serverDataWrite.flush();
        int readCount = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while ((readCount = this.serverDataRead.read(buffer)) > 0) {
            stringBuffer.append(new String(buffer, 0, readCount));
        }
        resultPase(new String(stringBuffer));
    }

    private void resultPase(String result) {
        String[] resultRows = result.split("\n");
        List<Map<String, String>> resultDatas = new ArrayList<Map<String, String>>();
        for (String resultRow : resultRows) {
            String[] resultColumns = resultRow.split(",");
            if (resultColumns.length <= 1) {
                this.result = result;
            } else {
                Map<String, String> resultData = new HashMap<String, String>();
                for (String resultColumn : resultColumns) {
                    String[] resultColumnAndValue = resultColumn.split("\\|");
                    resultData.put(resultColumnAndValue[0], resultColumnAndValue[1]);
                }
                resultDatas.add(resultData);
            }
        }

        this.results = resultDatas.iterator();

    }

    public void execute(String statement) throws IOException {
        this.serverDataWrite.write(statement.getBytes());
        this.serverDataWrite.flush();
    }

    public String getResult() {
        return this.result;
    }

    public boolean hasNext() {
        if (this.results.hasNext()) {
            this.resultData = this.results.next();
            return true;
        }
        return false;
    }

    public String getResult(String column) {
        return this.resultData.get(column);
    }

    public void close() {
        try {
            this.serverDataRead.close();
            this.serverDataWrite.close();
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
