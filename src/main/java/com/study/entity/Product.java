package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;
    private String category;
}
