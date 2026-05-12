package com.study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.entity.Profile;
import com.study.util.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/pojo2")
public class PojoServlet2 extends HttpServlet {
    // get요청시 Profile 객체들이 3개 담긴 리스트를
    // 응답하는 코드를 작성해주세요
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyUtils.setJson(resp);

        List<Profile> datas = List.of(
                new Profile("김첫째", 25, "one@kim.com"),
                new Profile("김둘째", 24, "two@kim.com"),
                new Profile("김셋째", 23, "three@kim.com")
        );

        String json = objectMapper.writeValueAsString(datas);
        PrintWriter writer = resp.getWriter();
        writer.write(json);

    }

    /*
        (서버의 데이터를)
        GET - 조회, 읽기
        -> get요청은 요청 body가 존재하지 않는다.
        브라우저에서 주소치고 들어가는건 get요청
        -----------------------------
        -> 아래의 요청메서드들은 요청 body가 존재함.
        POST - 추가
        PUT, PATCH - 수정
        DELETE - 삭제
    */
    // 터미널에서 curl로 http 요청을 할 수 있다(window, mac os)
    // -> 번거로우니, postman 소프트웨어 사용하자
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyUtils.setJson(resp);

        // 요청객체의 body에 JSON을 java Profile 객체로 변환
        BufferedReader reader = req.getReader(); // body 데이터 읽어오기
        com.study.entity.Profile profile = objectMapper.readValue(reader, Profile.class); // java객체로 변환

        System.out.println("요청에서 보낸 객체: " + profile);

        PrintWriter writer = resp.getWriter();

        String json = objectMapper.writeValueAsString(Map.of("success", "객체생성 완료"));
        writer.write(json);
    }
}
