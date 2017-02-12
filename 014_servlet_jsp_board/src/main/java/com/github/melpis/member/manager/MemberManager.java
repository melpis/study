package com.github.melpis.member.manager;

import com.github.melpis.member.exception.MemberInfoNotValidateException;
import com.github.melpis.member.exception.MemberUserIdExistException;
import com.github.melpis.member.exception.MemberUserIdNotExistException;
import com.github.melpis.member.exception.MemberUserPwNotSameException;
import com.github.melpis.member.vo.MemberVO;

import java.sql.*;

public class MemberManager {
    private String jdbcDriver = "org.postgresql.Driver";
    private String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private String jdbcUserId = "postgres";
    private String jdbcUserPw = "postgres";

    public void registMember(MemberVO member) throws MemberInfoNotValidateException, MemberUserIdExistException {
        //1. 데이터 추출
        if (member == null) {
            // 예외 처리
            throw new MemberInfoNotValidateException("회원 정보가 잘못 되었습니다.");
        }
        //2. 유효성 검사
        if (member.getUserId() == null
                || member.getUserId().trim().length() < 6
                || member.getUserId().trim().length() > 20) {
            // 예외 처리
            throw new MemberInfoNotValidateException("아이디가 올바르지 않습니다.");
        }
        //3. 처리
        //3.1. 회원 조회
        MemberVO checkMemberVO = getMember(member);
        //3.2. 회원 존재 여부 확인
        if (checkMemberVO != null) {
            // 예외 처리
            throw new MemberUserIdExistException("이미 존재하는 아이디입니다.");
        }
        //3. 처리

        //3.3. 회원 등록 처리
        //1. JDBC Driver 로드
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //2. Connection 생성
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. SQL문 생성
            String insertSql = "";
            insertSql += " insert into TBL_MEMBER ";
            insertSql += " (USER_ID, USER_PW, USER_NAME, REGIST_DATE, LAST_VISITED_DATE) ";
            insertSql += " values ";
            insertSql += " (?, ?, ?, now(), now()) ";
            //4. PreparedStatement 생성
            pstmt = conn.prepareStatement(insertSql);
            //5. 파라미터 매핑
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getUserPw());
            pstmt.setString(3, member.getUserName());
            //6. 실행
            pstmt.executeUpdate();
            //7. 결과 값 매핑(String--> Java)

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //8. 자원 해제
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //4. 결과 출력
    }

    public void editMember(MemberVO member) throws MemberInfoNotValidateException, MemberUserIdNotExistException {
        //1. 데이터 추출
        if (member == null) {
            // 예외 처리
            throw new MemberInfoNotValidateException("회원 정보가 잘못 되었습니다.");
        }
        //2. 유효성 검사
        if (member.getUserId() == null
                || member.getUserId().trim().length() < 6
                || member.getUserId().trim().length() > 20) {
            // 예외 처리
            throw new MemberInfoNotValidateException("아이디가 올바르지 않습니다.");
        }
        //3. 처리
        //3.1. 회원 조회
        MemberVO checkMemberVO = getMember(member);
        //3.2. 회원 존재 여부 확인
        if (checkMemberVO != null) {
            // 예외 처리
            throw new MemberUserIdNotExistException("이미 존재하는 아이디입니다.");
        }
        //3.3. 회원 수정 처리
        //1. JDBC Driver 로드
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //2. Connection 생성
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. SQL문 생성
            String insertSql = "";
            insertSql += " update TBL_MEMBER ";
            insertSql += " set ";
            insertSql += " 	USER_PW = ? ";
            insertSql += " where ";
            insertSql += " 	USER_ID = ? ";
            //4. PreparedStatement 생성
            pstmt = conn.prepareStatement(insertSql);
            //5. 파라미터 매핑
            pstmt.setString(1, member.getUserPw());
            pstmt.setString(2, member.getUserId());
            //6. 실행
            pstmt.executeUpdate();
            //7. 결과 값 매핑(String--> Java)

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //8. 자원 해제
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public MemberVO loginMember(MemberVO member) throws MemberInfoNotValidateException, MemberUserIdNotExistException, MemberUserPwNotSameException {
        MemberVO returnMemberVO = null;
        //1. 데이터 추출
        if (member == null) {
            // 예외 처리
            throw new MemberInfoNotValidateException("회원 정보가 잘못 되었습니다.");
        }
        //2. 유효성 검사
        if (member.getUserId() == null
                || member.getUserId().trim().length() < 6
                || member.getUserId().trim().length() > 20) {
            // 예외 처리
            throw new MemberInfoNotValidateException("아이디가 올바르지 않습니다.");
        }
        if (member.getUserPw() == null
                || member.getUserPw().trim().length() < 6
                || member.getUserPw().trim().length() > 20) {
            // 예외 처리
            throw new MemberInfoNotValidateException("아이디가 올바르지 않습니다.");
        }
        //3. 처리
        // 3.1. 해당 회원 정보 조회
        returnMemberVO = getMember(member);
        // 3.2. 로그인 가능 여부 확인
        if (returnMemberVO == null) {
            throw new MemberUserIdNotExistException("아이디가 존재하지 않습니다.");
        }
        if (!returnMemberVO.getUserPw().equals(member.getUserPw())) {
            throw new MemberUserPwNotSameException("패스워드가 일치하지 않습니다.");
        }
        // 3.3. 최종 로그인 일 수정
        //1. JDBC Driver 로드
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //2. Connection 생성
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);
            //3. SQL문 생성
            String updateSql = "";
            updateSql += " update TBL_MEMBER ";
            updateSql += " set ";
            updateSql += " 	LAST_VISITED_DATE = now() ";
            updateSql += " where ";
            updateSql += " 	USER_ID = ? ";
            //4. PreparedStatement 생성
            pstmt = conn.prepareStatement(updateSql);
            //5. 파라미터 매핑
            pstmt.setString(1, member.getUserId());
            //6. 실행
            pstmt.executeUpdate();
            //7. 결과 값 매핑(String--> Java)

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //8. 자원 해제
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //4. 결과 출력
        return returnMemberVO;
    }

    public MemberVO getMember(MemberVO member) {
        MemberVO returnMemberVO = null;
        //1. 데이터 추출
        if (member.getUserId() == null) {
            // 예외 처리
        }
        //2. 유효성 검사
        if (member.getUserId() == null
                || member.getUserId().trim().length() < 6
                || member.getUserId().trim().length() > 20) {
            // 예외 처리
        }
        //3. 처리

        //1. JDBC Driver 로드
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //2. Connection 생성
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcUserPw);

            //3. SQL문 생성
            String sql = "";
            sql += " select ";
            sql += " 	USER_ID, USER_PW, USER_NAME, REGIST_DATE, LAST_VISITED_DATE ";
            sql += " from ";
            sql += " 	TBL_MEMBER ";
            sql += " where ";
            sql += " 	USER_ID = ? ";
            //4. PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);
            //5. 파라미터 매핑
            pstmt.setString(1, member.getUserId());
            //6. 실행
            rs = pstmt.executeQuery();
            //7. 결과 값 매핑(String--> Java)
            if (rs.next()) {
                returnMemberVO = new MemberVO();
                returnMemberVO.setUserId(rs.getString("USER_ID"));
                returnMemberVO.setUserPw(rs.getString("USER_PW"));
                returnMemberVO.setUserName(rs.getString("USER_NAME"));
                returnMemberVO.setRegistDate(rs.getDate("REGIST_DATE"));
                returnMemberVO.setLastVisitedDate(rs.getDate("LAST_VISITED_DATE"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //8. 자원 해제
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return returnMemberVO;
    }


}
