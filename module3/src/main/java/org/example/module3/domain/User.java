package org.example.module3.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//Chcesz mutable POJO z minimalnym kodem → Lombok nadal króluje



@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String name;
    private int age;
}