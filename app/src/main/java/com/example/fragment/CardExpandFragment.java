package com.example.fragment;

import com.example.greenbit.R;
import com.example.greenbit.UserActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardExpandFragment extends Fragment {

	private TextView mTextViewTitle,
			mTextViewContent,
			mTextViewCreated,
			mTextViewCreator;
	private String mTitle, mContent, mCreated, mCreator;
	private CollapsingToolbarLayout mCollapsingToolbar;
	private Toolbar mToolbar;

	public CardExpandFragment(String title, String content, String created, String creator){
		this.mTitle = title;
		this.mContent = content;
		this.mCreated = created;
		this.mCreator = creator;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_article_card_expand, null);
		initialization(view);
		CreateToolbar();
		PopulateData();
		return view;
	}

	private void initialization(View view){
		//mTextViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
		mTextViewContent = (TextView) view.findViewById(R.id.textViewContent);
		mTextViewCreated = (TextView) view.findViewById(R.id.textViewCreated);
		mTextViewCreator = (TextView) view.findViewById(R.id.textViewCreator);
		mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolbar);
		mToolbar = (Toolbar) view.findViewById(R.id.toolBar);
	}

	private void CreateToolbar(){
		//mCollapsingToolbar.setTitle("asdfgh");
		mToolbar.setTitle(mTitle);
		//((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
	}

	private void PopulateData(){
		//mTextViewTitle.setText(mTitle);
		mTextViewContent.setText(mContent);
		mTextViewCreated.setText(mCreated);
		mTextViewCreator.setText(mCreator);
	}
}
