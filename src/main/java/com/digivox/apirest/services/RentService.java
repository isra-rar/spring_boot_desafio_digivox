package com.digivox.apirest.services;

import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digivox.apirest.dto.RentDTO;
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
//		LocalDate startDate = new LocalDate();
//		LocalDate devolutionDate = startDate.plusDays(7);
//		Client client = clientRepository.findById(rentDTO.getClient());
//		Book book = bookRepository.findById(rentDTO.getBook());
//		BigDecimal price = new BigDecimal("10.99");
//		
//
//		Rent rent = new Rent(rentDTO.getId(), client, book, startDate, devolutionDate, price);
//		Verifica se o livro já está reservado pela pessoa que está alugando ou por outro cliente		
//		if (bookingRepository.existsByDateAndBookAndClientAndIsCancelled(startDate, book, client, false) || 
//				!bookingRepository.existsByDateAndBookAndIsCancelled(startDate, book, false)) {
//			if (rentDTO.getId() > 0) {				
//			}else {
//				rent = new Rent(client, book, startDate, devolutionDate, price);
//			}
//			rentRepository.save(rent);
//			return "Success";
//		} else {
//			return "Fail, Livro ja foi reservado nesta data por outra pessoa";
//		}
		return null;
		
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
