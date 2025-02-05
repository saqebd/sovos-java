package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class DiscountLineItemGtdRequest {

    @Schema
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Schema
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
