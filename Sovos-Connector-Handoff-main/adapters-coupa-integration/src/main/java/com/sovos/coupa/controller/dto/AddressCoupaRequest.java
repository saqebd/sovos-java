package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class AddressCoupaRequest {

  @Schema
  private String street1;
  @Schema
  private String city;
  @Schema
  private String state;
  @Schema
  private CountryAddressCoupaRequest country;
  @Schema
  private String postalCode;
  @Schema
  private String locationCode;

  public String getStreet1() {
    return street1;
  }

  @XmlElement(name = "street1")
  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getCity() {
    return city;
  }

  @XmlElement(name = "city")
  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  @XmlElement(name = "state")
  public void setState(String state) {
    this.state = state;
  }

  public CountryAddressCoupaRequest getCountry() {
    return country;
  }

  @XmlElement(name = "country")
  public void setCountry(CountryAddressCoupaRequest country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  @XmlElement(name = "postal-code")
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getLocationCode() {
    return locationCode;
  }

  @XmlElement(name = "location-code")
  public void setLocationCode(String locationCode) {
    this.locationCode = locationCode;
  }
}
