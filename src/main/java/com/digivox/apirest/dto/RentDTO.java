package com.digivox.apirest.dto;

import javax.validation.constraints.NotNull;

public class RentDTO {
	
	private long id;
	
	@NotNull
	private long book;
	

	@NotNull
	private long client;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getBook() {
		return book;
	}

	public void setBook(long book) {
		this.book = book;
	}

	public long getClient() {
		return client;
	}

	public void setClient(long client) {
		this.client = client;
	}
	
}
