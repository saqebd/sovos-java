package com.sovos.coupa.domain;

import java.util.ArrayList;

public class OrgConfigurationMapping {

    private SolutionReference solutionReference;
    private ArrayList<Configuration> configuration = new ArrayList<>();

    public SolutionReference getSolutionReference() {
        return solutionReference;
    }

    public void setSolutionReference(SolutionReference solutionReference) {
        this.solutionReference = solutionReference;
    }

    public ArrayList<Configuration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ArrayList<Configuration> configuration) {
        this.configuration = configuration;
    }
}


