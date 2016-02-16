package com.example.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.adapter.AdapterRecyclerArticle;
import com.example.adapter.AdapterRecyclerArticle.RecyclerViewListener;
import com.example.controller.ParseJsonDataService;
import com.example.controller.VolleyGetArrayService;
import com.example.controller.VolleyGetArrayService.VolleyGetArrayResponseListener;
import com.example.greenbit.R;
import com.example.object.ArticleItem;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArticleListFragment extends Fragment implements VolleyGetArrayResponseListener {

	private String mUrl;
	private VolleyGetArrayService mVolleyGetService;
	private ParseJsonDataService mJsonService;
	private RecyclerView mRecyclerView;
	private AdapterRecyclerArticle mAdapter;
	private static ProgressDialog mProgressDialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_article_list, null);
		initialization(view);
		PopulateData(view);
		
		return view;
	}
	
	private void initialization(View view){
		mUrl = "http://10.0.2.2:3000/api/article";
		mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
	}

	private void PopulateData(View view){
		mVolleyGetService = new VolleyGetArrayService(getActivity(), mUrl);
		mVolleyGetService.setTargetFragment(ArticleListFragment.this, 1);
		ProgressDialog(getActivity());
		mVolleyGetService.fetchGetData();
	}
	
	private static void ProgressDialog(Context mContext){
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage("Please Wait...");
		mProgressDialog.show();
	}

	@Override
	public void onGetArrayResponse(JSONArray response) {
		// TODO Auto-generated method stub
		ArrayList<ArticleItem> mArticleList = new ArrayList<ArticleItem>();
		
		mJsonService = new ParseJsonDataService();
		mArticleList = mJsonService.ParseJsonArticle(response);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mAdapter = new AdapterRecyclerArticle(getActivity(), mArticleList);
		mRecyclerView.setAdapter(mAdapter);
		mProgressDialog.dismiss();
	}
}
