package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
public class Product extends BaseEntity {

    public Integer quantity;
    public String category;
    public UUID sku;
    @OneToOne
    @Cascade(CascadeType.ALL)
    ProductDetails productDetails;

}
