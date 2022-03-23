package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<CategoryProjection> findAllByActiveTrue();
//    List<Category> findAllByActiveTrue();
}
