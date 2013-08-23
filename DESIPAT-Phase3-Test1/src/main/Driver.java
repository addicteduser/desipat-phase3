package main;

import finalLoginModule.LoginController;
import finalLoginModule.LoginModel;
import finalLoginModule.LoginView;
import finalNavigatorModule.NavigatorController;
import finalNavigatorModule.NavigatorModel;
import finalNavigatorModule.NavigatorView;

public class Driver {
	public static void main(String[] args) {
		new LoginController(new LoginView(), new LoginModel());
	}
}
