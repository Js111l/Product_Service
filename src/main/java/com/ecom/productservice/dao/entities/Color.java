package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COLOR")
@Data
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hex_code")
    private String hexCode;  // Optional: You can store hex code or RGB value for colors.
}