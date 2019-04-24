package com.digivox.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Rent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@NotNull
	private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	private LocalDate startDate;

	private LocalDate devolutionDate;

	@NotNull
	private BigDecimal price;
	
	@NotNull
	private boolean returned = false;

	public Rent () {

	}

	public Rent(@NotNull Client client, LocalDate startDate, LocalDate devolutionDate,
			@NotNull BigDecimal price) {
		super();
		this.client = client;
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.price = price;
	}

	public Rent(long id, @NotNull Client client, LocalDate startDate, LocalDate devolutionDate,
			@NotNull BigDecimal price) {
		super();
		this.id = id;
		this.client = client;
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.price = price;
	}

	
	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean isReturned) {
		this.returned = isReturned;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
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
