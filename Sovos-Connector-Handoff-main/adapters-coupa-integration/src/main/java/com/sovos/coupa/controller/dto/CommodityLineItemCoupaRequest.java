package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class CommodityLineItemCoupaRequest {

  @Schema
  private String id;

  public String getId() {
    return id;
  }

  @XmlElement(name = "id")
  public void setId(String id) {
    this.id = id;
  }
}
