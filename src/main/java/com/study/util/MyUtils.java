package com.study.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MyUtils {

    // 응답을 json 및 utf-8 설정
    public static void setJson(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    // setJson + 응답쓰기 까지 담당
    public static void writeJson(
            HttpServletResponse resp,
            Object data,
            ObjectMapper mapper
    ) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String json = mapper.writeValueAsString(data);

        PrintWriter writer = resp.getWriter();
        writer.write(json);
    }
}
