package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Jackson은 기본생성자로 빈 객체를 만든 후에 
// getter와 setter로 데이터를 추가하는 방식
public class Profile {
    // POJO - Plain Old Java Object
    // Map대신에서 사용하는 데이터 모양(클래스)
    private String name;
    private int age;
    private String email;
}
