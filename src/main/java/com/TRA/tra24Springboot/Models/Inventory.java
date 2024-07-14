package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Builder
public class Inventory extends BaseEntity {


    String location;
    String manager; //TODO: Update once user class created
    List<String> workers; //TODO: Update user class created
    String supplier;
    String phoneNumber;
    String openingHours;
    String closingHours;
    LocalDate WriteOffDate;
    @OneToMany
    List<Product> products;



}
