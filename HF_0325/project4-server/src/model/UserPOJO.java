
package model;

import java.io.Serializable;

public class UserPOJO implements Serializable{
	
	private String userName;
	private int accessLevel;

	public UserPOJO(String username, int accessLevel) {
		this.userName = username;
		this.accessLevel = accessLevel;
	}
	
	public int getAccessLevel() {
		return accessLevel;
	}

	public String getUserName() {
		return userName;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
