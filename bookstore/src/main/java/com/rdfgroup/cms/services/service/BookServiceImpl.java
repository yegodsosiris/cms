package com.rdfgroup.cms.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfgroup.cms.services.domain.Book;
import com.rdfgroup.cms.services.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	BookRepository bookRepository;
	
	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> getBookByAuthors(String author) {
		return bookRepository.getBookByAuthors(author);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.updateBook(book);
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.createBook(book);
	}

	@Override
	public Book getBook(String id) {
		return bookRepository.getBook(id);
	}

	@Override
	public void deleteBook(String id) {
		bookRepository.deleteBook(id);
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		return bookRepository.getBookByTitle(title);
	}

	@Override
	public  List<Book> getBooks() {
		return bookRepository.getBooks();
	}

}
