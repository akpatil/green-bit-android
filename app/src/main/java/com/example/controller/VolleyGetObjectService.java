package com.example.controller;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

public class VolleyGetObjectService extends Fragment {
	
	private RequestQueue mRequestQueue;
	private JsonObjectRequest mJsonObjectRequest;
	private Context mContext;
	private String mUrl;
	private VolleyGetObjectResponseListener mGetResponseListener;
	
	public VolleyGetObjectService(){}
	
	public VolleyGetObjectService(Context context, String url){
		this.mContext = context;
		this.mUrl = url;
	}
	
	public interface VolleyGetObjectResponseListener{
		void onGetResponse(JSONObject response);
	}
	
	private void initialization(){
		mGetResponseListener = (VolleyGetObjectResponseListener) getTargetFragment();
		mRequestQueue = Volley.newRequestQueue(mContext);
	}
	
	public void fetchGetData(){
		initialization();
		System.out.println("URL: "+mUrl);
		mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrl, null, 
				new Response.Listener<JSONObject>() {
					
					@Override
					public void onResponse(JSONObject response) {
						System.out.println("Response: " + response.toString());
						mGetResponseListener.onGetResponse(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("Error: "+error);
						Log.e("volley", "error");
					}
				}
	 );
	mRequestQueue.add(mJsonObjectRequest);
	}
}
