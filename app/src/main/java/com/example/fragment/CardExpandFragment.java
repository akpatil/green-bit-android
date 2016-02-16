package com.example.fragment;

import com.example.greenbit.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardExpandFragment extends Fragment {

	private TextView mTextViewTitle, 
					mTextViewContent, 
					mTextViewCreated;
	private String mTitle, mContent, mCreated;
	
	public CardExpandFragment(String title, String content, String created){
		this.mTitle = title;
		this.mContent = content;
		this.mCreated = created;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_article_card_expand, null);
		initialization(view);
		PopulateData();
		return view;
	}

	private void initialization(View view){
		mTextViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
		mTextViewContent = (TextView) view.findViewById(R.id.textViewContent);
		mTextViewCreated = (TextView) view.findViewById(R.id.textViewCreated);
	}
	
	private void PopulateData(){
		mTextViewTitle.setText(mTitle);
		mTextViewContent.setText(mContent);
		mTextViewCreated.setText(mCreated);
	}
}
