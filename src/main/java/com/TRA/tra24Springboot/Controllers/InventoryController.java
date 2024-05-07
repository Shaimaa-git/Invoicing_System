package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Models.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;



@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private Inventory globalInventoryItem = new Inventory();

    @PostMapping("receive")
    public Inventory receiveStock(@RequestBody Inventory inventoryItem) {
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
        globalInventoryItem = inventoryItem;
        return inventoryItem;
    }
    @PostMapping("return")
    public Inventory returnStock(@RequestBody Inventory inventoryItem) {

        inventoryItem.setId(1);
        inventoryItem.setUpdatedDate(new Date());

        globalInventoryItem = inventoryItem;
        return inventoryItem;
    }
    @PostMapping("write-off")
    public Inventory writeOffInventory(@RequestBody Inventory inventoryItem) {
        inventoryItem.setIsActive(Boolean.FALSE);
        inventoryItem.setUpdatedDate(new Date());
        inventoryItem.setWriteOffDate(new Date());
        globalInventoryItem = inventoryItem;
        return inventoryItem;
    }
}
