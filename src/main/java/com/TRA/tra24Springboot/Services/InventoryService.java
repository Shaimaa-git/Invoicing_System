package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    public Inventory saveReceiveStock(@RequestBody Inventory inventoryItem) {
        inventoryItem.setId(1);
        inventoryItem.setCreatedDate(new Date());
        inventoryItem.setUpdatedDate(new Date());
        inventoryItem.setPhoneNumber("91767067");
        inventoryItem.setClosingHours("10:00 PM");
        inventoryItem.setSupplier("chine company");
        inventoryItem.setManager("SHaimaa");
        inventoryItem.setLocation("Nizwa");
        inventoryItem.setIsActive(Boolean.TRUE);
        inventoryItem.setOpeningHours("8:00AM");
        return inventoryRepository.save(inventoryItem);
    }
    public Inventory saveReturnStock(@RequestBody Inventory inventoryItem) {

        inventoryItem.setId(1);
        inventoryItem.setUpdatedDate(new Date());
        return inventoryRepository.save(inventoryItem);
    }
    public String deleteInventoryById(Integer inventoryId){

        Inventory inventoryFromDb = inventoryRepository.getByInventoryId(inventoryId);
        inventoryFromDb.setIsActive(Boolean.FALSE);
        inventoryRepository.save(inventoryFromDb);
        return "Success";
    }
    public Inventory updateStock(@RequestBody Inventory inventory) {
        inventory.setUpdatedDate(new Date());
        return inventoryRepository.save(inventory) ;
    }
    public Inventory getInventoryById(Integer inventoryId){
        return inventoryRepository.getByInventoryId(inventoryId);
    }
    public List<Inventory> getInventoryByIsActive(Boolean isActive) {
        return inventoryRepository.getInventoryByAvailability(isActive);
    }
    public List<Inventory> getInventoryByLocation(String location){
        return inventoryRepository.getInventoryByLocation(location);
    }
    public List<Inventory> getInventoryByManagerName(String manager){
        return inventoryRepository.getInventoryByManagerName(manager);
    }

}
