package com.rdfgroup.cms.controller.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rdfgroup.cms.services.domain.Book;
import com.rdfgroup.cms.services.service.BookService;

@Controller
@RequestMapping("/rest")
@Secured("ROLE_User")
public class BookRestController extends BaseRestController{

	@Autowired
	private BookService bookService;

	@RequestMapping(value="/books", method = RequestMethod.PUT)
	public @ResponseBody Book updateBook(@RequestBody Book book) throws UnsupportedEncodingException, MessagingException, IOException, Exception  {
		return bookService.updateBook(book);
	}
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody  List<Book> getBooks() throws UnsupportedEncodingException, MessagingException, IOException, Exception  {
		return bookService.getBooks();
	}

	
	@RequestMapping(value="/books/author/{author}", method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooksByAuthor(@PathVariable String author)  {
		return bookService.getBookByAuthors(author);
	}
	
	@RequestMapping(value="/books/title/{title}", method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooksByTitle(@PathVariable String title)  {
		return bookService.getBookByTitle(title);
	}

	@RequestMapping(value="/book", method = RequestMethod.POST)
	public @ResponseBody Book createBook(@RequestBody Book book) throws Exception  {
		return bookService.createBook(book);
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Book getBook(@PathVariable String id)  {
		Book book = bookService.getBook(id);
		return book;
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Boolean deleteBook(@PathVariable String id)  {
		bookService.deleteBook(id);
		return Boolean.TRUE;
	}
}
