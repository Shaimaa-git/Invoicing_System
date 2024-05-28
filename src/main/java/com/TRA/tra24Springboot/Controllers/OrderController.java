package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Repository.OrderRepository;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("create")
    public Order createOrder(@RequestBody Order order) {

        return orderService.createOrder(order);
    }
    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }
    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(Integer orderId) {
        return orderService.cancelOrder(orderId);
    }
}
