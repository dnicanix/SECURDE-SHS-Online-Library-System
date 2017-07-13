package edu.securde.beans;

public class ReadingCategory {
	
	public static final String TABLE_NAME = "categories";
	public static final String COLUMN_CATEGORYID = "categoryid";
	public static final String COLUMN_CATEGORY = "category";
	
	private int categoryid;
	private String category;
	
	public ReadingCategory(){
		
	}

	public ReadingCategory(int categoryid, String category) {
		super();
		this.categoryid = categoryid;
		this.category = category;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
