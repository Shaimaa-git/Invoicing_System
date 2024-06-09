package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Repository.OrderRepository;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error creating order: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        try {
            Order updatedOrder = orderService.updateOrder(order);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating order: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cancel/{orderId}")
    public <T> ResponseEntity<T> deleteOrderById(@RequestParam Integer id) throws Exception {
        try {
            String result = orderService.cancelOrder(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getById")
    public ResponseEntity<?> getOrderById(@RequestParam Integer orderId){
        try {
            Order order = orderService.getOrderById(orderId);
            return  new ResponseEntity<>(order, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Retrieving order failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByStatus")
    public ResponseEntity<?>  getOrderByStatus(@RequestParam OrderStatus orderStatus){
        try {
            List<Order> orders= orderService.getOrderByStatus(orderStatus);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving orders by status failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPaymentStatus")
    public ResponseEntity<?> getOrderByPaymentStatus(@RequestParam PaymentStatus paymentStatus){
        try {
            List<Order> orders= orderService.getOrderByPaymentStatus(paymentStatus);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving orders by payment status  failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByPaymentType")
    public ResponseEntity<?> getOrderByPaymentType(@RequestParam PaymentType paymentType){
        try {
            List<Order>orders =orderService.getOrderByPaymentType(paymentType);
            return  new ResponseEntity<>(orders,HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving orders by payment type failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCategoryName")
    public ResponseEntity<?> getOrderByPaymentType(@RequestParam String categoryName){
        try {
            List<Order> orders =orderService.getOrderByCategoryName(categoryName);
            return  new ResponseEntity<>(orders,HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving orders by category Name failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByIsActive")
    public ResponseEntity<?> getOrderByIsActive(@RequestParam Boolean isActive) {

        try {
            List<Order> orders= orderService.getOrderByIsActive(isActive);
            return  new ResponseEntity<>(orders,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving orders by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
