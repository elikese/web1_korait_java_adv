package com.study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.entity.Profile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// localhost:8080/pojo

@WebServlet("/pojo")
public class PojoServlet extends HttpServlet {

    // jackson 라이브러리
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Profile profile = new Profile("홍길동", 20, "hong@sample.com");

        String json = objectMapper.writeValueAsString(profile);

        PrintWriter writer = resp.getWriter();

        writer.write(json);

    }
}
