package controllers;

import services.auth.AuthService;
import services.auth.LoginRequestData;
import services.auth.View;

public class AuthController {
	
	private AuthService authService;
	
	public Object login(LoginRequestData data) {
		return this.authService.loginDoctor(data);
	}
}
