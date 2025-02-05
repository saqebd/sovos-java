package com.sovos.platform.security;

import java.util.Collection;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;

import com.sovos.coupa.domain.CoupaAccount;

public class CoupaUser extends JwtUser {

    private static final long serialVersionUID = 1L;
    private CoupaAccount account;

    public CoupaUser(String username, Collection<? extends GrantedAuthority> authorities,
                     DecodedJWT token) {

        super(username, authorities, token);
    }


    @Override
    public String getPassword() {

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public CoupaAccount getAccount() {

        return account;
    }

    public void setAccount(CoupaAccount account) {

        this.account = account;
    }

}
