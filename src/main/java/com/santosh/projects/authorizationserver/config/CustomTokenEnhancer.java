package com.santosh.projects.authorizationserver.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.santosh.projects.authorizationserver.model.CustomUser;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		
		Map<String, Object> information = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
		
		if (customUser.getUserId() != null)
			information.put("userId", customUser.getUserId());
		if (customUser.getFirstName() != null)
			information.put("firstName", customUser.getFirstName());
		if (customUser.getLastName() != null)
			information.put("lastName", customUser.getLastName());
		if (customUser.getMobile() != null)
			information.put("mobile", customUser.getMobile());
		if (customUser.getCountry() != null)
			information.put("country", customUser.getCountry());
		if (customUser.getUserType() != null)
			information.put("userType", customUser.getUserType());
		
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(information);
		return super.enhance(accessToken, authentication);
	}
	
	

}
