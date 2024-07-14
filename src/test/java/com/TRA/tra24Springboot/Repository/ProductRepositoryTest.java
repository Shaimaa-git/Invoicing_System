package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.BaseEntity;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  ProductsDetailsRepository productDetailsRepository;
    @BeforeEach
    void setUp() {
        ProductDetails details1 = ProductDetails.builder()
                .name("Springfield Elementary")
                .color("Red")
                .price(10.0)
                .countryOfOrigin("USA")
                .size("Large")
                .expiryDate(new Date())
                .description("Educational Institution")

                .build();
        details1.setCreatedDate(new Date());
        details1.setIsActive(Boolean.TRUE);
        productDetailsRepository.save(details1);
    }

    @Test
    void getByProductName() {
        List<Product> products = productRepository.getProductByName("Springfield Elementary");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Springfield Elementary", products.get(0).getProductDetails().getName());
    }
    @Test
    void getProductByID() {
        Product product = productRepository.findById(productRepository.findAll().get(0).getId()).orElse(null);
        assertNotNull(product);
        assertEquals("Springfield Elementary", product.getProductDetails().getName());
    }

    @Test
    void getProductByCountryOfOrigin() {
        List<Product> products = productRepository.getProductByCountryOfOrigin("Oman");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Oman", products.get(0).getProductDetails().getCountryOfOrigin());
    }

    @Test
    void getProductBySize() {
        List<Product> products = productRepository.getProductBySize("Large");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Large", products.get(0).getProductDetails().getSize());
    }

    @Test
    void getProductByColor() {
        List<Product> products = productRepository.getProductByColor("Blue");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Blue", products.get(0).getProductDetails().getColor());
    }


    @Test
    void getProductByCategory() {
        List<Product> products = productRepository.getProductByCategory("Education");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Education", products.get(0).getCategory());
    }

    @Test
    void getProductByPrice() {
        List<Product> products = productRepository.getProductByPrice(15.0);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(15.0, products.get(0).getProductDetails().getPrice());
    }

    @Test
    void getProductByAvailability() {
        List<Product> productsAvailability = productRepository.getProductByAvailability(Boolean.TRUE);
        assertThat(productsAvailability).isNotNull();
        assertThat(productsAvailability.size()).isEqualTo(2);
        assertThat(productsAvailability.get(0).getIsActive()).isEqualTo(Boolean.TRUE);
    }
}