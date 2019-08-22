package com.application.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.Logger;



public class SinarmasMsigTokenRemoteServices  implements ResourceServerTokenServices{
	  	private RestTemplate restTemplate;
	  

	    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
	    
	    
	    private String clientId;
	    private String clientSecret;
	    private String url;
	    
	    
	    
	    public SinarmasMsigTokenRemoteServices() {
	        restTemplate = new RestTemplate();
	        ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
	            @Override
	            // Ignore 400
	            public void handleError(ClientHttpResponse response) throws IOException {
	                if (response.getRawStatusCode() != 400) {
	                    super.handleError(response);
	                }
	            }
	        });
	    }
	
	    @Override
	    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
	    	HttpHeaders headers = new HttpHeaders();
	    	Base64.Encoder encoder = Base64.getEncoder();
	    	String normalString = clientId+":"+clientSecret;
	    	String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
	    	//http://localhost:8787/oauth/check_token
	        headers.add("Authorization", "Basic "+encodedString);	
	        Map<String, Object> map = executeGet(this.url.trim()+"?token="+accessToken.trim(), headers);
	        if (map == null || map.isEmpty() || map.get("error") != null) {
	            throw new InvalidTokenException("Token not allowed");
	        }
	        OAuth2Authentication authAuthenModel =  tokenConverter.extractAuthentication(map);
	        authAuthenModel.setDetails(map);
	        return authAuthenModel;
	    }


	    @Override
	    public OAuth2AccessToken readAccessToken(String accessToken) {
	        throw new UnsupportedOperationException("Not supported: read access token");
	    }
	    
	    private Map<String, Object> executeGet(String path, HttpHeaders headers) {
	        try {
	            if (headers.getContentType() == null) {
	                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	            };
	            HttpEntity<String> entity = new HttpEntity<String>(headers);
	            Map map = restTemplate.exchange(path, HttpMethod.GET, entity, Map.class).getBody();
	            Map<String, Object> result = map;
	            return result;
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        return null;
	    }

		public String getClientId() {
			return clientId;
		}

		public void setClientId(String clientId) {
			this.clientId = clientId;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

}
