package com.digivox.apirest.dto;

import javax.validation.constraints.NotNull;

public class RentDTO {
	
	private long id;
	
	@NotNull
	private long [] books;

	@NotNull
	private long client;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getClient() {
		return client;
	}

	public void setClient(long client) {
		this.client = client;
	}

	public long[] getBooks() {
		return books;
	}
	
	public void setBooks(long[] books) {
		this.books = books;
	}
	
}
