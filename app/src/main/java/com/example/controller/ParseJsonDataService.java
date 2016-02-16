package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.object.ArticleItem;

public class ParseJsonDataService {

	private JSONObject mObject;
	private JSONArray mArray;
	
	public ParseJsonDataService(){}
	
	public ArrayList<ArticleItem> ParseJsonArticle(JSONArray data){
		ArrayList<ArticleItem> mList = new ArrayList<ArticleItem>();
		for(int i=0; i<data.length(); i++){
			mObject = data.optJSONObject(i);
			try{
				mList.add(new ArticleItem(mObject.getString("title"), mObject.getString("content"), mObject.getString("created")));
			}
			catch(JSONException e){
				e.printStackTrace();
			}
		}
		return mList;
	}
}
