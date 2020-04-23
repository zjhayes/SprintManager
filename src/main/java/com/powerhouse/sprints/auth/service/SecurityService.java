package com.powerhouse.sprints.auth.service;

public interface SecurityService {
	String findLoggedInUsername();

	void autoLogin(String username, String password);

}
