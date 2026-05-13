package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor @Data @Builder
public class ProductResDto {
    private String name;
    private int price;
    private int stock;
    private String category;
}
