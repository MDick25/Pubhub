package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection

	@Override
	public List<Tag> getTagsByBook(Book book) {
		ArrayList<Tag> tags=new ArrayList<>();
		Tag tag;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				tag=new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag_name"));
				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}
	
	@Override
	public List<Tag> getTagsByBook(String isbn) {
		ArrayList<Tag> tags=new ArrayList<>();
		Tag tag;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				tag=new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag_name"));
				tags.add(tag);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}

	@Override
	public List<Book> getAllBooksWithTag(String tag) {
		ArrayList<Book> books=new ArrayList<>();
		Book book;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books INNER JOIN book_tags ON book_tags.isbn_13=books.isbn_13 where tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				book=new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				books.add(book);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
	}

	@Override
	public boolean addTag(String tag, Book book) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean addTag(String tag, String isbn) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tag);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTag(String tag, Book book) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE book_tags WHERE isbn_13=? and tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTag(String tag, String isbn) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE book_tags WHERE isbn_13=? and tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tag);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
