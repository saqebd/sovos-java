package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

public class LineItemsCoupaResponse {

  @Schema
  private List<LineItemCoupaResponse> invoiceLines;

  public List<LineItemCoupaResponse> getInvoiceLines() {
    return invoiceLines;
  }

  @XmlElement(name = "invoice-line")
  public void setInvoiceLines(List<LineItemCoupaResponse> invoiceLines) {
    this.invoiceLines = invoiceLines;
  }
}
