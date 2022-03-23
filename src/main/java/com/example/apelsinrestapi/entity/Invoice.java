package com.example.apelsinrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Order order;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date due;
}
