package com.TRA.tra24Springboot.Repository;

import com.TRA.tra24Springboot.Models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDetailsRepository extends JpaRepository<ProductDetails, Integer> {
}
