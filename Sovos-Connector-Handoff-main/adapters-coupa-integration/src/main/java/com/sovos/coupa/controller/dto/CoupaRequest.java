package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "invoice-header")
public class CoupaRequest {

    @Schema
    private String purchaseNumber;
    @Schema
    private String paid;
    @Schema
    private CurrencyCoupaRequest currency;
    @Schema
    private String invoiceDate;
    @Schema
    private String invoiceNumber;
    @Schema
    private String grossAmount;
    @Schema
    private AddressCoupaRequest shipToAddress;
    @Schema
    private AddressCoupaRequest billToAddress;
    @Schema
    private AddressCoupaRequest remitToAddress;
    @Schema
    private LineItemsCoupaRequest lineItems;
    @Schema
    private SupplierCoupaRequest supplier;
    @Schema
    private String deliveryDate;
    @Schema
    private CustomFieldsCoupaRequest customFields;
    @Schema
    private String documentType;
    @Schema
    private String shippingAmount;
    @Schema
    private String handlingAmount;
    @Schema
    private String miscAmount;


    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    @XmlElement(name = "id")
    public void setPurchaseNumber(String purchaseNumber) {

        this.purchaseNumber = purchaseNumber;
    }

    @XmlElement(name = "paid")
    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public CurrencyCoupaRequest getCurrency() {
        return currency;
    }

    @XmlElement(name = "currency")
    public void setCurrency(CurrencyCoupaRequest currency) {
        this.currency = currency;
    }

    public String getInvoiceDate() {

        return invoiceDate;
    }

    @XmlElement(name = "invoice-date")
    public void setInvoiceDate(String invoiceDate) {

        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {

        return invoiceNumber;
    }

    @XmlElement(name = "invoice-number")
    public void setInvoiceNumber(String invoiceNumber) {

        this.invoiceNumber = invoiceNumber;
    }

    public String getGrossAmount() {

        return grossAmount;
    }

    @XmlElement(name = "gross-total")
    public void setGrossAmount(String grossAmount) {

        this.grossAmount = grossAmount;
    }

    public AddressCoupaRequest getShipToAddress() {

        return shipToAddress;
    }

    @XmlElement(name = "ship-to-address")
    public void setShipToAddress(AddressCoupaRequest shipToAddress) {

        this.shipToAddress = shipToAddress;
    }

    public AddressCoupaRequest getBillToAddress() {

        return billToAddress;
    }

    @XmlElement(name = "bill-to-address")
    public void setBillToAddress(AddressCoupaRequest billToAddress) {

        this.billToAddress = billToAddress;
    }

    public AddressCoupaRequest getRemitToAddress() {

        return remitToAddress;
    }

    @XmlElement(name = "remit-to-address")
    public void setRemitToAddress(AddressCoupaRequest remitToAddress) {

        this.remitToAddress = remitToAddress;
    }


    public LineItemsCoupaRequest getLinesItems() {

        return lineItems;
    }

    @XmlElement(name = "invoice-lines")
    public void setLinesItems(LineItemsCoupaRequest lineItems) {

        this.lineItems = lineItems;
    }

    public SupplierCoupaRequest getSupplier() {

        return supplier;
    }

    @XmlElement(name = "supplier")
    public void setSupplier(SupplierCoupaRequest supplier) {

        this.supplier = supplier;
    }

    public String getDeliveryDate() {

        return deliveryDate;
    }

    @XmlElement(name = "delivery-date")
    public void setDeliveryDate(String deliveryDate) {

        this.deliveryDate = deliveryDate;
    }

    public CustomFieldsCoupaRequest getCustomFields() {

        return customFields;
    }

    @XmlElement(name = "custom-fields")
    public void setCustomFields(CustomFieldsCoupaRequest customFields) {

        this.customFields = customFields;
    }

    public String getDocumentType() {
        return documentType;
    }

    @XmlElement(name = "document-type")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getShippingAmount() {
        return shippingAmount;
    }

    @XmlElement(name = "shipping-amount")
    public void setShippingAmount(String shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public String getHandlingAmount() {
        return handlingAmount;
    }

    @XmlElement(name = "handling-amount")
    public void setHandlingAmount(String handlingAmount) {
        this.handlingAmount = handlingAmount;
    }

    public String getMiscAmount() {
        return miscAmount;
    }

    @XmlElement(name = "misc-amount")
    public void setMiscAmount(String miscAmount) {
        this.miscAmount = miscAmount;
    }
}
