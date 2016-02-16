package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VolleyPostService extends Fragment {

	private JsonObjectRequest mJsonObjectRequest;
	private Context mContext;
	private String mUrl;
	private JSONObject mJsonObject;
	private VolleyPostResponseListener mPostResponseListener;
	private RequestQueue mRequestQueue;
	
	public VolleyPostService(){}
	
	public VolleyPostService(Context context, String url, JSONObject object){
		this.mContext = context;
		this.mUrl = url;
		this.mJsonObject = object;
		System.out.println("Response from POST service: "+object);
	}
	
	public interface VolleyPostResponseListener{
		public void onPostResponse(JSONObject response);
	}
	
	private void initialization(){
		if(mContext instanceof Activity){
			mPostResponseListener = (VolleyPostResponseListener) mContext;
		}
		else{
			mPostResponseListener = (VolleyPostResponseListener) getTargetFragment();
		}
		mRequestQueue = Volley.newRequestQueue(mContext);
	}
	
	public void fetchPostData(){
		initialization();
		System.out.println("URL: "+mUrl);
		System.out.println("JSONObject: "+mJsonObject);
		mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, mUrl, mJsonObject, 
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						System.out.println("Response: "+response.toString());
						mPostResponseListener.onPostResponse(response);
					}
				}, 
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("Volley", "error");
						System.out.println("Error: "+error);
						try{
							mJsonObject = new JSONObject();
							mJsonObject.put("success", false);
							mPostResponseListener.onPostResponse(mJsonObject);
						}
						catch(JSONException e){
							e.printStackTrace();
						}
					}
				})
		{
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> params = new HashMap<String, String>();
				params.put("Content-Type", "application/json");
				return params;
			}
			
			@Override
	        public String getBodyContentType() {
	            return "application/json";
	        }
		};
		mRequestQueue.add(mJsonObjectRequest);
	}
}
