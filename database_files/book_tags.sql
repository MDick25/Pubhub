drop table if exists book_tags;

create table book_tags (
	isbn_13 varchar(13),
	tag_name varchar(50),
	primary key(isbn_13,tag_name)
);

insert into book_tags values (
	'1111111111111',   --isbn_13
	'male protagonist' --tag_name
);