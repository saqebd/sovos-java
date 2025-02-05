package com.sovos.coupa.controller.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "response")
@XmlSeeAlso({ErrorResponse.class})
public class BaseResponse implements Serializable {

  private static final long serialVersionUID = -2299284400878743109L;
  private boolean isSuccessful = true;
  private Integer status = 200;
  @XmlElement(name = "error")
  private List<ErrorResponse> errors;
  private String message;

  @JsonProperty
  public boolean getSuccess() {

    return isSuccessful;
  }

  public void setSuccess(boolean success) {

    this.isSuccessful = success;
  }

  public Integer getStatus() {

    return status;
  }

  public void setStatus(Integer status) {

    this.status = status;
  }

  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

  public void addError(ErrorResponse error) {

    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }

  public List<ErrorResponse> getErrors() {

    return errors;
  }

}
