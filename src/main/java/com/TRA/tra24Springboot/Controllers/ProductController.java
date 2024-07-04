package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.MailingService;
import com.TRA.tra24Springboot.Services.ProductService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    MailingService mailingService;

    @Autowired
    SlackService slackService;

    @PostMapping("add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            mailingService.sendSimpleMail();
            Product savedProduct = productService.saveProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByCountry")
    public ResponseEntity<List<Product>> getProductByCountryOfOrigin(@RequestParam String country) {
        try {
            List<Product> products = productService.getProductByCountryOfOrigin(country);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by country of origin: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getBySize")
    public ResponseEntity<List<Product>> getProductBySize(@RequestParam String size) {
        try {
            List<Product> products = productService.getProductBySize(size);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by size: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByColor")
    public ResponseEntity<List<Product>> getProductByColor(@RequestParam String color) {
        try {
            List<Product> products = productService.getProductByColor(color);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by color: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByCategory")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by category: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByPrice")
    public ResponseEntity<List<Product>> getProductByPrice(@RequestParam Double price) {
        try {
            List<Product> products = productService.getProductByPrice(price);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by price: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByAvailability")
    public ResponseEntity<List<Product>> getProductByAvailability(@RequestParam Boolean isActive) {
        try {
            List<Product> products = productService.getProductByAvailability(isActive);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error getting product by availability: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkStock")
    @Scheduled(cron = "0 0 9/6 * * *")
    public List<Product> getLowStockReport() {

        List<Product> lowStockProducts = productService.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Low stock alert:\n");
            for (Product product : lowStockProducts) {
                messageBuilder.append("Product ID: ").append(product.getId())
                        .append(", Product: ").append(product.getProductDetails().getName())
                        .append(", Quantity: ").append(product.getQuantity()).append("\n");
            }
            slackService.sendMessage("shaimaa", messageBuilder.toString());
        }
        return lowStockProducts;
    }

}

