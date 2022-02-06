package com.bookstore.apiutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookstore.beans.Book;
import com.bookstore.repository.BookRepository;

@Component
@Service
public final class ApiUtils {
	private ApiUtils() {};
	
	@Autowired
	private BookRepository bookRepository;
	

	public int getMaxID() {
		return bookRepository.findAll().size()+1;
	}
	
	public boolean checkAvailability(String authorName, String bookName) {
		boolean bookAvailability = false;
		for (Book book : bookRepository.findAll()) {
			if(book.getAuthorName().equalsIgnoreCase(authorName.toLowerCase()) &&  book.getBookName().equalsIgnoreCase(bookName)) {
				bookAvailability = true;
			}
		}
		return bookAvailability;
	}
	
	public  int getBookAndAuthorRowID(String authorName, String bookName) {
		int bookRowId = 0;
		
		for (Book book : bookRepository.findAll()) {
			if(book.getAuthorName().equalsIgnoreCase(authorName) && book.getBookName().equalsIgnoreCase(bookName)) {
				bookRowId = book.getId();
			}
		}
		if (bookRowId == 0) {
			System.out.println("Book Row Not found"); //to do - check with Chandra on how to handle these. Throw CustomerExceptions?
		}
		return bookRowId;
	}
	
	public int getBookAndAuthorCount(String authorName, String bookName) {
		int totalBookCount = 0;
		for (Book book : bookRepository.findAll()) {
			if(book.getAuthorName().equalsIgnoreCase(authorName) && book.getBookName().equalsIgnoreCase(bookName)) {
				totalBookCount = book.getBookAvailabilty();
			}
		}
		if (totalBookCount == 0) {
			System.out.println("Book Not found"); //to do - check with Chandra on how to handle these. Throw CustomerExceptions?
		}
		return totalBookCount;
	}

}
