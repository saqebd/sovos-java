package com.sovos.platform.security;

import static org.apache.commons.text.StringEscapeUtils.escapeJava;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sovos.coupa.domain.CoupaAccount;
import com.sovos.coupa.utility.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtToken extends JwtTokenUtil {

    private static final long serialVersionUID = 5998998170695540205L;
    private static final Logger LOGGER = LogManager.getLogger(JwtToken.class);

    public DecodedJWT decodeToken(String authToken) {

        return JWT.decode(authToken);
    }

    public UserDetails getUserDetails(String correlationId, String authToken, CoupaAccount account) {

        CoupaUser coupaUser = null;

        try {
            DecodedJWT jwt = JWT.decode(authToken);
            String email = jwt.getClaim("email").asString();
            List<Authority> authorities = new ArrayList<>();
            if (!jwt.getClaim("scope").isNull()) {
                String[] scopes = jwt.getClaim("scope").asArray(String.class);

                for (int i = 0; i < scopes.length; ++i) {
                    Authority auth = new Authority(scopes[i]);
                    authorities.add(auth);
                }
            }

            coupaUser = new CoupaUser(email, this.mapToGrantedAuthorities(authorities), jwt);
            coupaUser.setAccount(account);
        } catch (Exception e) {
            LOGGER.error(String.format(Constants.GENERAL_LOG_FORMAT, escapeJava(correlationId), "Error getting user details, returning null"), e);
        }

        return coupaUser;
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {

        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }
}
