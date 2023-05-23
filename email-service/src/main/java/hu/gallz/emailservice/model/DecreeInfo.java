package hu.gallz.emailservice.model;

public class DecreeInfo {
	private int pageNumber;
	private String decreeNumber;
		
	public DecreeInfo() {}
	
	public DecreeInfo(int pageNumber, String decreeNumber) {
		super();
		this.pageNumber = pageNumber;
		this.decreeNumber = decreeNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getDecreeNumber() {
		return decreeNumber;
	}
	public void setDecreeNumber(String decreeNumber) {
		this.decreeNumber = decreeNumber;
	}		
}
