package com.example.greenbit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CardActivity extends Activity {

	private TextView mTextViewTitle, mTextViewContent, mTextViewCreated;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		initialization();
	}
	
	private void initialization(){
		mTextViewTitle = (TextView) findViewById(R.id.textViewTitle);
		mTextViewContent = (TextView) findViewById(R.id.textViewContent);
		mTextViewCreated = (TextView) findViewById(R.id.textViewCreated);
	}
	
	private void PopulateData(){
		Bundle bundle = getIntent().getExtras();
	}
	
}
