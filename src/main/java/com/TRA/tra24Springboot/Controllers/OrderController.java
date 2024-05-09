package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("create")
    public Order createOrder(@RequestBody Order order) {

        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        return order;
    }
    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        order.setOrderDate(new Date());
        return order;
    }
    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") String orderId,Order order) {
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELED);
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            return "Order with ID " + orderId + " has been canceled.";
        } else {
            return "Unable to cancel order. Order may not exist or may not be cancelable.";
        }
    }
}
