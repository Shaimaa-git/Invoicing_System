package com.TRA.tra24Springboot.Repository;


import com.TRA.tra24Springboot.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT in from Invoice in WHERE in.Id =:invoiceId")
    Invoice getByInvoiceId(@Param("invoiceId") Integer invoiceId);

    @Query("SELECT v FROM Invoice v WHERE v.id =:id")
    public Invoice getInvoiceById(@Param("id") Integer id);

    @Query("SELECT v FROM Invoice v WHERE v.createdDate =:createdDate")
    public List<Invoice> getInvoiceByCreatedDate(@Param("createdDate") Date createdDate);

    @Query("SELECT v FROM Invoice v WHERE v.dueDate =:dueDate")
    public List<Invoice> getInvoiceByDueDate(@Param("dueDate") Date dueDate);

    @Query("SELECT v FROM Invoice v WHERE v.dueDate BETWEEN :startDate AND :endDate")
    public List<Invoice> getInvoicesByDueDateBetween(@Param("startDate") Date startDate,
                                                     @Param("endDate") Date endDate);

    @Query("SELECT v FROM Invoice v WHERE v.dueDate < :today")
    public List<Invoice> getOverdueInvoices(@Param("today") Date today);

    @Query("SELECT v FROM Invoice v WHERE v.createdDate BETWEEN :startDate AND :endDate")
    public List<Invoice> getInvoicesCreatedBetween(@Param("startDate") Date startDate,
                                                   @Param("endDate") Date endDate);

    @Query("SELECT v FROM Invoice v WHERE v.paymentDate BETWEEN :startDate AND :endDate")
    public List<Invoice> getPaidInvoicesBetween(@Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);
}