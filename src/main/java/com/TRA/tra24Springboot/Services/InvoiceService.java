package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repository.InvoiceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
    public class InvoiceService {
        @Autowired
         InvoiceRepository invoiceRepository;
        @Autowired
        ProductService productService;

      public Invoice createInvoice(Invoice invoice){
          Product product=new Product();
          invoice.setIsActive(Boolean.TRUE);
          invoice.setCreatedDate(new Date());
          invoice.setDueDate(new Date());
          Product products=productService.saveProduct(product);
          invoice.setListOfProduct(Arrays.asList(products));

          invoice.setPaidAmount(78.5);
          invoice.setTotalAmount(45.6);
          return invoiceRepository.save(invoice);
    }
    public  Invoice  getInvoiceById(Integer id){
          return invoiceRepository.getByInvoiceId(id);
    }


}
