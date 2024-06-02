package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String writeOffInventory(@RequestBody Integer inventoryId) {
        return inventoryService.deleteInventoryById(inventoryId);
    }
}
