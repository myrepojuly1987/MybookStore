package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookstore.apiutils.ApiUtils;
import com.bookstore.beans.Book;
import com.bookstore.repository.BookRepository;

/**
 * Class that adds the book details like author, price and availability to the database
 *
 */
@Component
@Service
public final class AddBookService {
	private AddBookService() {}
	//dependency injection
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ApiUtils apitUtils;
	
	
	/**
	 * This method adds the books to the DB. If the book and author are already available then it will increment the count of the number of books
	 * If book or the author is not found then it will create a new entry in the DB
	 * @param List<Books>
	 * @return List<Books>
	 * 
	 * Make get Call to <http://localhost:8080/addBooks> with request body as List as shwon below 
	 *      [
			    {
			        "authorName": "Andrew",
			        "bookName": "Ruby",
			        "price": 10.0
			    },
			    {
			        "authorName": "John",
			        "bookName": "Java",
			        "price": 20.0
			    }
			]
	 */
	public List<Book> addBooks(List<Book> books) {
		//for each books
		books.forEach((book) -> {
			//check if already available.
			boolean bookAvailability= apitUtils.checkAvailability(book.getAuthorName(), book.getBookName());
			if(bookAvailability) { //just increase the count of the book and author in DB
				int rowId = apitUtils.getBookAndAuthorRowID(book.getAuthorName(),  book.getBookName());
				int availableCount = apitUtils.getBookAndAuthorCount(book.getAuthorName(),  book.getBookName());
				
				book.setId(rowId);
				book.setBookAvailabilty(availableCount+1);
				
			}else { //create a new entry
				book.setId(apitUtils.getMaxID());
				book.setBookAvailabilty(1);
				
			}
			bookRepository.save(book); //save
		});
		return bookRepository.findAll();
	}

}
