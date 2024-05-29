package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("add")
    public Product addProduct(@RequestBody Product product){

        return productService.saveProduct(product);
    }
    @PostMapping("deleteById")
    public String deleteProductById(@RequestParam Integer productId){
        return productService.deleteProductById(productId);
    }
    @PostMapping("deleteByName")
    public String deleteProductByName(@RequestParam String productName){
        return productService.deleteProduct(productName);
    }

    @PostMapping("update")
    public String updateProduct(Product product){
        product.setUpdatedDate(new Date());
        return productService.updateProductQuantity(product.getId(), product.getQuantity());
    }
}
