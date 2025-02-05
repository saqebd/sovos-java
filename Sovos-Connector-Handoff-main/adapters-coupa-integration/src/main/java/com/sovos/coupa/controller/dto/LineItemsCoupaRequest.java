package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

public class LineItemsCoupaRequest {

  @Schema
  private List<LineItemCoupaRequest> lineItems;

  public List<LineItemCoupaRequest> getLineItems() {
    return lineItems;
  }

  @XmlElement(name = "invoice-line")
  public void setLineItems(List<LineItemCoupaRequest> lineItems) {
    this.lineItems = lineItems;
  }
}
