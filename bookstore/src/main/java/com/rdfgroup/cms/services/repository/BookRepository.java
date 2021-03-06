package com.rdfgroup.cms.services.repository;

import java.util.List;

import com.rdfgroup.cms.services.domain.Book;

public interface BookRepository {

	public List<Book> getBookByAuthors(String author);

	public Book updateBook(Book book);

	public Book createBook(Book book);

	public Book getBook(String id);

	public void deleteBook(String id);

	public List<Book> getBookByTitle(String title);

	public List<Book> getBooks();

}
