package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookstore.beans.Book;
import com.bookstore.repository.BookRepository;

@Component
@Service
public final class GetBooksService {
	private GetBooksService() {}
	
	
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * This method gets all the books from DB
	 * @param List<Books>
	 * @return List<Books>
	 * Example: Make get call as http://localhost:8080/getBooks
	 */
	public List<Book> getAllBooks(){
		return	bookRepository.findAll();
	}
	
	/**
	 * This method gets all the books based on the author name
	 * @param List<Books>
	 * @return List<Books>
	 * Example: Make get call as http://localhost:8080/getBooks/author?name=John
	 */
	public List<Book> getAllBooksByAuthor(String author){
		List<Book> bookList = new ArrayList<Book>();
		
		for (Book book : bookRepository.findAll()) {
			if(book.getAuthorName().equalsIgnoreCase(author)) {
				bookList.add(book);
			}
		}
		return bookList;
	}
	
	/**
	 * This method gets all the books based on the book name
	 * @param List<Books>
	 * @return List<Books>
	 *  Example: Make get call as http://localhost:8080/getBooks/bookName?book=Ruby
	 *    
	 */	
	public List<Book> getAllBooksByBookName(String bookName){
		List<Book> bookList = new ArrayList<Book>();
		for (Book book : bookRepository.findAll()) {
			if(book.getBookName().equalsIgnoreCase(bookName)) {
				bookList.add(book);
			}
		}
		return bookList;
	}
	
	/**
	 * This method gets all the books within the given price limit
	 * @param List<Books>
	 * @return List<Books>
	 * Example: Make get call as http://localhost:8080/getBooks/maxPrice?price=10
	 */
	public List<Book> getAllBooksByMaxPrice(double maxPrice){
		List<Book> bookList = new ArrayList<Book>();
		for (Book book : bookRepository.findAll()) {
			if(book.getPrice() <= maxPrice) {
				bookList.add(book);
			}
		}
		return bookList;
	}

}
