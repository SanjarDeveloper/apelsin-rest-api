package com.example.apelsinrestapi.projection;

import com.example.apelsinrestapi.entity.Category;
import org.springframework.beans.factory.annotation.Value;

public interface ProductDetailsProjection {
    String getName();
    @Value("#{target.category.name}")
    String getCategoryName();

    String getDescription();

    Double getPrice();

    String getPhoto();

}
