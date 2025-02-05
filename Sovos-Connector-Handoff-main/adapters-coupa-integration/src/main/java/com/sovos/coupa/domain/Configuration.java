package com.sovos.coupa.domain;

public class Configuration {

    private String s1ConfigurationId;
    private String name;
    private String title;
    private String type;
    private boolean required;
    private String value;
    private float maxLength;

    public String getS1ConfigurationId() {
        return s1ConfigurationId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public boolean getRequired() {
        return required;
    }

    public String getValue() {
        return value;
    }

    public float getMaxLength() {
        return maxLength;
    }

    public void setS1ConfigurationId(String s1ConfigurationId) {
        this.s1ConfigurationId = s1ConfigurationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setMaxLength(float maxLength) {
        this.maxLength = maxLength;
    }

}
