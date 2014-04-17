package com.rdfgroup.cms.controller.jsp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rdfgroup.cms.services.domain.Book;
import com.rdfgroup.cms.services.service.BookService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes
public class BookJspMvcController extends BaseJspController{

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/mvc/book/{id}", method = RequestMethod.GET)
	public String getBook(ModelMap model, @PathVariable String id)	throws Exception {
		Book book = bookService.getBook(id);
		model.addAttribute("book", book);
		return velocityWriter.write("books:book", model);
	}

	@RequestMapping(value = "/mvc/book", method = RequestMethod.GET)
	public String createBook(ModelMap model)	throws Exception {
		model.addAttribute("book", new Book());
		return "mvc/book";
	}

	@RequestMapping(value="mvc/book/{id}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("command") Book book,	BindingResult result) {
		bookService.updateBook(book);
		return "redirect:/mvc/books";
	}

	@RequestMapping(value="mvc/book", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("bookmvc") Book book,	BindingResult result) {
		bookService.createBook(book);
		return "redirect:/mvc/books";
	}

	@RequestMapping(value = "/mvc/books", method = RequestMethod.GET)
	public String getbooks(ModelMap model) throws Exception {
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return velocityWriter.write("books:books", model);
	}

}
