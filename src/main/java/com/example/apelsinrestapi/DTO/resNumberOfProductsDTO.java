package com.example.apelsinrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class resNumberOfProductsDTO {
    private String country;
    private Integer totalNum;

    public resNumberOfProductsDTO(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
