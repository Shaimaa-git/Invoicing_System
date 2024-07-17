package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repository.InvoiceRepository;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
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
        @Autowired
        Product product;

    public Invoice createInvoice(Invoice invoice) {
        invoice.setListOfProduct(Arrays.asList(productService.getProductByID(802)));
        invoice.setCreatedDate(new Date());
        invoice.setDueDate(new Date());
        invoice.setTotalAmount(350.0);

        Date dueDate = DateHelperUtils.addDays(invoice.getCreatedDate(), 7);
        invoice.setDueDate(dueDate);

        return invoiceRepository.save(invoice);
    }

    public Invoice getBInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceById(id);
    }

    public List<Invoice> getInvoiceByCreatedDate(Date createdDate) {
        return invoiceRepository.getInvoiceByCreatedDate(createdDate);
    }

    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepository.getInvoiceByDueDate(dueDate);
    }

    // method to get invoices due in next few days
    public List<Invoice> getInvoiceDueInNextDays(Integer days){
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepository.getInvoicesByDueDateBetween(today, dueDate);
    }

    //method to get overdue invoices
    public List<Invoice> getOverDueInvoices(){
        Date today = new Date();
        return invoiceRepository.getOverdueInvoices(today);
    }

    //method to
    public List<Invoice> getInvoicesCreatedBetween(Date startDate, Date endDate) {
        return invoiceRepository.getInvoicesCreatedBetween(startDate, endDate);
    }

    public List<Invoice> getPaidInvoicesBetween(Date startDate, Date endDate) {
        return invoiceRepository.getPaidInvoicesBetween(startDate, endDate);
    }

}

