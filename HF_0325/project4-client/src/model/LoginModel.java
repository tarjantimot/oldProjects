package model;

public class LoginModel {
	private UserPOJO user;
	private final Server SERVER = new Server();
	
	public void logIn(String userName, String password){
		user = SERVER.login(userName, password);
		if(null == user){
			this.user.setUserName("");
			this.user.setAccessLevel(-1);
		}
	}

	public UserPOJO getUser() {
		return user;
	}

	public void setUser(UserPOJO user) {
		this.user = user;
	}

	public void logOut() {
		user = null;
	}

}
