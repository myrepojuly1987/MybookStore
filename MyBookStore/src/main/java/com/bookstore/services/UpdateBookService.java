package com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookstore.apiutils.ApiUtils;
import com.bookstore.beans.Book;
import com.bookstore.frameworkexception.RecordNotFoundException;
import com.bookstore.repository.BookRepository;


/**
 * Class that updates the book details like author, price and availability to the database
 *
 */
@Component
@Service
public final class UpdateBookService {
	private UpdateBookService() {}
	//dependency injection
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ApiUtils apitUtils;
	

	/**
	 * This method updates the books to the DB only if it is found in the DB. It does not increase the book availability count. Updates other details
	 * @param List<Books>
	 * @return List<Books>
	 * Make Put Call to <http://localhost:8080/updateBooks> with request body as List as shwon below 
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
	public List<Book> updateBooks(List<Book> books) throws RecordNotFoundException{
		books.forEach((book) -> { //for each  books
			int rowId = apitUtils.getBookAndAuthorRowID(book.getAuthorName(),  book.getBookName());
		
			if(rowId > 0) { 
				int availableCount = apitUtils.getBookAndAuthorCount(book.getAuthorName(),  book.getBookName());
				book.setId(rowId);
				book.setBookAvailabilty(availableCount);
				bookRepository.save(book);
				//
			}
			else {
				//check with Chandra on how to handle these
				// "ROW NOT FOUND: "+rowId;
			//	return bookRepository.findAll();
			}
			
		});
		return bookRepository.findAll();
	}
}
