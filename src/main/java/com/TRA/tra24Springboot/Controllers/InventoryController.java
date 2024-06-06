package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;



@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping("receive")
    public ResponseEntity<Inventory> receiveStock(@RequestBody Inventory inventoryItem) {
        try {
            Inventory savedItem = inventoryService.saveReceiveStock(inventoryItem);
            return new ResponseEntity<>(savedItem, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error saving received stock: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("return")
    public ResponseEntity<Inventory> returnStock(@RequestBody Inventory inventoryItem) {
        try {
            Inventory savedItem = inventoryService.saveReturnStock(inventoryItem);
            return new ResponseEntity<>(savedItem, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error saving returned stock: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("write-off")
    public <T> ResponseEntity<T> deleteInventoryById(@RequestParam Integer inventoryId) throws Exception {
        try {
            String result =inventoryService.deleteInventoryById(inventoryId);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<Inventory> updateStock(@RequestBody Inventory inventory) {
        try {
            Inventory updatedItem = inventoryService.updateStock(inventory);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating stock: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
