package hu.bh.dzzt.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private String userName;
	private int role;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDTO(String userName, int role) {
		this.userName = userName;
		this.role = role;
	}

	public UserDTO() {
	}
	
	
}
