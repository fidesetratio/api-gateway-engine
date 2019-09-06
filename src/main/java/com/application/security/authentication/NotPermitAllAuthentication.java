package com.application.security.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class NotPermitAllAuthentication implements Authentication{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "gaga";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList("NOT ALLOWED");
		
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return new Object();
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return "gaa";
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
