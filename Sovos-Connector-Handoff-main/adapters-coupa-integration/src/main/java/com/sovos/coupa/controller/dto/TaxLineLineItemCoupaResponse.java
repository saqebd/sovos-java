package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tax-line")
public class TaxLineLineItemCoupaResponse {

  public TaxLineLineItemCoupaResponse() {
  }

  @Schema
  private String LineNum;

  public String getLineNum() {
    return LineNum;
  }

  @XmlElement(name = "line-num")
  public void setLineNum(String lineNum) {
    LineNum = lineNum;
  }

  @Schema
  private String taxEngineLine;

  public String getTaxEngineLine() {
    return taxEngineLine;
  }

  @XmlElement(name = "tax-engine-line")
  public void setTaxEngineLine(String taxEngineLine) {
    this.taxEngineLine = taxEngineLine;
  }

  @Schema
  private String taxEngineRate;

  public String getTaxEngineRate() {
    return taxEngineRate;
  }

  @XmlElement(name = "rate-engine")
  public void setTaxEngineRate(String taxEngineRate) {
    this.taxEngineRate = taxEngineRate;
  }

  @Schema
  private String taxEngineCode;

  public String getTaxEngineCode() {
    return taxEngineCode;
  }

  @XmlElement(name = "code-engine")
  public void setTaxEngineCode(String taxEngineCode) {
    this.taxEngineCode = taxEngineCode;
  }
}
