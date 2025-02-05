package com.sovos.coupa.service;

import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.domain.IamOrgResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class S1IamService {

    @Autowired
    private S1RestService s1RestService;
    @Autowired
    private AccountTranslatorService translatorService;


    public CoupaAccount getAccount(String s1AccountId) {
        IamOrgResponse iamOrgResponse = s1RestService.getCoupaAccount(s1AccountId);
        CoupaAccount coupaAccount = translatorService.IAMOrgResponseToCoupaAccount(iamOrgResponse);

        return coupaAccount;
    }


}
