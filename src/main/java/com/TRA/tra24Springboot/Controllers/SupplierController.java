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
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplierDetails) {
        try {
            Supplier addedSupplier = supplierService.addSupplier(supplierDetails);
            return new ResponseEntity<>(addedSupplier, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error adding supplier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplierUpdating) {
        try {
            Supplier updatedSupplier = supplierService.updateSupplier(supplierUpdating);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating supplier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Supplier> reportSupplier() {
        try {
            Supplier reportedSupplier = supplierService.reportSupplier();
            return new ResponseEntity<>(reportedSupplier, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error reporting supplier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

