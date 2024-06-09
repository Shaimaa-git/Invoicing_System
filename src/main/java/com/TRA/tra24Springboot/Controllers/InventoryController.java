package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


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
    @GetMapping("getById")
    public ResponseEntity<Inventory> getInventoryById(@RequestParam Integer inventoryId) {
        try {
            Inventory inventory = inventoryService.getInventoryById(inventoryId);
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting inventory by ID: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByAvailability")
    public ResponseEntity<List<Inventory>> getInventoryByAvailability(@RequestParam Boolean isActive) {
        try {
            List<Inventory> inventoryList = inventoryService.getInventoryByIsActive(isActive);
            return new ResponseEntity<>(inventoryList, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting inventory by availability: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByLocation")
    public ResponseEntity<List<Inventory>> getInventoryByLocation(@RequestParam String location) {
        try {
            List<Inventory> inventoryList = inventoryService.getInventoryByLocation(location);
            return new ResponseEntity<>(inventoryList, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting inventory by location: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByAdmin")
    public ResponseEntity<List<Inventory>> getInventoryByManagerName(@RequestParam String manager) {
        try {
            List<Inventory> inventoryList = inventoryService.getInventoryByManagerName(manager);
            return new ResponseEntity<>(inventoryList, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting inventory by manager name: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
