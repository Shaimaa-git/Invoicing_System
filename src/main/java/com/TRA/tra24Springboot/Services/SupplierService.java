package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    Supplier supplier = new Supplier();
    public Supplier addSupplier(@RequestBody Supplier supplierDetails) {
        supplierDetails.setShippingMethods("Air freight");
        supplierDetails.setIsActive(Boolean.TRUE);
        supplierDetails.setCreatedDate(LocalDate.now().plusDays(30));
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
    public List<Supplier> getSupplierByCompanyName (String companyName) throws  Exception{
        try {
            List<Supplier> suppliers = supplierRepository.getSupplierByCompanyName(companyName);
            if (suppliers.isEmpty()) {
                throw new Exception("No Supplier found with Company Name " + companyName);
            }
            return suppliers;
        }catch (Exception e) {
            throw new Exception("Failed to retrieve suppliers by Company name: " + e.getMessage(), e);}
    }
    public List<Supplier> getSupplierByCountry (String countr) throws  Exception{
        try {
            List<Supplier> suppliers = supplierRepository.getSupplierByCountry(countr);
            if (suppliers.isEmpty()) {
                throw new Exception("No Supplier found with Country  Name " + countr);
            }
            return suppliers;
        }catch (Exception e){
            throw new Exception("Failed to retrieve suppliers by Country  Name"+e.getMessage());
        }

    }

    public List<Supplier> getSupplierByMinimumOrderQuantity (Integer minimumOrderQuantity) throws  Exception{
        try{
            List<Supplier> suppliers = supplierRepository.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
            if (suppliers.isEmpty()){
                throw new Exception("No Suppliers found with Minimum Order Quantity"+minimumOrderQuantity);
            }
            return suppliers;
        }catch (Exception e){
            throw  new Exception("Failed to retrieve suppliers By Minimum Order Quantity"+e.getMessage());
        }
    }

    public List<Supplier> getSupplierByIsActive (Boolean isActive)throws  Exception{
        try {
            List<Supplier> suppliers = supplierRepository.findBySupplierByIsActive(isActive);
            if(suppliers.isEmpty()){
                throw  new Exception("No suppliers found with isActive: " + isActive);
            }
            return  suppliers;
        }catch (Exception e){
            throw new Exception("Failed to retrieve suppliers With isActive : " + e.getMessage(), e);
        }
    }

    public List<Supplier> findBySupplierByShippingMethods (String shippingMethods) throws  Exception{
        try {
            List<Supplier> suppliers = supplierRepository.findBySupplierByShippingMethods(shippingMethods);
            if(suppliers.isEmpty()){
                throw  new Exception("No suppliers found with  this shipping Method: " + shippingMethods);
            }
            return  suppliers;
        }catch (Exception e){
            throw new Exception("Failed to retrieve suppliers shipping Method : " + e.getMessage(), e);
        }
    }
    public List<Supplier> getSupplierByPaymentMethod (PaymentType paymentMethods) throws  Exception {
        try {
            List<Supplier> suppliers = supplierRepository.getSupplierByPaymentMethod(paymentMethods);
            if (suppliers.isEmpty()) {
                throw new Exception("No suppliers found with this Payment method " + paymentMethods);
            }
            return suppliers;
        } catch (Exception e) {
            throw new Exception("Failed to retrieve suppliers Payment method : " + e.getMessage(), e);
        }
    }

}
