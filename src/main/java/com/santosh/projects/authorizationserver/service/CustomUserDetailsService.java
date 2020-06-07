package com.santosh.projects.authorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santosh.projects.authorizationserver.dao.OauthDao;
import com.santosh.projects.authorizationserver.model.CustomUser;
import com.santosh.projects.authorizationserver.model.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	OauthDao oauthDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = null;
		
		try {
			userEntity = oauthDao.getUserDetails(username);
			
			if (userEntity != null && userEntity.getUserId() != null && !"".equalsIgnoreCase(userEntity.getUserId())) {
				CustomUser customUser = new CustomUser(userEntity);
				return customUser;
			}else {
				throw new UsernameNotFoundException("User"+username+"was not found in database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User"+username+"was not found in database");
		}
		
	}

	
	
}
