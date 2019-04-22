package com.digivox.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@NotNull
	private Client client;

	@OneToOne
	@NotNull
	private Book book;

	private LocalDate startDate;

	private LocalDate devolutionDate;

	@NotNull
	private BigDecimal price;
	
	@NotNull
	private boolean isReturned;

	public Rent () {

	}

	public Rent(@NotNull Client client, @NotNull Book book, LocalDate startDate, LocalDate devolutionDate,
			@NotNull BigDecimal price) {
		super();
		this.client = client;
		this.book = book;
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.price = price;
	}

	public Rent(long id, @NotNull Client client, @NotNull Book book, LocalDate startDate, LocalDate devolutionDate,
			@NotNull BigDecimal price) {
		super();
		this.id = id;
		this.client = client;
		this.book = book;
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(LocalDate devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

}
