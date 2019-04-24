package com.digivox.apirest.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digivox.apirest.dto.RentDTO;
import com.digivox.apirest.models.Book;
import com.digivox.apirest.models.Client;
import com.digivox.apirest.models.Rent;
import com.digivox.apirest.repository.BookRepository;
import com.digivox.apirest.repository.BookingRepsitory;
import com.digivox.apirest.repository.ClientRepository;
import com.digivox.apirest.repository.RentRepository;
import com.digivox.apirest.services.exceptions.ObjectNotFoundException;

@Service
public class RentService {

	@Autowired
	RentRepository rentRepository;

	@Autowired
	BookingRepsitory bookingRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	BookRepository bookRepository;

	public List<Rent> findAll(){
		return rentRepository.findAll();
	}

	public List<Rent> findAllThisWeek(){
		LocalDate now = new LocalDate();
		return rentRepository.findAllByStartDateBetween(
				now.withDayOfWeek(DateTimeConstants.MONDAY),
				now.withDayOfWeek(DateTimeConstants.SUNDAY));
	}

	public List<Rent> findAllThisWeekDevolution(){
		LocalDate now = new LocalDate();
		return rentRepository.findAllByDevolutionDateBetween(
				now.withDayOfWeek(DateTimeConstants.MONDAY),
				now.withDayOfWeek(DateTimeConstants.SUNDAY));
	}

	public Rent findById(long id) {
		return rentRepository.findById(id);
	}

	public String save(RentDTO rentDTO) {
		LocalDate startDate = new LocalDate();
		LocalDate devolutionDate = startDate.plusDays(7);

		Client client = clientRepository.findById(rentDTO.getClient());

		BigDecimal price = new BigDecimal("0");
		
		double priceBook = 5.99;

		if (rentDTO.getId() == 0) {
			Rent rent = new Rent(rentDTO.getId(), client, startDate, devolutionDate, price);

			for (long l : rentDTO.getBooks()) {
				Book book = bookRepository.findById(l);
				if (
						!bookingRepository.existsByDateAndBooksAndCancelled(startDate, book, false) &&
						!rentRepository.existsByBooksAndReturned(book, false)
					) {			
					rent.getBooks().add(book);
					rent.setPrice(BigDecimal.valueOf(rent.getPrice().doubleValue() + priceBook));
				}	
			}
			if (rent.getBooks().size() > 0) {
				rentRepository.save(rent);				
			} 

		}else {
			Rent rent = rentRepository.findById(rentDTO.getId());
			List<Book> b = new ArrayList<>();

			rent.setBooks(b);
			rent.setPrice(BigDecimal.valueOf(0));

			for (long l : rentDTO.getBooks()) {
				Book book = bookRepository.findById(l);
				if (
						!bookingRepository.existsByDateAndBooksAndCancelled(startDate, book, false) || 
						bookingRepository.existsByDateAndBooksAndClientAndCancelled(startDate, book, client, false) ||
						!rentRepository.existsByBooksAndReturned(book, false) || 
						rentRepository.existsByBooksAndClientAndReturned(book, client, false)

						) {
					rent.getBooks().add(book);
					rent.setPrice(BigDecimal.valueOf(rent.getPrice().doubleValue() + priceBook));
				}
			}

			rent.setClient(clientRepository.findById(rentDTO.getClient()));

			if (rent.getBooks().size() > 0) {
				rentRepository.save(rent);				
			}

		}

		return "Success";
	}

	public void changeStatusRent(long id) {
		Rent rent = rentRepository.findById(id);
		rent.setReturned(true);
		rentRepository.save(rent);
	}

	public void delete(long id) {
		try {
			Rent rent = rentRepository.findById(id);
			rentRepository.delete(rent);				
		} catch (Exception e) {
			throw new ObjectNotFoundException("ObjectNotFound!");
		}
	}

}
