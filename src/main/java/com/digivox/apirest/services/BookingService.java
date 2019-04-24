package com.digivox.apirest.services;

import java.util.ArrayList;
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
				now.withDayOfWeek(DateTimeConstants.SATURDAY));
	}

	public Booking findById(long id) {
		return bookingRepository.findById(id);
	}

	public String save(BookingDTO bookingDTO) {
		DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
		LocalDate date = FORMATTER.parseLocalDate(bookingDTO.getDate());
		Client client = clientRepository.findById(bookingDTO.getClient());

		if (bookingDTO.getId() == 0) {
			Booking booking = new Booking(client, date, false);

			for (long l : bookingDTO.getBooks()) {
				Book book = bookRepository.findById(l);
				if (!bookingRepository.existsByDateAndBooksAndCancelled(date, book, false)) {					
					booking.getBooks().add(book);
				}
			}

			if (booking.getBooks().size() > 0) {				
				bookingRepository.save(booking);
			}
		} else {
			Booking booking = bookingRepository.findById(bookingDTO.getId());
			List<Book> b = new ArrayList<>();
			booking.setBooks(b);

			for (long l : bookingDTO.getBooks()) {
				Book book = bookRepository.findById(l);
				if (!bookingRepository.existsByDateAndBooksAndCancelled(date, book, false) || 
						bookingRepository.existsByDateAndBooksAndClientAndCancelled(date, book, client, false)) {					
					booking.getBooks().add(book);
				}
			}

			booking.setClient(clientRepository.findById(bookingDTO.getClient()));
			booking.setDate(date);
			if (booking.getBooks().size() > 0) {				
				bookingRepository.save(booking);
			}
		}

		return "Success";
	}

	public void changeStatusBooking(long id) {
		Booking booking = bookingRepository.findById(id);
		booking.setCancelled(true);
		bookingRepository.save(booking);
	}

	public void delete(long id) {
		try {
			Booking booking = bookingRepository.findById(id);
			bookingRepository.delete(booking);				
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("ObjectNotFound!");
		}
	}
}
