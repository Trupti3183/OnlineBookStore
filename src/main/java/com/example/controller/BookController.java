package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Book;
import com.example.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/show")
	public String showBooksPage(Model model) {
		// Add logic to fetch all books from the database and add them to the model
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "user/book.html"; // Assuming your view name is "book.html"
	}
	@GetMapping("/update/{id}")
	public String showUpdateBookForm(@PathVariable Long id, Model model) {
	    // Retrieve the book by its ID
	    Book book = bookService.findById(id);
	    
	    // Check if the book exists
	    if (book == null) {
	        // Handle the case where the book is not found
	        // You can redirect to an error page or return an appropriate response
	        return "redirect:/error"; // Redirect to an error page
	    }
	    
	    // Add the book object to the model
	    model.addAttribute("book", book);
	    
	    // Return the name of the Thymeleaf template for the update form
	    return "user/updateBook.html";
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Book getBookById(@PathVariable Long id) {
		return bookService.findById(id);
	}

	@GetMapping("/view")
	public String getAllBooks(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "user/view.html";
	}

	@GetMapping("/add")
	public String showAddBookForm(Model model) {
		// Create a new Book object to bind to the form
		Book book = new Book();
		model.addAttribute("book", book);
		return "user/addBook.html"; // Assuming "addBookForm" is the name of your Thymeleaf template for the form
	}

	@PostMapping("/add")
	public String addBook(@ModelAttribute("book") Book book) {
		bookService.save(book);
		return "redirect:/books/show"; // Redirect to the page where you display all books
	}

	@PostMapping("/{id}")
	public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
	    book.setId(id);
	    bookService.update(book);
	    return "redirect:/books/show"; // Redirect to the page where you display all books
	}

	@GetMapping("/delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable Long id, Model model) {
        // Retrieve the book by its ID and add it to the model for display
        Book book = bookService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
            bookService.delete(book);
            return "redirect:/books/view"; // Assuming you have a Thymeleaf template for the confirmation page
        } else {
            // Handle the case where the book is not found
            return "redirect:/error"; // Redirect to an error page or handle it as needed
        }
    }

}
