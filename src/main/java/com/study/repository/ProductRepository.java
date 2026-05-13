package com.study.repository;

import com.study.db.DBConnection;
import com.study.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// ORM들은 모두 내부적으로 이 방식을 사용한다.
public class ProductRepository {

    // 전체 조회
    // 일반적으로는 전체 조회하면 안됨.
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";

        // jdbc에서 데이터베이스를 다룰때 필요한 3객체
        Connection conn = null; // 연결객체
        PreparedStatement ps = null; // sql쿼리 실행용 객체
        ResultSet rs = null; // db 데이터 응답용 객체

        try {
            conn = DBConnection.getConnection(); // 연결 빌려오기
            ps = conn.prepareStatement(sql); // 해당 연결에 sql실행 객체 담기
            rs = ps.executeQuery(); // 실행하고 결과를 받아보기

            // resultSet은 결과를 한줄씩 가져옴
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");
                String category = rs.getString("category");

                Product product = new Product(id, name, price, stock, category);
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 반납 필수 - 가져온것의 역순으로 반납
            if(rs != null) try {rs.close();} catch (SQLException e) {}
            if(ps != null) try {ps.close();} catch (SQLException e) {}
            if(conn != null) try {conn.close();} catch (SQLException e) {}
        }

        return products;
    }

    // 단건 추가
    public int save(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, price, stock, category) values (?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection(); // 커넥션 빌려오기
            ps = conn.prepareStatement(sql); // 미완성된 sql

            String name = product.getName();
            int price = product.getPrice();
            int stock = product.getStock();
            String category = product.getCategory();

            // SQL injection - ? 자리에 이상한 명령어 끼워넣어서 무차별 공격
            // 문자열 + 연산 혹은 다른 방법으로 이어서 sql 조합하지 x
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, stock);
            ps.setString(4, category);
            // sql 완성

            int successCount = ps.executeUpdate(); // 쿼리 전송&실행
            return successCount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 반납
            // 역순으로
            if(ps != null) try { ps.close(); } catch (SQLException e) {}
            if(conn != null) try { conn.close(); } catch (SQLException e) {}
        }

        return -1; // 실패시 -1 리턴
    }

    // 단건 삭제
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int sc = ps.executeUpdate();
            return sc;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(ps != null) try {ps.close();} catch (SQLException e) {}
            if(conn != null) try {conn.close();} catch (SQLException e) {}
        }

        return -1;
    }


}
