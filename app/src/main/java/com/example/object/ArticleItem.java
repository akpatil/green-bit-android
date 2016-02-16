package com.example.object;

public class ArticleItem {

	private String mTitle,
				   mContent,
				   mCreated;
	
	public ArticleItem(){}
	
	public ArticleItem(String title, String content, String created){
		this.mTitle = title;
		this.mContent = content;
		this.mCreated = created;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String mContent) {
		this.mContent = mContent;
	}

	public String getCreated() {
		return mCreated;
	}

	public void setCreated(String mCreated) {
		this.mCreated = mCreated;
	}
	
}
