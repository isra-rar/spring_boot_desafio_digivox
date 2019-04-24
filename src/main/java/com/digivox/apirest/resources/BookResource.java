package com.digivox.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivox.apirest.models.Book;
import com.digivox.apirest.repository.BookRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BookResource {

	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/books")
	public List<Book> books(){
		return bookRepository.findAll();
	}

	@GetMapping("/book/{id}")
	public Book book(@PathVariable(value="id") long id){
		return bookRepository.findById(id);
	}
	
	@PostMapping("/book")
	public Book save(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/book/{id}")
	public void delete(@PathVariable(value="id") long id) {
		Book book = bookRepository.findById(id);
		bookRepository.delete(book);
	}	

	@PutMapping("/book")
	public Book update(@RequestBody Book book) {
		return bookRepository.save(book);
	}	
}
