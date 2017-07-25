package edu.securde.beans;

public class ReviewInfo {
	
	private int readingid;
	private String readingtitle;
	private int userid;
	private String username;
	private String usertype;
	private String userfullname;
	private String review;
	private String reviewdate;
	
	public ReviewInfo(){
		
	}	

	public ReviewInfo(int readingid, String readingtitle, int userid, String username, String usertype,
			String userfullname, String review, String reviewdate) {
		super();
		this.readingid = readingid;
		this.readingtitle = readingtitle;
		this.userid = userid;
		this.username = username;
		this.usertype = usertype;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

 }
