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

import com.digivox.apirest.dto.BookingDTO;
import com.digivox.apirest.models.Booking;
import com.digivox.apirest.services.BookingService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:3000")

public class BookingResource {

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/booking")
	public List<Booking> booking() {
		return bookingService.findAll();
	}

	@GetMapping("/booking/thisweek")
	public List<Booking> bookingThisWeek() {
		return bookingService.findAllThisWeek();
	}
	
	@GetMapping("/booking/{id}")
	public Booking booking(@PathVariable(value="id") long id) {
		return bookingService.findById(id);
	}
	
	@PostMapping("/booking")
	public String save(@RequestBody BookingDTO bookingDTO) {
		return bookingService.save(bookingDTO); 
	}
	
	@DeleteMapping("/booking/{id}")
	public void delete(@PathVariable(value="id") long id) {
		bookingService.delete(id);
	}
	
	@PutMapping("/booking/status/{id}")
	public void changeStatusBooking(@PathVariable(value="id") long id) {
		bookingService.changeStatusBooking(id);
	}
}
