package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierDTO {
    String companyName;
    String country;
    List<ProductDTO> productsOffered;
    List<OrderDTO> orders;
    ProductDTO productDTO;
    OrderDTO orderDTO;
    public static SupplierDTO convertToDTO(Supplier supplierDTO) {
        SupplierDTO supplier= new SupplierDTO();
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setCountry(supplierDTO.getCountry());
        supplier.setProductsOffered(ProductDTO.convertToDTO(supplierDTO.getProductsOffered()));

        return supplier;
    }
    public static List<SupplierDTO> convertToDTO(List<Supplier> supplierList) {
        List<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplierObjectFromDatabase: supplierList) {

            SupplierDTO dto = convertToDTO(supplierObjectFromDatabase);
            supplierDTOS.add(dto);
        }
        return supplierDTOS;
    }

}
