package com.edts.concert.request;

public class ConcertBookingRequest {
	String concertId;
	String email;
	
	public String getConcertId() {
		return concertId;
	}
	public void setConcertId(String concertId) {
		this.concertId = concertId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
