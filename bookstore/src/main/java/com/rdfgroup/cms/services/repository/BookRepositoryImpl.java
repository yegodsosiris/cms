package com.rdfgroup.cms.services.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.domain.Book;

@Repository
public class BookRepositoryImpl extends BaseRepository implements BookRepository {
	

	@Override
	public List<Book> getBookByAuthors(String author) {
		return find("author", author, Book.class);
	}


	@Override
	public List<Book> getBookByTitle(String title) {
		return find("title", title, Book.class);
	}

	@Override
	public Book updateBook(Book book) {
		return (Book) save(book);
	}

	@Override
	public Book createBook(Book book) {
		return (Book) insert(book);
	}

	@Override
	public Book getBook(String id) {
		return findById(id, Book.class);
	}

	@Override
	public void deleteBook(String id) {
		delete(id, Book.class);
	}


	@Override
	public List<Book> getBooks() {
		return findAll(Book.class);
	}


}
