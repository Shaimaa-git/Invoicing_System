package com.TRA.tra24Springboot.Models;

import lombok.Builder;
import lombok.Data;


public enum OrderStatus {
    PENDING, CANCELED, IN_PROGRESS, SHIPPED, COMPLETED, IN_TRANSIT
}
