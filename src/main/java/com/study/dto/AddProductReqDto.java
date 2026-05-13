package com.study.dto;

import com.study.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddProductReqDto {
    // dto는 돌려쓰는 용도가 x
    // 타입검증용!, 코드 분리를 위한 용도
    private String name;
    private int price;
    private int stock;
    private String category;

    // 엔티티 변환
    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .category(this.category)
                .build();
    }
}
