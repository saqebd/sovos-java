package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class TaxLineLineItemsCoupaResponse {

  @Schema
  private TaxLineLineItemCoupaResponse taxLine;

  public TaxLineLineItemCoupaResponse getTaxLine() {
    return taxLine;
  }

  @XmlElement(name = "tax-line")
  public void setTaxLine(TaxLineLineItemCoupaResponse taxLine) {
    this.taxLine = taxLine;
  }
}
