package com.challenges.challengeTwo.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Size(min = 10,max = 10)
    private BigDecimal mobile;

    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    public Seller() {
    }

    public Seller(Long id, BigDecimal mobile, @Email String email, @NotNull @Size(min = 2, max = 100) String name) {
        this.id = id;
        this.mobile = mobile;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMobile() {
        return mobile;
    }

    public void setMobile(BigDecimal mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
