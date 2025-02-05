package com.sovos.coupa.controller.dto;

import jakarta.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 8980577370944340385L;

  private String subcode;

  private String message;

  @XmlElement(name = "subcode")
  public String getSubcode() {

    return subcode;
  }

  public void setSubcode(String subcode) {

    this.subcode = subcode;
  }

  @XmlElement(name = "message")
  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

}
