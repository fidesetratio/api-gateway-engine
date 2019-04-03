package com.application.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.manager.model.AuthenticationProvider;

public interface AuthenticationProviderRepository extends CrudRepository<AuthenticationProvider,Long>{
	
	public AuthenticationProvider findByProviderName(String providerName);
	public AuthenticationProvider findByProviderId(Long providerId);
	public List<AuthenticationProvider> findByActiveTrue();

}
