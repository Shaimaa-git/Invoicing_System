package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repository.ProductsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
@Service
public class ProductDetailsService {
    @Autowired
    ProductsDetailsRepository productsDetailsRepository;
    public ProductDetails saveProductDetails(ProductDetails productDetails){
        productDetails=new ProductDetails();
        productDetails.setCreatedDate(LocalDate.now().plusDays(30));
        productDetails.setExpiryDate(new Date());
        productDetails.setIsActive(Boolean.TRUE);
        productDetails.setDescription("make you full of energy");
        productDetails.setSize("big");
        productDetails.setCountryOfOrigin("KU");
        productDetails.setColor("red");
        productDetails.setName("Apple");
        productDetails.setPrice(10.0);
        return productsDetailsRepository.save(productDetails);
    }

}
