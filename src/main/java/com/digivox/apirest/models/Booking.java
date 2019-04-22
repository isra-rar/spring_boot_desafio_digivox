package com.digivox.apirest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

@Entity
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@NotNull
	private Client client;

    @OneToOne
	private Book book;

	@NotNull
    private LocalDate date;
    
    @NotNull
    private boolean isCancelled;

	public Booking() {
		
	}
	
	public Booking(Client client, Book book, LocalDate date, boolean isCanceled) {
		this.client = client;
		this.book = book;
		this.date = date;
		this.isCancelled = isCanceled;
	}
	
	public Booking(long id, Client client, Book book, LocalDate date) {
		this.id = id;
		this.client = client;
		this.book = book;
		this.date = date;
	}
	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean getisCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
