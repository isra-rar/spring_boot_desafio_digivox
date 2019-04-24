package com.digivox.apirest.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingDTO {

	private long id;
	
	@NotNull
	private long [] books;
	
	@NotNull
	private long client;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-mm-dd")
	@NotNull
	private String date;
	
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

	public String getDate() {
		return date;
	}

	public long[] getBooks() {
		return books;
	}

	public void setBooks(long[] books) {
		this.books = books;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
