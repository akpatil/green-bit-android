package com.example.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

public class VolleyGetArrayService extends Fragment {

	private RequestQueue mRequestQueue;
	private JsonArrayRequest mJsonArrayRequest;
	private Context mContext;
	private String mUrl;
	private VolleyGetArrayResponseListener mGetArrayResponseListener;
	
	public VolleyGetArrayService(){}
	
	public VolleyGetArrayService(Context context, String url){
		this.mContext = context;
		this.mUrl = url;
	}
	
	public interface VolleyGetArrayResponseListener{
		void onGetArrayResponse(JSONArray response);
	}
	
	private void initialization(){
		mGetArrayResponseListener = (VolleyGetArrayResponseListener) getTargetFragment();
		mRequestQueue = Volley.newRequestQueue(mContext);
	}
	
	public void fetchGetData(){
		initialization();
		System.out.println("URL: "+mUrl);
		mJsonArrayRequest = new JsonArrayRequest(mUrl, 
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
						// TODO Auto-generated method stub
						System.out.println("Response: " + response);
						mGetArrayResponseListener.onGetArrayResponse(response);
					}
		}, 
		new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mRequestQueue.add(mJsonArrayRequest);
	}
}
