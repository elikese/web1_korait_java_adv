package com.study.service;

import com.study.entity.Product;
import com.study.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository repository = new ProductRepository();

    // 트랜잭션 관리를 해줘야 하나, 생략
    // 비즈니스 로직 작성. 현재는 없음
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

}
