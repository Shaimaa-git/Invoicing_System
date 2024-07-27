package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductsDetailsRepository productDetailsRepository;
    @Autowired
     ProductDetails productDetails;
    InventoryDTO inventoryDTO;
    @Autowired
    Supplier supplier;
    @Autowired
    Product product;
    @TrackExecutionTime
    public Inventory saveReceiveStock(@RequestBody Inventory inventory) {

        productDetails.setName("Laptop");
        productDetails.setColor("Black");
        productDetails.setPrice(350d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setQuantity(100);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        product = productRepository.save(product);

        Order order = new Order();
        order.setProductsOrdered(Arrays.asList(product)); //setting the products lists
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());
        order = orderRepository.save(order);

        supplier.setCompanyName("Dell");
        supplier.setOrders(Arrays.asList(order));
        supplier.setCountry("USA");
        //supplier.setContactDetails(contactDetails);
        supplier.setMinimumOrderQuantity(2);
        supplier.setCreatedDate(new Date());
        supplier.setIsActive(Boolean.TRUE);
        supplier = supplierRepository.save(supplier);

        inventory.setProducts(Arrays.asList(product));
        inventory.setLocation("Salalah");
        inventory.setManager("Noura");
        inventory.setPhoneNumber("12345778");
        inventory.setSupplier(Arrays.asList(supplier));
        inventory.setOpeningHours("8 AM");
        inventory.setClosingHours("8 PM");
        inventory.setWorkers(Arrays.asList("Jack", "Andrew", "Sam"));
        inventory.setCreatedDate(new Date());
        inventory.setIsActive(Boolean.TRUE);

        return inventoryRepository.save(inventory);
    }
    @TrackExecutionTime
    public Inventory saveReturnStock(@RequestBody Inventory inventoryItem) {

        inventoryItem.setId(1);
        inventoryItem.setUpdatedDate(new Date());
        return inventoryRepository.save(inventoryItem);
    }
    @TrackExecutionTime
    public String deleteInventoryById(Integer inventoryId){

        Inventory inventoryFromDb = inventoryRepository.getByInventoryId(inventoryId);
        inventoryFromDb.setIsActive(Boolean.FALSE);
        inventoryRepository.save(inventoryFromDb);
        return "Success";
    }
    @TrackExecutionTime
    public Inventory updateStock(@RequestBody Inventory inventory) {
        inventory.setUpdatedDate(new Date());
        return inventoryRepository.save(inventory) ;
    }
    @TrackExecutionTime
    public Inventory getInventoryById(Integer inventoryId){
        return inventoryRepository.getByInventoryId(inventoryId);
    }
    public List<Inventory> getInventoryByIsActive(Boolean isActive) {
        return inventoryRepository.getInventoryByAvailability(isActive);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoryByLocation(String location){
        return inventoryRepository.getInventoryByLocation(location);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoryByManagerName(String manager){
        return inventoryRepository.getInventoryByManagerName(manager);
    }

}
