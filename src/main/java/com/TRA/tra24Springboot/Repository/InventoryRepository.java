package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i from Inventory i WHERE i.Id =:inventoryId")
    Inventory getByInventoryId(@Param("inventoryId") Integer inventoryId);
}
