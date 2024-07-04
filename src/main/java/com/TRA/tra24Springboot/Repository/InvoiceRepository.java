package com.TRA.tra24Springboot.Repository;


import com.TRA.tra24Springboot.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT in from Invoice in WHERE in.Id =:invoiceId")
    Invoice getByInvoiceId(@Param("invoiceId") Integer invoiceId);

    @Query("SELECT in from Invoice in WHERE in.dueDate =:invoiceDueDate")
    Invoice getByInvoiceDueDate(@Param("invoiceDueDate") Date invoiceDueDate);

    @Query("SELECT in from Invoice in WHERE in.dueDate =:invoiceCreatedDate")
    Invoice getByInvoiceCreatedDate(@Param("invoiceCreatedDate") Date invoiceCreatedDate);

}
