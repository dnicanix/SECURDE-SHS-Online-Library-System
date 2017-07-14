package edu.securde.beans;

public class ReviewInfo {
	
	private int readingid;
	private int userid;
	private String readingtitle;
	private String userfullname;
	private String review;
	private String reviewdate;
	
	public ReviewInfo(){
		
	}

	public ReviewInfo(int readingid, int userid, String readingtitle, String userfullname, String review,
			String reviewdate) {
		super();
		this.readingid = readingid;
		this.userid = userid;
		this.readingtitle = readingtitle;
		this.userfullname = userfullname;
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

	public String getReadingtitle() {
		return readingtitle;
	}

	public void setReadingtitle(String readingtitle) {
		this.readingtitle = readingtitle;
	}

	public String getUserfullname() {
		return userfullname;
	}

	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
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
