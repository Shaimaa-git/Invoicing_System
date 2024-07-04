package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Services.InvoiceService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
