package com.example.object;

public class ArticleItem {

	private String mTitle,
			mContent,
			mCreated,
			mCreator;

	public ArticleItem(){}

	public ArticleItem(String title, String content, String created, String creator){
		this.mTitle = title;
		this.mContent = content;
		this.mCreated = created;
		this.mCreator = creator;
	}

	public String getCreator() {
		return mCreator;
	}

	public void setCreator(String mCreator) {
		this.mCreator = mCreator;
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
