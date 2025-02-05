package com.sovos.coupa.domain;


import java.util.ArrayList;

public class IamOrgResponse {

    private String id;
    private String parentId;
    private String name;
    private String description = null;
    private String client;
    private String rootOrg;
    ArrayList<OrgConfigurationMapping> orgConfigurationMappings = new ArrayList<>();

    private String customAttributes = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRootOrg() {
        return rootOrg;
    }

    public void setRootOrg(String rootOrg) {
        this.rootOrg = rootOrg;
    }

    public ArrayList<OrgConfigurationMapping> getOrgConfigurationMappings() {
        return orgConfigurationMappings;
    }

    public void setOrgConfigurationMappings(ArrayList<OrgConfigurationMapping> orgConfigurationMappings) {
        this.orgConfigurationMappings = orgConfigurationMappings;
    }

    public String getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(String customAttributes) {
        this.customAttributes = customAttributes;
    }
}

