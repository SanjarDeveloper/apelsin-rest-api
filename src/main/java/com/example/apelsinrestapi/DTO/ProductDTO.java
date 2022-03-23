package com.example.apelsinrestapi.DTO;

import com.example.apelsinrestapi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String name;
    private Integer category_id;
    private String description;
    private Double price;
    private String photo;
    private boolean active;
}
