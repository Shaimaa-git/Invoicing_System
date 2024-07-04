package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public String deleteProductByName(String productName){

        Product productFromDb = productRepository.getByProductName(productName);
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

    public Product getProductByID(Integer productID){
        return productRepository.getProductByID(productID);
    }

    public List<Product> getProductByName(String productName){
        return productRepository.getProductByName(productName);
    }
    public List<Product> getProductByCountryOfOrigin(String country){
        return productRepository.getProductByCountryOfOrigin(country);
    }

    public List<Product> getProductBySize(String size){
        return productRepository.getProductBySize(size);
    }

    public List<Product> getProductByColor(String color){
        return productRepository.getProductByColor(color);
    }

    public Product getProductBySKU(UUID sku){
        return productRepository.getProductBySKU(sku);
    }

    public List<Product> getProductByCategory(String category){
        return productRepository.getProductByCategory(category);
    }

    public List<Product> getProductByPrice (Double price){
        return productRepository.getProductByPrice(price);
    }

    public List<Product> getProductByAvailability(Boolean isActive){
        return productRepository.getProductByAvailability(isActive);
    }
    public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 50) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
}
