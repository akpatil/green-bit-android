package com.example.adapter;

import com.example.greenbit.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolderArticle extends RecyclerView.ViewHolder {

	protected TextView mTextViewTitle, mTextViewContent, mTextViewCreated;
	protected LinearLayout mLinearLayoutRow;
	
	public ViewHolderArticle(View view) {
		super(view);
		this.mTextViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
		this.mTextViewContent = (TextView) view.findViewById(R.id.textViewContent);
		this.mTextViewCreated = (TextView) view.findViewById(R.id.textViewCreated);
		this.mLinearLayoutRow = (LinearLayout) view.findViewById(R.id.linearLayoutRow);
	}

}
