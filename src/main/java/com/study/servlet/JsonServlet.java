package com.study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/json")
public class JsonServlet extends HttpServlet {

    // 자동파싱 라이브러리(jackson)
    // java객체 <> JSON 변환
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 응답타입을 JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 객체 혹은 Map으로 JSON을 만듬
        Map<String, Object> data = new HashMap<>();
        data.put("message", "응답성공!");
        data.put("responseCode", 200);
        data.put("isSuccess", true);

        String json = objectMapper.writeValueAsString(data);

        PrintWriter writer = resp.getWriter();
        writer.write(json);

    }
}
