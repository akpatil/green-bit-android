package com.example.adapter;

import java.util.ArrayList;

import com.example.greenbit.R;
import com.example.object.ArticleItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class AdapterRecyclerArticle extends RecyclerView.Adapter<ViewHolderArticle> {
	
	private Context mContext;
	private ArrayList<ArticleItem> mArrayListArticle;
	private OnClickListener cardListener;
	public RecyclerViewListener mListener;
	
	public AdapterRecyclerArticle(Context context, ArrayList<ArticleItem> list){
		this.mContext = context;
		this.mArrayListArticle = list;
		mListener = (RecyclerViewListener) mContext;
	}
	
	public interface RecyclerViewListener{
		public void getCardView(View v, ArrayList<ArticleItem> list, int pos);
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mArrayListArticle.size();
	}

	@Override
	public void onBindViewHolder(ViewHolderArticle view, int i) {
		// TODO Auto-generated method stub
		ArticleItem mItem = mArrayListArticle.get(i);
		view.mTextViewTitle.setText(mItem.getTitle());
		view.mTextViewContent.setText(mItem.getContent());
		view.mTextViewCreated.setText(mItem.getCreated());
		view.mLinearLayoutRow.setTag(view);
		view.mLinearLayoutRow.setOnClickListener(OnCardClickEvent(cardListener));
	}

	@Override
	public ViewHolderArticle onCreateViewHolder(ViewGroup viewGroup, int i) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_article_card_row, viewGroup, false);
		ViewHolderArticle mViewHolder = new ViewHolderArticle(view);
		return mViewHolder;
	}
	
	public OnClickListener OnCardClickEvent(OnClickListener listener){
		listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ViewHolderArticle mHolder = (ViewHolderArticle) v.getTag();
				int pos = mHolder.getLayoutPosition();
				ArticleItem mItem = mArrayListArticle.get(pos);
				String tempTitle = mItem.getTitle();
				String tempContent = mItem.getContent();
				String tempCreated = mItem.getCreated();
				Toast.makeText(mContext, tempTitle, Toast.LENGTH_LONG).show();
				System.out.println("View: "+v+"Pos: "+pos+"ArrayList: "+mArrayListArticle);
				mListener.getCardView(v, mArrayListArticle, pos);
			}
		};
		
		return listener;
	}
}
