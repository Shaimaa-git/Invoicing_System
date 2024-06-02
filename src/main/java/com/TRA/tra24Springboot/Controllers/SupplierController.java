package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Repository.SupplierRepository;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping("add")
    public Supplier addSupplier(@RequestBody Supplier supplierDetails) {
        return supplierService.addSupplier(supplierDetails);
    }

    @PutMapping("update")
    public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {
        return supplierService.updateSupplier(supplierUpdating);
    }

    @PostMapping("delete/{id}")
    public <T> ResponseEntity<T> deleteSupplierById(@RequestParam Integer id) throws Exception {
        try {
            String result = supplierService.deleteSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("get")
    public Supplier reportSupplier(){

        return supplierService.reportSupplier();
    }
}

