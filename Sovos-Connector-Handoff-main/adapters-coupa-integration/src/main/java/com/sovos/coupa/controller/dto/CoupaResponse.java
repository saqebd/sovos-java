package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoice-header")
public class CoupaResponse {

  @Schema
  private String taxEngineHeader;

  public String getTaxEngineHeader() {
    return taxEngineHeader;
  }

  @XmlElement(name = "tax-engine-header")
  public void setTaxEngineHeader(String taxEngineHeader) {
    this.taxEngineHeader = taxEngineHeader;
  }

  @Schema
  private LineItemsCoupaResponse invoiceLines;

  public LineItemsCoupaResponse getInvoiceLines() {
    return invoiceLines;
  }

  @XmlElement(name = "invoice-lines")
  public void setInvoiceLines(LineItemsCoupaResponse invoiceLines) {
    this.invoiceLines = invoiceLines;
  }
}
