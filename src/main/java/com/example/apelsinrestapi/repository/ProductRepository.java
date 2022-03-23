package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.DTO.resOrderDetailsDTO;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.projection.ProductDetailsProjection;
import com.example.apelsinrestapi.projection.ProductProjection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<ProductProjection> findAllByActiveTrue();
    Optional<ProductDetailsProjection> findAllById(Integer product_id);
    @Query(value = "select c.name, p.name as product_name, p.price as product_price, d.quantity,  i.amount, i.issued, i.due from product p join detail d on p.id = d.product_id join orders o on d.order_id = o.id join customer c on o.customer_id = c.id join invoice i on o.id = i.order_id where o.id =:orderId",nativeQuery = true)
    Optional<resOrderDetailsDTO> getOrderDetails(@Param("orderId") Integer orderId);
}
