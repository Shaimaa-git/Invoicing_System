package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.MailingService;
import com.TRA.tra24Springboot.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    MailingService mailingService;

    @PostMapping("add")
    public Product addProduct(@RequestBody Product product){
        mailingService.sendSimpleMail();

        return productService.saveProduct(product);
    }
    @PostMapping("deleteById")
    public <T> ResponseEntity<T> deleteProductById(@RequestParam Integer id) throws Exception {
        try {
            String result = productService.deleteProductById(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("deleteByName")
    public <T> ResponseEntity<T> deleteProductByName(@RequestParam String productName) throws Exception{
        try {
            String result = productService.deleteProductByName(productName);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    public String updateProduct(Product product){
        product.setUpdatedDate(new Date());
        return productService.updateProductQuantity(product.getId(), product.getQuantity());
    }

}
