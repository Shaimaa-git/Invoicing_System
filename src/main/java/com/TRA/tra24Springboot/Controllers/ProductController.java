package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Repository.ProductRepository;
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
    @Autowired
    ProductRepository productRepository;
    @PostMapping("add")
    public Product addProduct(@RequestBody Product product){

        return productService.saveProduct(product);
    }
    @PostMapping("delete")
    public String deleteProduct(Integer productId){
        Product productFromDb = productRepository.getProductById(productId);
        productFromDb.setIsActive(Boolean.FALSE);
        productRepository.save(productFromDb);
        return "Success";
    }
    @PostMapping("update")
    public Product updateProduct(Product product){
        product.setUpdatedDate(new Date());
        return productService.updateProduct(product);
    }
}
