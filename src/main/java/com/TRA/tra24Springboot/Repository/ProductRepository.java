package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer> {
    @Query("SELECT p from Product p WHERE p.productDetails.name =:productName")
    Product getByProductName( @Param("productName") String productName);

    @Query("SELECT p from Product p WHERE p.id =:productId")
    Product getProductById( @Param("productId") Integer ProductId );

    //Query to get product by ID
    @Query("SELECT p FROM Product p WHERE p.id =:productID")
    Product getProductByID(@Param("productID") Integer productID);

    //Query to get product by name
    @Query("SELECT p FROM Product p WHERE p.productDetails.name =:product")
    List<Product> getProductByName (@Param("product") String product);


    //Query to get product by country of origin
    @Query ("SELECT p FROM Product p WHERE p.productDetails.countryOfOrigin =:country")
    List<Product> getProductByCountryOfOrigin(@Param("country") String countryOfOrigin);

    //Query to get product by size
    @Query("SELECT p FROM Product p WHERE p.productDetails.size =:size")
    List<Product> getProductBySize(@Param("size") String size);


    //Query to get product by color
    @Query("SELECT p FROM Product p WHERE p.productDetails.color =:color")
    List<Product> getProductByColor(@Param("color") String color);

    //Query to get product by SKU
    @Query("SELECT p FROM Product p WHERE p.sku =:sku")
    Product getProductBySKU(@Param("sku") UUID sku);

    //Query to get product by category
    @Query("SELECT p FROM Product p WHERE p.category =:category")
    List<Product> getProductByCategory(@Param("category") String category);

    //Query to get product by price
    @Query("SELECT p FROM Product p WHERE p.productDetails.price =:price")
    List<Product> getProductByPrice(@Param("price") Double price);

    @Query("SELECT p FROM Product p WHERE p.isActive =:isActive")
    List<Product> getProductByAvailability(@Param("isActive") Boolean isActive);

}
