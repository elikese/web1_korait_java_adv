package com.study.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// REST 서버를 만들것 -> JSON을 응답하는 서버
// localhost:8080
// www.naver.com:443 
// www.naver.com -> 서버주소
// :433 -> 해당 서버의 포트번호
// localhost:8080/hello -> url 경로

@WebServlet("/hello") // /hello로 들어온 요청은 HelloServlet으로 매칭
public class HelloServlet extends HttpServlet {

    // HTTP 프로토콜(약속) - 1회 요청시 1회 응답
    // HTTP 메서드 - 요청 & 응답
    // GET, POST, PUT, PATCH, DELETE 
    // 메서드마다 의미가 존재하며, 의미와 매칭되게 코딩하는걸 restful 표현

    // hello로 들어온 요청 중 get요청시, 실행할 메서드
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청과 응답
        // 클라이언트의 요청정보는 req에 담겨있음
        // resp에 응답할 데이터를 담아서 응답!

        // 1. 응답타입 설정
        resp.setContentType("text/plain"); // JSON 아님
        resp.setCharacterEncoding("UTF-8");

        // 2. 응답 body에 쓸 문자열 준비
        String msg = "안녕하세요! 내가 직접 쏴주는 데이터";
        
        // 3 ~ 4번은 스프링에서 return으로 대체됨
        // 3. 응답도구 셋팅
        PrintWriter writer = resp.getWriter();
        // 4. 메세지 작성
        writer.write(msg);

    }
}
