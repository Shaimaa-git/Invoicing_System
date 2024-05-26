package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsService productDetailsService;
    public Product saveProduct(Product product){
        product.setSku(UUID.randomUUID());
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());
        product.setUpdatedDate(new Date());
        ProductDetails productDetails=productDetailsService.saveProductDetails(product.getProductDetails());
        product.setProductDetails(productDetails);
        return productRepository.save(product);
    }
    public String deleteProduct(String productName){
        Product productFromDb = productRepository.getByProductName(productName);
        productFromDb.setIsActive(Boolean.FALSE);
        productRepository.save(productFromDb);
        return "Success";
    }
    public String deleteProductById(Integer productId){

        Product productFromDb = productRepository.getProductById(productId);
        productFromDb.setIsActive(Boolean.FALSE);
        productRepository.save(productFromDb);
        return "Success";
    }

    public String updateProductQuantity(Integer productId, Integer quantity) {
        Product productFromDb = productRepository.getProductById(productId);
        productFromDb.setQuantity(quantity);
        productFromDb.setUpdatedDate(new Date());

        productRepository.save(productFromDb);
        return "Updated Successfully";
    }
    public Product updateProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<ProductDTO> getProduct(){
        List <Product> products = productRepository.findAll();

        return ProductDTO.convertToDTO(products);
    }
}
