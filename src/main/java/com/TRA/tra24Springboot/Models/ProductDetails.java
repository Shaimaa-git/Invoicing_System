package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Builder
public class ProductDetails extends BaseEntity{

    String name;
    String countryOfOrigin;
    Date expiryDate;
    String size;
    String color;
    String description;
    Double price;

}
