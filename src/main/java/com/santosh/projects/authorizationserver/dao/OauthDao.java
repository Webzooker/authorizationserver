package com.santosh.projects.authorizationserver.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.santosh.projects.authorizationserver.model.UserEntity;
import com.santosh.projects.authorizationserver.util.DaoUtil;

@Repository
public class OauthDao {

	@Autowired
	private JdbcTemplate jdbcTemplete;

	public UserEntity getUserDetails(String username) {

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		List<UserEntity> userEntities = jdbcTemplete.query(DaoUtil.SELECT_USERS_BY_EMAIL_ID, new String[] { username },
				(ResultSet rs, int rowNum) -> {
					UserEntity user = new UserEntity();
					user.setEmailId(username);
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getString("user_id"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setMobile(rs.getString("mobile"));
					user.setCountry(rs.getString("country"));
					user.setUserType(rs.getString("user_type"));
					return user;
				});

		if (!userEntities.isEmpty()) {
			UserEntity userEntity = userEntities.get(0);

			if (userEntity.getUserType() != null) {
				// If user is not a super admin type
				if (!userEntity.getUserType().trim().equalsIgnoreCase("super_admin")) {
					List<String> permissions = jdbcTemplete.query(DaoUtil.SELECT_PERMISION_NAME_BY_EMAIL_ID,
							new String[] { username }, (ResultSet rs, int rowNum) -> {
								return "ROLE_" + rs.getString("permission_name");
							});
					if (permissions != null && !permissions.isEmpty()) {
						for (String permission : permissions) {
							GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
							grantedAuthorities.add(grantedAuthority);
						}
						userEntities.get(0).setGrantedAuthorities(grantedAuthorities);
					}
					return userEntities.get(0);
				} else {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("RLOE_SUPERADMIN");
					grantedAuthorities.add(grantedAuthority);
					userEntities.get(0).setGrantedAuthorities(grantedAuthorities);
					return userEntities.get(0);
				}
			} else {
				return null;
			}
		}
		return null;
	}
}
