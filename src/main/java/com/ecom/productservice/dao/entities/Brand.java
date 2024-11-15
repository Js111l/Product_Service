package com.ecom.productservice.dao.entities;


import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "BRAND")
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;  // Optional: Description of the brand.
}