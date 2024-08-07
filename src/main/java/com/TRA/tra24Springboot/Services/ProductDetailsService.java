package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
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
    @TrackExecutionTime
    public ProductDetails saveProductDetails(ProductDetails productDetails){
        productDetails.setCreatedDate(new Date());
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
