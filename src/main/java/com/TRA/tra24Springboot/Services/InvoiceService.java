package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repository.InvoiceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
          invoice.setCreatedDate(LocalDate.now().plusDays(30));
          invoice.setDueDate(LocalDate.now().plusDays(30));
          Product products=productService.saveProduct(product);
          invoice.setListOfProduct(Arrays.asList(products));

          invoice.setPaidAmount(78.5);
          invoice.setTotalAmount(45.6);
          return invoiceRepository.save(invoice);
    }
    public  Invoice  getInvoiceById(Integer id){

          return invoiceRepository.getByInvoiceId(id);
    }
    public List<Invoice> findInvoicesDueInNextDays(int days) {
        LocalDate dueDateThreshold = LocalDate.now().plusDays(days);
        return invoiceRepository.findByDueDateBefore(dueDateThreshold);
    }
    public List<Invoice> findOverdueInvoices() {
        LocalDate today = LocalDate.now();
        return invoiceRepository.findByDueDateBeforeAndIsActive(today, true);
    }
    public List<Invoice> findInvoicesCreatedInLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusDays(7);
        return invoiceRepository.findByCreatedDateBetween(lastWeek, today);
    }

    public List<Invoice> findPaidInvoicesInLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusDays(7);
        return invoiceRepository.findByPaidAmountGreaterThanAndCreatedDateBetween(0.0, lastWeek, today);
    }

    public List<Invoice> findOverdueInvoicesInLastWeek() {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusDays(7);
        return invoiceRepository.findByDueDateBeforeAndCreatedDateBetween(today, lastWeek, today);
    }
    public List<Invoice> findInvoicesCreatedInLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        return invoiceRepository.findByCreatedDateBetween(firstDayOfMonth, today);
    }

    public List<Invoice> findPaidInvoicesInLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        return invoiceRepository.findByPaidAmountGreaterThanAndCreatedDateBetween(0.0, firstDayOfMonth, today);
    }

    public List<Invoice> findOverdueInvoicesInLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        return invoiceRepository.findByDueDateBeforeAndCreatedDateBetween(today, firstDayOfMonth, today);
    }
}

