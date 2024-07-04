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
}
