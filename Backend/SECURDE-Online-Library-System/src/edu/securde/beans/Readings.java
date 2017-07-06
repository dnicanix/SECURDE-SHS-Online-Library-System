package edu.securde.beans;

public class Readings {

	public static final String TABLE_NAME = "readings";
	public static final String COLUMN_READINGID = "readingid";
	public static final String COLUMN_READINGTITLE = "readingtitle";
	public static final String COLUMN_CATEGORYID = "categoryid";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_PUBLISHER = "publisher";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_TAGS = "tags";
	public static final String COLUMN_STATUS = "status";
	
	private int readingid;
	private String readingtitle;
	private int categoryid;
	private String location;
	private String author;
	private String publisher;
	private String year;
	private String tags;
	private String status;
	
	public Readings(){
		
	}

	public Readings(String readingtitle, int categoryid, String location, String author,
			String publisher, String year, String tags, String status) {
		super();
		this.readingtitle = readingtitle;
		this.categoryid = categoryid;
		this.location = location;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.tags = tags;
		this.status = status;
	}

	public int getReadingid() {
		return readingid;
	}

	public void setReadingid(int readingid) {
		this.readingid = readingid;
	}

	public String getReadingtitle() {
		return readingtitle;
	}

	public void setReadingtitle(String readingtitle) {
		this.readingtitle = readingtitle;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
