package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o from Order o WHERE o.id =:orderId")
    Order getOrderById(@Param("orderId") Integer orderId );
}
