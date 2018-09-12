--DONOT run
--add tag
insert into book_tags values (
	'1111111111111',   --Book.getIsbn13() or isbn
	'male protagonist' --tag
);
--remove tag
delete from book_tags where tag_name='tag' and isbn_13=Book.getIsbn13();
delete from book_tags where tag_name='tag' and isbn_13=isbn;

--all tags for a book
select tag_name from book_tags where isbn_13=Book.getIsbn13();

--all books with tag
select * from books inner join book_tags on book_tags.isbn_13=books.isbn_13 where tag_name='tag';