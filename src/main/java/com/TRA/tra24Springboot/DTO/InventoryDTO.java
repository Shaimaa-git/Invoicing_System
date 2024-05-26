package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Product;
import lombok.Data;

import java.util.List;

@Data
public class InventoryDTO {
    Integer inventoryId;
    List<Product> products;
    String location;

}
