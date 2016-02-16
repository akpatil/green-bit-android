package com.example.greenbit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends Activity {

	private SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mSharedPreferences = SplashActivity.this.getSharedPreferences("UserPrefs", Context.MODE_APPEND);
		String state = mSharedPreferences.getString("login", "null");
		System.out.println("Login State Value: " + state);
		
			if(state.equals("success")){
				System.out.println("Inside state = success");
				Intent intent = new Intent(SplashActivity.this, UserActivity.class);
				startActivity(intent);
			}
			else{
				System.out.println("Inside state = failure");
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
			}
	}
}
