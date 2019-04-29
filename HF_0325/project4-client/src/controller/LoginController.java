package controller;

import model.LoginModel;
import model.UserPOJO;
import view.View;

public class LoginController {
	LoginModel loginModel;
	View view;

	public LoginController(LoginModel loginModel, View view) {
		this.loginModel = loginModel;
		this.view = view;
	}
	
	
	
	public void logIn(String userName, String password) {
		loginModel.logIn(userName, password);
		UserPOJO user = loginModel.getUser();
		if(user.getAccessLevel()==0){
			view.loggedInAccess0(userName);
		} else if(user.getAccessLevel()==1){
			view.loggedInAccess1(userName);
		} else {
			view.invalidUserOrPw();
		}
//		view.setUserName(user.getUserName());
//		view.setAccessLevel(user.getAccessLevel());
	}

	public void logOut() {
		loginModel.logOut();
	}
	
}
