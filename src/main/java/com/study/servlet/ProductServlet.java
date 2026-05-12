package com.study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.entity.Product;
import com.study.service.ProductService;
import com.study.util.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// products
// products/{id}
// 에 대한 url을 매칭하겠다.
@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private final ProductService service = new ProductService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = service.getAllProduct();
        MyUtils.writeJson(resp, products, objectMapper);
    }

    // 단건 조회

    // 등록
    // 수정
    // 삭제
}
