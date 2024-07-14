package com.TRA.tra24Springboot.Repository;


import com.TRA.tra24Springboot.Models.BaseEntity;
import com.TRA.tra24Springboot.Models.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class InventoryRepositoryTest extends BaseEntity {
    @Autowired
    InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        Inventory inventory1 =Inventory.builder()
                .location("Warehouse A")
                .manager("Manager1")
                .build();

        Inventory inventory2 = Inventory.builder()
                .location("Warehouse B")
                .manager("Manager2")
                .build();

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);
    }
    @Test
    public void getByInventoryId() {
        Inventory inventory = inventoryRepository.getByInventoryId(1);
        assertThat(inventory).isNotNull();
        assertThat(inventory.getId()).isEqualTo(1);
    }
    @Test
    public void getInventoryByAvailability() {
        List<Inventory> inventories = inventoryRepository.getInventoryByAvailability(true);
        assertThat(inventories).hasSize(1);
        assertThat(inventories.get(0).getIsActive()).isEqualTo(true);
    }
    @Test
    public void getInventoryByLocation() {
        List<Inventory> inventories = inventoryRepository.getInventoryByLocation("Warehouse B");
        assertThat(inventories).hasSize(1);
        assertThat(inventories.get(0).getLocation()).isEqualTo("Warehouse B");
    }
    @Test
    public void getInventoryByManagerName() {
        List<Inventory> inventories = inventoryRepository.getInventoryByManagerName("Manager1");
        assertThat(inventories).hasSize(1);
        assertThat(inventories.get(0).getManager()).isEqualTo("Manager1");
    }

}