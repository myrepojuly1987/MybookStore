package com.bookstore.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
	@Id //primary key
	@Column(name="id")
	public int id;
	
	@Column(name="AuthorName")
	public String authorName;
	
	@Column(name="BookName")
	public String bookName;
	
	@Column(name="Availability")
	public int bookAvailabilty;
	
	@Column(name="Price")
	public double price;

}
