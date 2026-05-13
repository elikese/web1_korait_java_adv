package com.study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.dto.AddProductReqDto;
import com.study.dto.ProductResDto;
import com.study.entity.Product;
import com.study.service.ProductService;
import com.study.util.MyUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

// products
// products/{id}
// 에 대한 url을 매칭하겠다.
@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    private final ProductService service = new ProductService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductResDto> products = service.getAllProduct();
        MyUtils.writeJson(resp, products, objectMapper);
    }

    // 등록

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 객체에서 body 데이터를 가져옴
        ServletInputStream in = req.getInputStream();
        AddProductReqDto dto = objectMapper.readValue(in, AddProductReqDto.class);
        try {
            int successCount = service.createProduct(dto);
            if(successCount < 0) {
                // 에러 응답
            }
            resp.setStatus(HttpServletResponse.SC_CREATED); // 201
            Map<String, String> msg = Map.of("message", "등록 성공");
            MyUtils.writeJson(resp, msg, objectMapper);

        } catch (SQLException e) {
            // 에러발생 -> 서버에러
            // 응답 상태코드
            /*
                200 : 요청성공하여 응답도 성공
                201 : 요청성공하여 서버에 데이터 만들어졌음
                400대 : 요청쪽 잘못 
                401/403 : 권한이 없다
                404 : 요청 경로가 잘못
                500대 : 서버쪽 잘못
            */
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, String> errorMsg = Map.of("message", "DB 오류");
            MyUtils.writeJson(resp, errorMsg, objectMapper);
        }
        
    }

    // 삭제
    // /product/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // url은 String 취급
        String path = req.getPathInfo();
        
        if(path == null || path.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            Map<String, String> errorMsg = Map.of("message", "id가 필요합니다");
            MyUtils.writeJson(resp, errorMsg, objectMapper);
            return;
        }
        
        // ex) "/11", "/3", "/1"
        String strId = path.substring(1);
        int id = Integer.parseInt(strId);

        try {
            int sc = service.removeProduct(id);
            if (sc <= 0) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                Map<String, String> errorMsg = Map.of("message", "해당 상품을 찾을 수 없습니다");
                MyUtils.writeJson(resp, errorMsg, objectMapper);
                return;
            }

            // 성공했을때
            resp.setStatus(HttpServletResponse.SC_OK);
            MyUtils.writeJson(resp, Map.of("message", "삭제완료"), objectMapper);

        } catch (SQLException e) {
            // DB 오류메세지, 500 상태코드
        }


        


    }
}
