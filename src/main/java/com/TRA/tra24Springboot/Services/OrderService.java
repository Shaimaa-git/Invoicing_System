package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public Order createOrder(@RequestBody Order order) {

        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        return orderRepository.save(order);
    }
    public Order updateOrder(@RequestBody Order order) {
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }
    public String cancelOrder(Integer orderId) {
        Order orderFromDb = orderRepository.getOrderById(orderId);
        if (orderFromDb != null && orderFromDb.getStatus() == OrderStatus.IN_PROGRESS) {
            orderFromDb.setStatus(OrderStatus.CANCELED);
            if (orderFromDb.getPaymentStatus() == PaymentStatus.PAID) {
                orderFromDb.setPaymentStatus(PaymentStatus.REJECTED);
            }
            return "Order with ID " + orderId + " has been canceled.";
        } else {
            return "Unable to cancel order. Order may not exist or may not be cancelable.";
        }
    }
}
