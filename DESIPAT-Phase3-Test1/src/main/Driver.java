package main;

import finalLoginModule.LoginController;
import finalLoginModule.LoginModel;
import finalLoginModule.LoginView;

public class Driver {
	public static void main(String[] args) {
		new LoginController(new LoginView(), new LoginModel());
	}
}
