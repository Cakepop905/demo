package com.example.demo.domain;

import lombok.Data;
import lombok.Generated;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Order {
    @NotBlank(message = "Name required to order")
    private String name;
    @NotBlank(message = "Please give street address")
    private String street;
    @NotBlank(message = "city required")
    private String city;
    @NotBlank(message = "state required")
    private String state;
    @NotBlank
    private String zip;
    @CreditCardNumber
    private String ccNumber;
    @Pattern(regexp = "^0[1-9]|1[[0-2])(\\/]([0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 5, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Data createAt;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
        @PrePersist
                void createdAt(){
            this.createdAt = new Date();
        }
    }
}
