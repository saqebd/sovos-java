package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoice-line")
public class LineItemCoupaResponse {

  @Schema
  private String LineNum;
  @Schema
  private TaxLineLineItemsCoupaResponse taxLine;
  @Schema
  private String gtdTax;

  public String getLineNum() {
    return LineNum;
  }

  @XmlElement(name = "line-num")
  public void setLineNum(String lineNum) {
    LineNum = lineNum;
  }

  public TaxLineLineItemsCoupaResponse getTaxLines() {
    return taxLine;
  }

  @XmlElement(name = "tax-lines")
  public void setTaxLines(TaxLineLineItemsCoupaResponse taxLine) {
    this.taxLine = taxLine;
  }

  public String getGtdTax() {
    return gtdTax;
  }

  @XmlElement(name = "gtd-tax")
  public void setGtdTax(String gtdTax) {
    this.gtdTax = gtdTax;
  }
}
