package edu.securde.beans;

public class Review {

	public static final String TABLE_NAME = "reviews";
	public static final String COLUMN_READINGID = "readingid";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_REVIEW = "review";
	public static final String COLUMN_REVIEWDATE = "reviewdate";
	
	private int readingid;
	private int userid;
	private String review;
	private String reviewdate;
	
	public Review(){
		
	}
	
	public Review(int readingid, int userid, String review) {
		super();
		this.readingid = readingid;
		this.userid = userid;
		this.review = review;
	}

	public Review(int readingid, int userid, String review, String reviewdate) {
		super();
		this.readingid = readingid;
		this.userid = userid;
		this.review = review;
		this.reviewdate = reviewdate;
	}

	public int getReadingid() {
		return readingid;
	}

	public void setReadingid(int readingid) {
		this.readingid = readingid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
	
}
