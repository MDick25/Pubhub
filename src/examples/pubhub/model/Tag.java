package examples.pubhub.model;

public class Tag {
	private String isbn13;			// International Standard Book Number, unique
	private String tag;
	
	public Tag(String isbn,String tag){
		this.tag = tag;
		this.isbn13 = isbn;
	}
	public Tag(){
		this.tag = null;
		this.isbn13 = null;
	}
	
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString(){
		return this.isbn13+" '"+this.tag+"'";
	}
}
