package com.study.service;

import com.study.dto.AddProductReqDto;
import com.study.dto.ProductResDto;
import com.study.entity.Product;
import com.study.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductRepository repository = new ProductRepository();

    // 트랜잭션 관리를 해줘야 하나, 생략
    // 비즈니스 로직 작성. 현재는 없음
    // 실습) 서비스에서는 entity를 리턴하는게 아니라, dto를 리턴
    // id만 뺀 나머지 정보만 노출
    // ProductResDto 정의, entity에 변환 메서드를 작성
    public List<ProductResDto> getAllProduct() {
        List<Product> products = repository.findAll();
        List<ProductResDto> dtos = products.stream()
                .map(Product::toResDto)
                .toList();

        return repository.findAll()
                .stream()
                .map(Product::toResDto)
                .toList();
    }

    public int createProduct(AddProductReqDto dto) throws SQLException {
        return repository.save(dto.toEntity());
    }

    public int removeProduct(int id) throws SQLException {
        int successCount = repository.delete(id);
        return successCount;
    }

}
