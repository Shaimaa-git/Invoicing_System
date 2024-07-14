package com.TRA.tra24Springboot.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToMany
    List<Product> productsOrdered;
    String categoryName;
    LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
    String description;
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    PaymentType paymentType;
    LocalDate dueDate;
}
