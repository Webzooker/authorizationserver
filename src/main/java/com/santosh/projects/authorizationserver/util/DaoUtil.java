package com.santosh.projects.authorizationserver.util;

public class DaoUtil {

	public static final String SELECT_USERS_BY_EMAIL_ID = "SELECT * FROM users WHERE email_id=? ";
	public static final String SELECT_PERMISION_NAME_BY_EMAIL_ID = "SELECT p.permission_name FROM users u \r\n"
			+ "INNER JOIN role_users r_u ON u.user_id = r_u.user_id\r\n"
			+ "INNER JOIN role r ON r_u.role_id = r.role_id\r\n"
			+ "INNER JOIN role_permission r_p ON r_p.role_id = r.role_id\r\n"
			+ "INNER JOIN permission p ON p.permission_id = r_p.permission_id where u.email_id=?";
}
