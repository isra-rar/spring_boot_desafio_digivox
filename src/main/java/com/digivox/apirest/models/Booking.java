package com.digivox.apirest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

@Entity
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@OneToOne
	private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	@NotNull
    private LocalDate date;
    
    @NotNull
    private boolean cancelled;

	public Booking() {
		
	}

	public Booking(Client client, LocalDate date, boolean isCanceled) {
		this.client = client;
		this.date = date;
		this.cancelled = isCanceled;
	}
	
	public Booking(long id, Client client, LocalDate date) {
		this.id = id;
		this.client = client;
		this.date = date;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.cancelled = isCancelled;
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
