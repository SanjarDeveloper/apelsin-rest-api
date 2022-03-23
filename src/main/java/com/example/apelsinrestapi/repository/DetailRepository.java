package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Detail;
import com.example.apelsinrestapi.projection.OrderDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    List<Detail> findByProductId(Integer product_id);

    List<OrderDetailsProjection> findAllByOrderId(Integer order_id);
}
