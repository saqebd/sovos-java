/**
 * Copyright (c) 2020 by Sovos Compliance
 */
package com.sovos.coupa.domain;

import java.util.List;

public class CoupaAccount {

    private String id;
    private String s1AccountId;
    private List<Solution> solutions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS1AccountId() {
        return s1AccountId;
    }

    public void setS1AccountId(String id) {
        this.s1AccountId = id;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }


}
