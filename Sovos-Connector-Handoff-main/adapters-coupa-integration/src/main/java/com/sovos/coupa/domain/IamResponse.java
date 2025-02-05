package com.sovos.coupa.domain;

import java.util.List;

public class IamResponse {

    private String status;
    private String message;
    private List<IamOrgResponse> data;
    private boolean success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IamOrgResponse> getData() {
        return data;
    }

    public void setData(List<IamOrgResponse> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

