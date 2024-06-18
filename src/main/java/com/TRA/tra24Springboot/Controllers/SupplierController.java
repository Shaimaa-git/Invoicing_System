package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Repository.SupplierRepository;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @GetMapping("getByCompanyName")
    public ResponseEntity<?> getSupplierByCompanyName(@RequestParam String companyName) {
        try {
            List<Supplier> suppliers = supplierService.getSupplierByCompanyName(companyName);
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving suppliers by company name  failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByCountry")
    public ResponseEntity<?> getSupplierByCountry(@RequestParam String country) {
        try {
            List<Supplier> suppliers = supplierService.getSupplierByCountry(country);
            return new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving suppliers by contry name failed!"+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByMinQuantity")
    public ResponseEntity<?> getSupplierByMinimumOrderQuantity(@RequestParam Integer minimumOrderQuantity) {
        try {
            List<Supplier> suppliers = supplierService.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers ByMinimumOrderQuantity faild "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByIsActive")
    public ResponseEntity<?> getSupplierByIsActive(@RequestParam Boolean isActive) {
        try {
            List<Supplier> suppliers = supplierService.getSupplierByIsActive(isActive);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByShippingMethods")
    public ResponseEntity<?> findBySupplierByShippingMethods(@RequestParam String shippingMethods) {
        try{
            List<Supplier> suppliers = supplierService.findBySupplierByShippingMethods(shippingMethods);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by Shipping Method ! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByPayment")
    public ResponseEntity<?> getSupplierByPaymentMethod(@RequestParam PaymentType paymentMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSupplierByPaymentMethod(paymentMethods);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by Payment Type  ! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

