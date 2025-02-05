package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class CustomFieldsCoupaRequest {

  @Schema
  private boolean bypassGTD;

  public boolean getBypassGTD() {
    return bypassGTD;
  }

  @XmlElement(name = "bypass-gtd")
  public void setBypassGTD(boolean bypassGTD) {
    this.bypassGTD = bypassGTD;
  }

}
