package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class LineItemCoupaRequest {

  @Schema
  private String lineNum;

  public String getLineNum() {
    return lineNum;
  }

  @XmlElement(name = "line-num")
  public void setLineNum(String lineNum) {
    this.lineNum = lineNum;
  }

  @Schema
  private String price;

  public String getPrice() {
    return price;
  }

  @XmlElement(name = "price")
  public void setPrice(String price) {
    this.price = price;
  }

  @Schema
  private String quantity;

  public String getQuantity() {
    return quantity;
  }

  @XmlElement(name = "quantity")
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  @Schema
  private String type;

  public String getType() {
    return type;
  }

  @XmlElement(name = "type")
  public void setType(String type) {
    this.type = type;
  }

  @Schema
  private String discountAmount;

  public String getDiscountAmount() {
    return discountAmount;
  }

  @XmlElement(name = "discount-amount")
  public void setDiscountAmount(String discountAmount) {
    this.discountAmount = discountAmount;
  }

  @Schema
  private String description;

  public String getDescription() {
    return description;
  }

  @XmlElement(name = "description")
  public void setDescription(String description) {
    this.description = description;
  }

  @Schema
  private CommodityLineItemCoupaRequest commodity;

  public CommodityLineItemCoupaRequest getCommodity() {
    return commodity;
  }

  @XmlElement(name = "commodity")
  public void setCommodity(CommodityLineItemCoupaRequest commodity) {
    this.commodity = commodity;
  }

  @Schema
  private CustomFieldsLineItemCoupaRequest customFields;

  public CustomFieldsLineItemCoupaRequest getCustomFields() {
    return customFields;
  }

  @XmlElement(name = "custom-fields")
  public void setCustomFields(CustomFieldsLineItemCoupaRequest customFields) {
    this.customFields = customFields;
  }
}
