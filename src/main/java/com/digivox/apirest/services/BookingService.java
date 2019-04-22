package com.digivox.apirest.services;

import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digivox.apirest.dto.BookingDTO;
import com.digivox.apirest.models.Book;
import com.digivox.apirest.models.Booking;
import com.digivox.apirest.models.Client;
import com.digivox.apirest.repository.BookRepository;
import com.digivox.apirest.repository.BookingRepsitory;
import com.digivox.apirest.repository.ClientRepository;
import com.digivox.apirest.services.exceptions.ObjectNotFoundException;

@Service
public class BookingService {

	@Autowired
	BookingRepsitory bookingRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	BookRepository bookRepository;

	public List<Booking> findAll(){
		return bookingRepository.findAll();
	}

	public List<Booking> findAllThisWeek(){
		LocalDate now = new LocalDate();
		return bookingRepository.findAllByDateBetween(now.withDayOfWeek(DateTimeConstants.MONDAY),
				now.withDayOfWeek(DateTimeConstants.FRIDAY));
	}

	public Booking findById(long id) {
		return bookingRepository.findById(id);
	}

	public String save(BookingDTO bookingDTO) {
		DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-M-dd");
		Client client = clientRepository.findById(bookingDTO.getClient());
		LocalDate date = FORMATTER.parseLocalDate(bookingDTO.getDate());
		Book book = bookRepository.getOne(bookingDTO.getBook());
		if (!bookingRepository.existsByDateAndBookAndIsCancelled(date, book, false)) {			
			Booking booking = null;
			if (bookingDTO.getId() > 0) {				
				booking = new Booking(bookingDTO.getId(), client, book, date);	
			} else {				
				booking = new Booking(client, book, date, false);	
			}
			bookingRepository.save(booking);
			return "Success";
		}else {
			return "Fail, livro ja foi reservado nesta data";
		}
		
	}

	public void changeStatusBooking(long id) {
		Booking booking = bookingRepository.findById(id);
		booking.setCancelled(!booking.getisCancelled());
		bookingRepository.save(booking);
	}

	public void delete(long id) {
		try {
			Booking booking = bookingRepository.findById(id);
			bookingRepository.delete(booking);				
		} catch (Exception e) {
			throw new ObjectNotFoundException("ObjectNotFound!");
		}
	}
}
