package controllers;

import services.auth.AuthService;
import services.auth.LoginRequestData;
import services.auth.View;

public class AuthController {
	
	private AuthService authService;
	
	public Object login(LoginRequestData data, View view) {
		return this.authService.login(data, view);
	}
}
