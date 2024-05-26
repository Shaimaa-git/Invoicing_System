package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDTO {
    Integer inventoryId;
    List<Product> products;
    String location;
    public static InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO inventoryDTO=new InventoryDTO();
        inventoryDTO.setLocation(inventory.getLocation());
        inventoryDTO.setProducts(inventory.getProducts());
        inventoryDTO.setInventoryId(inventory.getId());

        return inventoryDTO;
    }
    public static List<InventoryDTO> convertToDTO(List<Inventory> inventoryList) {
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();

        for (Inventory inventoryObjectFromDatabase: inventoryList) {

            InventoryDTO dto = convertToDTO(inventoryObjectFromDatabase);
            inventoryDTOS.add(dto);
        }
        return inventoryDTOS;
    }
}
