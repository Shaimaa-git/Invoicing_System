package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    Supplier supplier = new Supplier();
    public Supplier addSupplier(@RequestBody Supplier supplierDetails) {
        supplierDetails.setShippingMethods("Air freight");
        supplierDetails.setIsActive(Boolean.TRUE);
        supplierDetails.setCreatedDate(new Date());
        supplierDetails.setUpdatedDate(new Date());
        supplierDetails.setCountry("China");
        supplierDetails.setCompanyName("SSS");
        supplierDetails.setMinimumOrderQuantity("12");
        supplierDetails.setId(1001);
        supplierDetails.setComplaints("no complaints");
        supplierDetails.setPaymentMethods("cache");
        supplierDetails.setNextDeliveryTime(new Date());

        supplier = supplierDetails;
        return supplierRepository.save(supplierDetails);
    }
    public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {


        ContactDetails pd = supplierUpdating.getContactDetails();
        pd.setUpdatedDate(new Date());

        supplierUpdating.setContactDetails(pd);
        supplierUpdating.setUpdatedDate(new Date());

        supplier = supplierUpdating;
        return supplierRepository.save(supplierUpdating);
    }
    public String deleteSupplier(@PathVariable Integer supplierId) {
        Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);

        if (supplierFromDb.getId().equals(supplierId)) {
            supplierFromDb.setIsActive(Boolean.FALSE);
            System.out.println(supplierFromDb.toString());
        }
        return "Success!";
    }
    public Supplier reportSupplier(){

        return supplierRepository.save(supplier);
    }


}
