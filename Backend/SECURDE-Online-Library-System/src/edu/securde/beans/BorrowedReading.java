package edu.securde.beans;

public class BorrowedReading {

	public static final String TABLE_NAME ="borrowed_readings";
	public static final String COLUMN_BORROWEDREADINGID = "borrowedreadingid";
	public static final String COLUMN_READINGID = "readingid";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_DATERESERVED= "datereserved";
	public static final String COLUMN_DATEBORROWED = "dateborrowed";
	public static final String COLUMN_DUEDATE = "duedate";
	public static final String COLUMN_DATERETURNED = "datereturned";
	
	private int borrowedreadingid;
	private int readingid;
	private int userid;
	private String datereserved;
	private String dateborrowed;
	private String duedate;
	private String datereturned;
	
	public BorrowedReading(){
		
	}

	public BorrowedReading(int readingid, int userid) {
		super();
		this.readingid = readingid;
		this.userid = userid;
	}

	public BorrowedReading(int readingid, int userid, String datereserved, String dateborrowed, String duedate,
			String datereturned) {
		super();
		this.readingid = readingid;
		this.userid = userid;
		this.datereserved = datereserved;
		this.dateborrowed = dateborrowed;
		this.duedate = duedate;
		this.datereturned = datereturned;
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

	public String getDatereserved() {
		return datereserved;
	}

	public void setDatereserved(String datereserved) {
		this.datereserved = datereserved;
	}

	public String getDateborrowed() {
		return dateborrowed;
	}

	public void setDateborrowed(String dateborrowed) {
		this.dateborrowed = dateborrowed;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getDatereturned() {
		return datereturned;
	}

	public void setDatereturned(String datereturned) {
		this.datereturned = datereturned;
	}

}
