package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    //Query to get inventory by ID
    @Query("SELECT i FROM Inventory i WHERE i.id =:inventoryID")
    Inventory getByInventoryId(@Param("inventoryID") Integer inventoryID);

    //Query to git inventory by availability
    @Query("SELECT i FROM Inventory i WHERE i.isActive =:isActive")
    List<Inventory> getInventoryByAvailability(@Param("isActive") Boolean isActive);

    //Query to get inventory by location
    @Query("SELECT i FROM Inventory i WHERE i.location =:location")
    List<Inventory> getInventoryByLocation(@Param("location") String location);

    //Query to get inventory by admin name
    @Query("SELECT i FROM Inventory i WHERE i.manager =:manager")
    List<Inventory> getInventoryByManagerName(@Param("manager") String admin);
}
