package com.TRA.tra24Springboot.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Builder
public class User extends BaseEntity {

    String name;
    @Enumerated(EnumType.STRING)
    UserTypes userType;

    @OneToOne
    ContactDetails contactDetails;

    String accessPrivileges; //TODO: add an enum for accessPrivileges


}
