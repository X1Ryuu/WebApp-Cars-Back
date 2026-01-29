
package com.example.demo.jwt;

import org.springframework.core.convert.converter.Converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {
    @Override
    public CustomJwt convert(Jwt jwt) {
        //   System.out.println(jwt.getHeaders()+ " "+jwt.getClaims()+" "+ jwt.getAudience());

        Collection<String> roles = extractRoles(jwt);
        String username = Optional.ofNullable(jwt.getClaimAsString("preferred_username"))
                .orElse(jwt.getClaimAsString("client_id"));
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return new CustomJwt(jwt, authorities, username, roles);
    }

    private Collection<String> extractRoles(Jwt jwt) {
        Collection<String> roles = new HashSet<>();

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles.addAll((Collection<String>) realmAccess.get("roles"));
        }

        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                Map<String, Object> clientRoles = (Map<String, Object>) entry.getValue();
                if (clientRoles.containsKey("roles")) {
                    roles.addAll((Collection<String>) clientRoles.get("roles"));
                }
            }
        }

        return roles;
    }

}