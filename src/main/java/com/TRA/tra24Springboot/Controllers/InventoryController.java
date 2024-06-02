package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Models.Inventory;
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
    public Inventory receiveStock(@RequestBody Inventory inventoryItem) {

        return inventoryService.saveReceiveStock(inventoryItem);
    }
    @PostMapping("return")
    public Inventory returnStock(@RequestBody Inventory inventoryItem) {

        return inventoryService.saveReturnStock(inventoryItem);
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
}
