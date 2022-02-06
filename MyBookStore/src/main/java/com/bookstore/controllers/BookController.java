package com.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.beans.Book;
import com.bookstore.responsehandler.ResponseHandler;
import com.bookstore.services.AddBookService;
import com.bookstore.services.GetBooksService;
import com.bookstore.services.UpdateBookService;


@RestController
public final class BookController {
	private BookController() {}
	@Autowired //Dependency injection
	private  AddBookService addBookService;
	
	@Autowired //Dependency injection
	private UpdateBookService updateBookService;
	
	@Autowired //Dependency injection
	private  GetBooksService getBooksService;
	
	@GetMapping("/getBooks")
	public ResponseEntity<Object> getAllBooks(){		
		List<Book> responseBody = getBooksService.getAllBooks();
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}
	
	//http://localhost:8080/getBooks/author?name=John
	@GetMapping("/getBooks/author")
	public ResponseEntity<Object> getAllBooksByAuthorName(@RequestParam(value = "name") String authorName){
		List<Book> responseBody  = getBooksService.getAllBooksByAuthor(authorName);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}
	
	//http://localhost:8080/getBooks/bookName?book=Ruby
	@GetMapping("/getBooks/bookName")
	public ResponseEntity<Object> getAllBooksByBookName(@RequestParam(value = "book") String bookName){
		List<Book> responseBody  =  getBooksService.getAllBooksByBookName(bookName);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}
	
	//http://localhost:8080/getBooks/maxPrice?price=10
	@GetMapping("/getBooks/maxPrice")
	public ResponseEntity<Object> getAllBooksByMaxPrice(@RequestParam(value = "price") double maxPrice){
		List<Book> responseBody  = getBooksService.getAllBooksByMaxPrice(maxPrice);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}
	
	//http://localhost:8080/addBooks
	@PostMapping("/addBooks")
	public ResponseEntity<Object> addBooks(@RequestBody List<Book> books){
		List<Book> responseBody  =  addBookService.addBooks(books);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}
	
	//http://localhost:8080/updateBooks
	@PutMapping("/updateBooks")
	public ResponseEntity<Object>updateBooks(@RequestBody List<Book> books){
		List<Book> responseBody  =  updateBookService.updateBooks(books);
		return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseBody); //check on how to handle exceptions
	}	
}
