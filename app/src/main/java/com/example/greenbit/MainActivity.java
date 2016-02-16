package com.example.greenbit;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.controller.VolleyPostService;
import com.example.controller.VolleyPostService.VolleyPostResponseListener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements VolleyPostResponseListener {

	private EditText mEditTextUsername, mEditTextPassword;
	private Button mButtonLogin;
	private String mUsername, mPassword, mUrl;
	private VolleyPostService mVolleyPostService;
	private JSONObject mJsonObjectUserCredentials;
	private OnClickListener mLoginListener;
	private SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialization();
		mEditTextUsername.setText(mSharedPreferences.getString("username", null));
		mButtonLogin.setOnClickListener(OnClickLoginEvent(mLoginListener));
	}
	
	private void initialization(){
		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
		mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
		mButtonLogin = (Button) findViewById(R.id.buttonLogin);
		mSharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_APPEND);
	}
	
	private OnClickListener OnClickLoginEvent(OnClickListener listener){
		listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ValidateUser();
			}
		};
		
		return listener;
	}
	
	private void ValidateUser(){
		mUsername = mEditTextUsername.getText().toString().trim();
		mPassword = mEditTextPassword.getText().toString().trim();
		mUrl = "http://10.0.2.2:3000/signin-mobile";
		mVolleyPostService = new VolleyPostService(MainActivity.this, mUrl, ParseUserCredentials(mUsername, mPassword));
		mVolleyPostService.fetchPostData();
	}
	
	private JSONObject ParseUserCredentials(String username, String password) {
		try{
			mJsonObjectUserCredentials = new JSONObject();

			mJsonObjectUserCredentials.put("password", mPassword);
			mJsonObjectUserCredentials.put("username", mUsername);
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		return mJsonObjectUserCredentials;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPostResponse(JSONObject response) {
		// TODO Auto-generated method stub
		try{
			String pass = response.get("success").toString();
			if(pass.equals("true")){
				SharedPreferences.Editor mEditor = mSharedPreferences.edit();
				mEditor.putString("login", "success");
				mEditor.putString("username", mUsername);
				mEditor.commit();
				Intent mObjIntent = new Intent(MainActivity.this, UserActivity.class);
				startActivity(mObjIntent);
			}
			else{
				SharedPreferences.Editor mEditor = mSharedPreferences.edit();
				mEditor.putString("login", "failure");
				mEditor.commit();
				Toast.makeText(getApplicationContext(), "Authentication Failure! Try Again", Toast.LENGTH_LONG).show();
				mEditTextPassword.setText(null);
			}
		}
		catch(JSONException e){
			e.printStackTrace();
		}
	}
}
