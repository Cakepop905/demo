package com.example.demo.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Taco {
    @NotBlank
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @Size(min = 2, message = "you must have at least two ingredients to make a taco")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<String> ingredients;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

