package com.digivox.apirest.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.digivox.apirest.models.Book;
import com.digivox.apirest.models.Booking;
import com.digivox.apirest.models.Client;

public interface BookingRepsitory extends JpaRepository<Booking, Long> {

	Booking findById(long id);

	boolean existsByDateAndBooksAndCancelled(LocalDate date, Book books, boolean cancelled);

	boolean existsByDateAndBooksAndClientAndCancelled(LocalDate date, Book books, Client client, boolean cancelled);
	
	List<Booking> findAllByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}	
