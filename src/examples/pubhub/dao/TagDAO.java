package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;

public interface TagDAO {

	public List<String> getTagsByBook(Book book);
	public List<Book> getAllBooksWithTag(String tag);
	
	public boolean addTag(String tag,Book book);
	public boolean addTag(String tag,String isbn);
	public boolean removeTag(String tag,Book book);
	public boolean removeTag(String tag,String isbn);
	
}
