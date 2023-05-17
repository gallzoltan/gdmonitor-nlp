package hu.gallz.appservice.model;

public class PdfContent {
	private int pgnumber;
    private String content;
    
	public PdfContent() {}
	
	public PdfContent(int pgnumber, String content) {
		this.pgnumber = pgnumber;
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

	@Override
	public String toString() {
		return "PdfContent [pgnumber=" + pgnumber + "]";
	}
    
}
