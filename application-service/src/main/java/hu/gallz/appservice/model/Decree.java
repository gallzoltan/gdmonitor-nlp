package hu.gallz.appservice.model;

public class Decree {
	private int pageNumber;
    private String decreeNumber;
    private String decreeText;
    
	public Decree() {}

	public Decree(int pageNumber, String decreeNumber, String decreeText) {
		this.pageNumber = pageNumber;
		this.decreeNumber = decreeNumber;
		this.decreeText = decreeText;
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

	public String getDecreeText() {
		return decreeText;
	}

	public void setDecreeText(String decreeText) {
		this.decreeText = decreeText;
	}
    
}
