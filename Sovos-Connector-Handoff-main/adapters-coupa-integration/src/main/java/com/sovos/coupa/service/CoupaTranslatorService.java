package com.sovos.coupa.service;

import static java.util.Objects.isNull;

import com.sovos.coupa.config.ConfigurationService;
import com.sovos.coupa.controller.dto.*;
import com.sovos.coupa.utility.Constants;
import com.sovos.coupa.utility.Utility;
import com.sovos.integrations.gtd.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CoupaTranslatorService {


    @Autowired
    private ConfigurationService configuration;

    public GtdRequest translateRequest(CoupaRequest coupaRequest) {

        GtdRequest gtdRequest = new GtdRequest();
        AuthenticationTranslate(gtdRequest);
        InvoiceTranslate(gtdRequest, coupaRequest);
        LineItemTranslate(gtdRequest, coupaRequest);

        return gtdRequest;
    }

    public CoupaResponse translateResponse(ResponseEntity<GtdResponse> gtdResponse) {

        CoupaResponse coupaResponse = new CoupaResponse();
        List<LineItemGtdResponse> gtdLinesList = gtdResponse.getBody().getLnRslts();
        List<LineItemCoupaResponse> coupaLinesList = new ArrayList<>();

        coupaResponse.setTaxEngineHeader(gtdResponse.getBody().getTxAmt());

        for (LineItemGtdResponse gtdLineItem : gtdLinesList) {

            LineItemCoupaResponse coupaLineItem = new LineItemCoupaResponse();
            coupaLineItem.setLineNum(gtdLineItem.getLnNm());
            TaxLineLineItemCoupaResponse taxLine = new TaxLineLineItemCoupaResponse();
            taxLine.setLineNum("1");
            taxLine.setTaxEngineLine(gtdLineItem.getTxAmt());
            coupaLineItem.setGtdTax(gtdLineItem.getTxAmt());
            TaxLineLineItemsCoupaResponse taxLines = new TaxLineLineItemsCoupaResponse();
            taxLines.setTaxLine(taxLine);
            coupaLineItem.setTaxLines(taxLines);
            coupaLinesList.add(coupaLineItem);
        }

        LineItemsCoupaResponse lineItems = new LineItemsCoupaResponse();
        lineItems.setInvoiceLines(coupaLinesList);
        coupaResponse.setInvoiceLines(lineItems);

        return coupaResponse;

    }

    private void AuthenticationTranslate(GtdRequest gtdRequest) {
        gtdRequest.setUsrname(configuration.getApiInformation().getUsername());
        gtdRequest.setPswrd(configuration.getApiInformation().getPassword());
    }

    private void InvoiceTranslate(GtdRequest gtdRequest, CoupaRequest coupaRequest) {
        gtdRequest.setIsAudit(coupaRequest.getPaid());
        //Removed trnId to stop using GTD pending since this is currently a quote call
        //gtdRequest.setTrnId(coupaRequest.getPurchaseNumber());
        gtdRequest.setCurrn(coupaRequest.getCurrency().getCode());
        gtdRequest.setDocDt(coupaRequest.getInvoiceDate());
        if (!isNull(coupaRequest.getInvoiceNumber()) && !coupaRequest.getInvoiceNumber().isEmpty()) {
            gtdRequest.setTrnDocNum(coupaRequest.getInvoiceNumber());
        }else{
            throw new IllegalStateException("Invoice Number not valid");
        }
        if (!isNull(configuration.getApiInformation().getClientUnit()) &&
            !configuration.getApiInformation().getClientUnit().isEmpty() &&
            !configuration.getApiInformation().getClientUnit().equals("null")) {
            gtdRequest.setTrnSrc("OVER_" + configuration.getApiInformation().getClientUnit());
        }else{
            throw new IllegalStateException("GTD Organization not valid");
        }
        gtdRequest.setTxCalcTp("1");

        gtdRequest.setDlvrAmt(String.valueOf(Float.valueOf(coupaRequest.getShippingAmount())+Float.valueOf(coupaRequest.getHandlingAmount())+Float.valueOf(coupaRequest.getMiscAmount())));

    }

    private void LineItemTranslate(GtdRequest gtdRequest, CoupaRequest coupaRequest) {

        LineItemsCoupaRequest linesList = coupaRequest.getLinesItems();
        List<LineItemCoupaRequest> linesList2 = linesList.getLineItems();

        List<LineItemGtdRequest> lines = new ArrayList<>();

        for (LineItemCoupaRequest coupaLineItem : linesList2) {

            LineItemGtdRequest gtdLineItem = new LineItemGtdRequest();

            CalculationLineItemTranslate(gtdLineItem, coupaLineItem, coupaRequest);
            LocationLineItemTranslate(gtdLineItem, coupaRequest);

            lines.add(gtdLineItem);
        }
        gtdRequest.setLines(lines);
    }

    private void CalculationLineItemTranslate(LineItemGtdRequest gtdLineItem, LineItemCoupaRequest coupaLineItem, CoupaRequest coupaRequest) {

        if(coupaRequest.getDocumentType().equals("Credit Note")){
            gtdLineItem.setDebCredIndr("2");
        }

        gtdLineItem.setGoodSrvCd(coupaLineItem.getCommodity().getId());
        gtdLineItem.setGoodSrvDesc(coupaLineItem.getDescription());
        gtdLineItem.setLnItmNum(coupaLineItem.getLineNum());

        if (coupaLineItem.getType().equals(Constants.AMOUNT_LINE)) {
            gtdLineItem.setQnty("1");
            gtdLineItem.setGrossAmt(String.valueOf(Float.parseFloat(coupaLineItem.getPrice())));
        } else {
            gtdLineItem.setQnty(coupaLineItem.getQuantity());
            gtdLineItem.setGrossAmt(String.valueOf(Float.parseFloat(coupaLineItem.getPrice()) * Float.parseFloat(coupaLineItem.getQuantity())));
        }

        gtdLineItem.setOrgCd(configuration.getApiInformation().getClientUnit());
        gtdLineItem.setTransactionTypeEnum(TransactionType.SALE);
        gtdLineItem.setCustVendName(coupaRequest.getSupplier().getName());
        gtdLineItem.setCustVendCd(coupaRequest.getSupplier().getNumber());
        gtdLineItem.setDlvrDt(coupaRequest.getDeliveryDate());

        CustomFieldsLineItemGtdRequest custAttrbs = new CustomFieldsLineItemGtdRequest();
        gtdLineItem.setCustAttrbs(custAttrbs);
        boolean emptyCustom = true;

        if (coupaLineItem.getCustomFields().getCustomField1() != null && !coupaLineItem.getCustomFields().getCustomField1().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_1(coupaLineItem.getCustomFields().getCustomField1());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField2() != null && !coupaLineItem.getCustomFields().getCustomField2().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_2(coupaLineItem.getCustomFields().getCustomField2());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField3() != null && !coupaLineItem.getCustomFields().getCustomField3().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_3(coupaLineItem.getCustomFields().getCustomField3());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField4() != null && !coupaLineItem.getCustomFields().getCustomField4().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_4(coupaLineItem.getCustomFields().getCustomField4());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField5() != null && !coupaLineItem.getCustomFields().getCustomField5().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_5(coupaLineItem.getCustomFields().getCustomField5());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField6() != null && !coupaLineItem.getCustomFields().getCustomField6().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_6(coupaLineItem.getCustomFields().getCustomField6());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField7() != null && !coupaLineItem.getCustomFields().getCustomField7().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_7(coupaLineItem.getCustomFields().getCustomField7());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField8() != null && !coupaLineItem.getCustomFields().getCustomField8().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_8(coupaLineItem.getCustomFields().getCustomField8());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField9() != null && !coupaLineItem.getCustomFields().getCustomField9().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_9(coupaLineItem.getCustomFields().getCustomField9());
            emptyCustom = false;
        }
        if (coupaLineItem.getCustomFields().getCustomField10() != null && !coupaLineItem.getCustomFields().getCustomField10().isEmpty()){
            gtdLineItem.getCustAttrbs().set_custom_field_10(coupaLineItem.getCustomFields().getCustomField10());
            emptyCustom = false;
        }
        if(emptyCustom){
            gtdLineItem.setCustAttrbs(null);
        }
    }

    private void LocationLineItemTranslate(LineItemGtdRequest gtdLineItem, CoupaRequest coupaRequest) {

		if (coupaRequest.getRemitToAddress() != null && Utility.validateAddress(coupaRequest.getRemitToAddress())) {
			gtdLineItem.setsFStateProv(coupaRequest.getRemitToAddress().getState());
			gtdLineItem.setsFCountry(coupaRequest.getRemitToAddress().getCountry().getCode());
			gtdLineItem.setsFStNameNum(coupaRequest.getRemitToAddress().getStreet1());
			gtdLineItem.setsFCity(coupaRequest.getRemitToAddress().getCity());
			gtdLineItem.setsFPstlCd(coupaRequest.getRemitToAddress().getPostalCode());
		}

		if (coupaRequest.getShipToAddress() != null && Utility.validateAddress(coupaRequest.getShipToAddress())) {
			gtdLineItem.setsTStateProv(coupaRequest.getShipToAddress().getState());
			gtdLineItem.setsTCountry(coupaRequest.getShipToAddress().getCountry().getCode());
			gtdLineItem.setsTStNameNum(coupaRequest.getShipToAddress().getStreet1());
			gtdLineItem.setsTCity(coupaRequest.getShipToAddress().getCity());
			gtdLineItem.setsTPstlCd(coupaRequest.getShipToAddress().getPostalCode());
		}else{
			throw new IllegalStateException("Ship To field not valid");
		}

		if (coupaRequest.getBillToAddress() != null && Utility.validateAddress(coupaRequest.getBillToAddress())) {
			gtdLineItem.setbTStateProv(coupaRequest.getBillToAddress().getState());
			gtdLineItem.setbTCountry(coupaRequest.getBillToAddress().getCountry().getCode());
			gtdLineItem.setbTStNameNum(coupaRequest.getBillToAddress().getStreet1());
			gtdLineItem.setbTCity(coupaRequest.getBillToAddress().getCity());
			gtdLineItem.setbTPstlCd(coupaRequest.getBillToAddress().getPostalCode());
		}
    }
}
