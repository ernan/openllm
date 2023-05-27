package com.fodala.security;

import org.springframework.security.core.GrantedAuthority;

class GrantedAuthorityImpl implements GrantedAuthority {
    final String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
