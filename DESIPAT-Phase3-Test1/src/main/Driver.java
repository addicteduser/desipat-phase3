package main;

import finalLoginModule.LoginController;
import finalLoginModule.LoginModel;
import finalLoginModule.loginView;

public class Driver {
	public static void main(String[] args) {
		new LoginController(new loginView(), new LoginModel());
	}
}
