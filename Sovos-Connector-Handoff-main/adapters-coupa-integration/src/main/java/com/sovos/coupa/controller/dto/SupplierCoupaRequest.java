package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class SupplierCoupaRequest {

  @Schema
  private String name;

  public String getName() {
    return name;
  }

  @XmlElement(name = "name")
  public void setName(String name) {
    this.name = name;
  }

  @Schema
  private String number;

  public String getNumber() {
    return number;
  }

  @XmlElement(name = "number")
  public void setNumber(String number) {
    this.number = number;
  }
}
