package com.study.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB 연결을 담당하는 클래스
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1q2w3e4r!";

    // 연결객체를 new로 만들지 않고, 대여해오는 방식
    // -> 반드시 대여 이후에 반납을 해줘야한다!
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // 외부생성 막기
    private DBConnection() {}

}
