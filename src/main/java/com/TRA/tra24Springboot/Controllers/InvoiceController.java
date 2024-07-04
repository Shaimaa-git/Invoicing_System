package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Services.InvoiceService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
  InvoiceService invoiceService;
    @Autowired
    SlackService slackService;
  @PostMapping("createInvoice")
  public Invoice createInvoice(Invoice invoice){
      slackService.sendMessage("shaimaa", "Invoice created successfully");
      return invoiceService.createInvoice(invoice);
  }
    @GetMapping("/DueDate")
    @Scheduled(cron = "0 0 9 * * ?")  // every day at 9 AM
    public void sendDueReminders() {
        List<Invoice> dueInvoices = invoiceService.findInvoicesDueInNextDays(7);  // Remind 7 days in advance
        for (Invoice invoice : dueInvoices) {
            String message = "Reminder: Invoice " + invoice.getId() + " is due on " + invoice.getDueDate();
            slackService.sendMessage("shaimaa", message);
        }
    }
    @GetMapping("/OverDue")
    @Scheduled(cron = "0 0 10 * * ?")  // every day at 10 AM
    public void sendOverdueAlerts() {
        List<Invoice> overdueInvoices = invoiceService.findOverdueInvoices();
        for (Invoice invoice : overdueInvoices) {
            String message = "Alert: Invoice " + invoice.getId() + " is overdue. Due date was " + invoice.getDueDate();
            slackService.sendMessage("shaimaa", message);
        }
    }
    @GetMapping("/weeklyReport")
    @Scheduled(cron = "0 0 9 ? * MON")  // every Monday at 9 AM
    public void sendWeeklySummaryReport() {
        List<Invoice> createdInvoices = invoiceService.findInvoicesCreatedInLastWeek();
        List<Invoice> paidInvoices = invoiceService.findPaidInvoicesInLastWeek();
        List<Invoice> overdueInvoices = invoiceService.findOverdueInvoicesInLastWeek();

        StringBuilder report = new StringBuilder("Weekly Summary Report:\n\n");

        report.append("Created Invoices:\n");
        for (Invoice invoice : createdInvoices) {
            report.append("Invoice ID: ").append(invoice.getId()).append(", Due Date: ").append(invoice.getDueDate()).append("\n");
        }

        report.append("\nPaid Invoices:\n");
        for (Invoice invoice : paidInvoices) {
            report.append("Invoice ID: ").append(invoice.getId()).append(", Paid Amount: ").append(invoice.getPaidAmount()).append("\n");
        }

        report.append("\nOverdue Invoices:\n");
        for (Invoice invoice : overdueInvoices) {
            report.append("Invoice ID: ").append(invoice.getId()).append(", Due Date: ").append(invoice.getDueDate()).append("\n");
        }

        slackService.sendMessage("shaimaa", report.toString());
    }
}
