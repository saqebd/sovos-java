package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class CurrencyCoupaRequest {

  @Schema
  private String code;

  public String getCode() {
    return code;
  }

  @XmlElement(name = "code")
  public void setCode(String code) {
    this.code = code;
  }
}
