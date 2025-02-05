package com.sovos.coupa.service;

import com.sovos.coupa.domain.Configuration;
import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.domain.IamOrgResponse;
import com.sovos.coupa.domain.OrgConfigurationMapping;
import com.sovos.coupa.domain.Solution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTranslatorService {
    private static final String GTD_ORG_CODE = "gtd_org_code";
    private static final String GTD_USR = "gtd_username";

    public CoupaAccount IAMOrgResponseToCoupaAccount(IamOrgResponse iamOrgResponse) {
        CoupaAccount coupaAccount = new CoupaAccount();
        if (iamOrgResponse != null && iamOrgResponse.getOrgConfigurationMappings().size() != 0) {
            List<Solution> solutions = new ArrayList<>();
            coupaAccount.setS1AccountId(iamOrgResponse.getId());
            for (OrgConfigurationMapping orgConfig : iamOrgResponse.getOrgConfigurationMappings()
            ) {
                Solution solution = new Solution();
                solution.setId(orgConfig.getSolutionReference().getName());
                for (Configuration config :
                        orgConfig.getConfiguration()) {
                    switch (config.getName()) {
                        case GTD_ORG_CODE:
                            solution.setGtdOrgCode(config.getValue());
                            break;
                        case GTD_USR:
                            solution.setGtdUsername(config.getValue());
                            break;
                    }
                }
                solutions.add(solution);
            }
            coupaAccount.setSolutions(solutions);
        } else {
            coupaAccount = null;
        }
        return coupaAccount;
    }
}
