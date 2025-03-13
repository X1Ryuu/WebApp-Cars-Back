/*
package com.example.demo.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomJwt extends JwtAuthenticationToken {
    private String username;

    private Collection<String> roles;


    public CustomJwt(Jwt jwt, Collection< ? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public List<GrantedAuthority> getAuthoritiesList() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }
}
*/

package com.example.demo.jwt;

import lombok.Setter;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class CustomJwt extends JwtAuthenticationToken {
    private final String username;
    private final Collection<String> roles;

    public CustomJwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String username, Collection<String> roles) {
        super(jwt, authorities);
        this.username = username;
        this.roles = roles;

    }


    public List<GrantedAuthority> getAuthoritiesList() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}