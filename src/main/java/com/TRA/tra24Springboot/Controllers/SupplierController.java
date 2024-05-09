package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    Supplier supplier = new Supplier();

    @PostMapping("add")
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
        return supplierDetails;
    }

    @PutMapping("update")
    public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {


        ContactDetails pd = supplierUpdating.getContactDetails();
        pd.setUpdatedDate(new Date());

        supplierUpdating.setContactDetails(pd);
        supplierUpdating.setUpdatedDate(new Date());

        supplier = supplierUpdating;
        return supplierUpdating;
    }

    @PostMapping("delete/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        if (supplier.getId().equals(id)) {
            supplier.setIsActive(Boolean.FALSE);
            System.out.println(supplier.toString());
        }
        return "Success!";
    }
}

