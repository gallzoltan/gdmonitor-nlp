package hu.gallz.appservice.model;

public class PdfContent {
	private int pgnumber;
    private String content;
    private String decree;
    
	public PdfContent() {}
	
	public PdfContent(int pgnumber, String decree, String content) {
		this.pgnumber = pgnumber;
		this.decree = decree;
		this.content = content;
		
	}
	public int getPgnumber() {
		return pgnumber;
	}
	public void setPgnumber(int pgnumber) {
		this.pgnumber = pgnumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getDecree() {
		return decree;
	}

	public void setDecree(String decree) {
		this.decree = decree;
	}

	@Override
	public String toString() {
		return "PdfContent [pgnumber=" + pgnumber + ", decree=" + decree + "]";
	}
    
}
