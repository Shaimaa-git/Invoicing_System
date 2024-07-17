package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
public class Invoice extends BaseEntity {

     Double totalAmount;
     Double paidAmount;
     @OneToMany
     List<Product> listOfProduct;
     Date dueDate;

}
