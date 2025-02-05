package com.sovos.coupa.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;

public class CustomFieldsLineItemCoupaRequest {

	@Schema
	private String customField1;

	public String getCustomField1() {
		return customField1;
	}

	@XmlElement(name = "sovos-custom-field-1")
	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	@Schema
	private String customField2;

	public String getCustomField2() {
		return customField2;
	}

	@XmlElement(name = "sovos-custom-field-2")
	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	@Schema
	private String customField3;

	public String getCustomField3() {
		return customField3;
	}

	@XmlElement(name = "sovos-custom-field-3")
	public void setCustomField3(String customField3) {
		this.customField3 = customField3;
	}

	@Schema
	private String customField4;

	public String getCustomField4() {
		return customField4;
	}

	@XmlElement(name = "sovos-custom-field-4")
	public void setCustomField4(String customField4) {
		this.customField4 = customField4;
	}

	@Schema
	private String customField5;

	public String getCustomField5() {
		return customField5;
	}

	@XmlElement(name = "sovos-custom-field-5")
	public void setCustomField5(String customField5) {
		this.customField5 = customField5;
	}

	@Schema
	private String customField6;

	public String getCustomField6() {
		return customField6;
	}

	@XmlElement(name = "sovos-custom-field-6")
	public void setCustomField6(String customField6) {
		this.customField6 = customField6;
	}

	@Schema
	private String customField7;

	public String getCustomField7() {
		return customField7;
	}

	@XmlElement(name = "sovos-custom-field-7")
	public void setCustomField7(String customField7) {
		this.customField7 = customField7;
	}

	@Schema
	private String customField8;

	public String getCustomField8() {
		return customField8;
	}

	@XmlElement(name = "sovos-custom-field-8")
	public void setCustomField8(String customField8) {
		this.customField8 = customField8;
	}

	@Schema
	private String customField9;

	public String getCustomField9() {
		return customField9;
	}

	@XmlElement(name = "sovos-custom-field-9")
	public void setCustomField9(String customField9) {
		this.customField9 = customField9;
	}

	@Schema
	private String customField10;

	public String getCustomField10() {
		return customField10;
	}

	@XmlElement(name = "sovos-custom-field-10")
	public void setCustomField10(String customField10) {
		this.customField10 = customField10;
	}
}
