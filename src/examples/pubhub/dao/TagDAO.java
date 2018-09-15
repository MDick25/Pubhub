package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {

	public List<Tag> getTagsByBook(Book book);
	public List<Tag> getTagsByBook(String isbn);
	public List<Book> getAllBooksWithTag(String tag);
	
	public boolean addTag(String tag,Book book);
	public boolean addTag(String tag,String isbn);
	public boolean removeTag(String tag,Book book);
	public boolean removeTag(String tag,String isbn);
	
}
