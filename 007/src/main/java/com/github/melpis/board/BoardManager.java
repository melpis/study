package com.github.melpis.board;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardManager {
    private final String DRIVER_NAME="org.postgresql.Driver";
    private final String DB_URI="jdbc:postgresql://127.0.0.1:5432/postgres";
    private final String DB_USER_NAME="postgres";
    private final String DB_USER_PASSWORE="postgres";
    /*
    CREATE TABLE public.TB_BOARD
    (
        seq BIGSERIAL PRIMARY KEY NOT NULL,
        title VARCHAR(255),
        content TEXT NOT NULL,
        regist_date DATE NOT NULL,
        hit INT NOT NULL
    );
    CREATE UNIQUE INDEX "TB_BOARD_seq_uindex" ON public.TB_BOARD (seq);
     */
    public BoardManager(){
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
        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql = "insert into TB_BOARD(TITLE, CONTENT, REGIST_DATE, HIT)";
            sql += "values( ?, ?, now(), 0) ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 prepardStatement 값 맵핑
            preparedStatement.setString(1, userInputTitle);
            preparedStatement.setString(2, userInputContent);
            //3.6 실행
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
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
        //2. 유효성 검사
        if(parameterSeq <=0){
            return;
        }

        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map data = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql =" select SEQ, TITLE, CONTENT, REGIST_DATE, HIT ";
            sql+=" from TB_BOARD ";
            sql+=" where SEQ= ? ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 prepardStatement 값 맵핑
            preparedStatement.setInt(1, parameterSeq);
            //3.6 실행
            resultSet =preparedStatement.executeQuery();
            //3.7 결과값 매핑
            if(resultSet.next()){
                data = new HashMap();
                data.put("SEQ", String.valueOf(resultSet.getInt("SEQ")));
                data.put("TITLE",resultSet.getString("TITLE"));
                data.put("CONTENT",resultSet.getString("CONTENT"));
                data.put("REGIST_DATE",resultSet.getDate("REGIST_DATE").toString());
                data.put("HIT",String.valueOf(resultSet.getInt("HIT")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(resultSet != null){try {resultSet.close();} catch (SQLException e) {} }
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
        }
        //4. 결과 출력
        System.out.println("글번호 : " +data.get("SEQ"));
        System.out.println("제목  : "  +data.get("TITLE"));
        System.out.println("등록일 : " +data.get("REGIST_DATE"));
        System.out.println("조회수 : " +data.get("HIT"));
        System.out.println("내용 : "  +data.get("CONTENT"));

    }


    public void viewDocumentList(){

        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Map<String,String>> dataList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql =" select SEQ, TITLE, CONTENT, REGIST_DATE, HIT ";
            sql+=" from TB_BOARD ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 실행
            resultSet =preparedStatement.executeQuery();
            //3.6 결과값 매핑
            while(resultSet.next()){
                Map<String,String> data = new HashMap<>();
                data.put("SEQ", String.valueOf(resultSet.getInt("SEQ")));
                data.put("TITLE",resultSet.getString("TITLE"));
                data.put("CONTENT",resultSet.getString("CONTENT"));
                data.put("REGIST_DATE",resultSet.getDate("REGIST_DATE").toString());
                data.put("HIT",String.valueOf(resultSet.getInt("HIT")));
                dataList.add(data);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(resultSet != null){try {resultSet.close();} catch (SQLException e) {} }
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
        }
        // 4. 결과 출력
        for(Map<String,String> data:dataList){
            System.out.println("글번호 : " +data.get("SEQ"));
            System.out.println("제목  : "  +data.get("TITLE"));
            System.out.println("등록일 : " +data.get("REGIST_DATE"));
            System.out.println("조회수 : " +data.get("HIT"));
            System.out.println("내용 : "  +data.get("CONTENT"));
        }

    }

    public void deleteDocument(String[] outputData) {
        //1. 사용자가 입력한 문서 번호 가져오기
        String userInputSeq = outputData[1];
        //1.1 변환
        int parameterSeq = Integer.parseInt(userInputSeq);
        //2. 유효성 검사
        if(parameterSeq<=0){
            return ;
        }

        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql =" delete from TB_BOARD ";
            sql+=" where SEQ= ? ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 prepardStatement 값 맵핑
            preparedStatement.setInt(1, parameterSeq);
            //3.6 실행
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
        }

        //4. 결과 출력
        System.out.println("삭제 되었습니다");

    }

    public void editDocumentForm(String[] outputData) throws ParseException {
        //1. 사용자가 입력한 문서 번호 가져오기
        String userInputSeq = outputData[1];
        //1.1 변환
        int parameterSeq = Integer.parseInt(userInputSeq);
        //2. 유효성 검사
        if(parameterSeq<=0){
            return;
        }

        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map data = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql =" select SEQ, TITLE, CONTENT, REGIST_DATE, HIT ";
            sql+=" from TB_BOARD ";
            sql+=" where SEQ= ? ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 prepardStatement 값 맵핑
            preparedStatement.setInt(1, parameterSeq);
            //3.6 실행
            ResultSet resultSet =preparedStatement.executeQuery();
            //3.7 결과값 매핑
            if(resultSet.next()){
                data = new HashMap();
                data.put("SEQ", String.valueOf(resultSet.getInt("SEQ")));
                data.put("TITLE",resultSet.getString("TITLE"));
                data.put("CONTENT",resultSet.getString("CONTENT"));
                data.put("REGIST_DATE",resultSet.getDate("REGIST_DATE").toString());
                data.put("HIT",String.valueOf(resultSet.getInt("HIT")));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
        }
        //4. 결과 출력
        System.out.println("글번호 : " +data.get("SEQ"));
        System.out.println("제목  : "  +data.get("TITLE"));
        System.out.println("등록일 : " +data.get("REGIST_DATE"));
        System.out.println("조회수 : " +data.get("HIT"));
        System.out.println("내용 : "  +data.get("CONTENT"));

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
        //2. 유효성검사
        if(parameterSeq<=0){
            return ;
        }
        if (userInputTitle.length() < 1 && userInputTitle.length() > 600) {
            return;
        }
        if (userInputContent.length() < 1 && userInputContent.length() > 2000) {
            return;
        }
        try {
            //3.1 드라이버 로딩
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //3.2 연결하기
            connection = DriverManager.getConnection(DB_URI,DB_USER_NAME,DB_USER_PASSWORE);
            //3.3 sql문 준비하기
            String sql =" update TB_BOARD set ";
            sql+=" TITLE = ? , ";
            sql+=" CONTENT = ? , ";
            sql+=" REGIST_DATE = now() ";
            sql+=" where SEQ= ? ";
            //3.4 prepardStatement에 sql넣기
            preparedStatement = connection.prepareStatement(sql);
            //3.5 prepardStatement 값 맵핑
            preparedStatement.setString(1, userInputTitle);
            preparedStatement.setString(2, userInputContent);
            preparedStatement.setInt(3,parameterSeq);
            //3.6 실행
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){ try {preparedStatement.close();} catch (SQLException e) {} }
            if(connection != null){ try { connection.close();} catch (SQLException e) {}	}
        }

        //4. 결과 출력
        System.out.println("수정완료 하였습니다");

    }

}