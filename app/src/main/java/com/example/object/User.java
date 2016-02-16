package com.example.object;

public class User {

	private String mFirstname,
				   mLastname;
	
	public User(String firstname, String lastname){
		this.mFirstname = firstname;
		this.mLastname = lastname;
	}

	public String getmFirstname() {
		return mFirstname;
	}

	public void setmFirstname(String mFirstname) {
		this.mFirstname = mFirstname;
	}

	public String getmLastname() {
		return mLastname;
	}

	public void setmLastname(String mLastname) {
		this.mLastname = mLastname;
	}
	
}
