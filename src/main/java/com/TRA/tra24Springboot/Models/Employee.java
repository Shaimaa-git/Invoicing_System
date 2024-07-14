package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Employee extends BaseEntity {

    @OneToOne
    User user;
    String department;
    String shift;
    String jobTitle;
    String nextOfKin;
    String location;
    String civilID;

}
