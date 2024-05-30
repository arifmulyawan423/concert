package com.edts.concert.response;

public class ConcertBookingResponse {
	String status;
	String message;
	String concertId;
	String concertName;
	String concertBookingId;
	String email;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getConcertId() {
		return concertId;
	}
	public void setConcertId(String concertId) {
		this.concertId = concertId;
	}
	public String getConcertName() {
		return concertName;
	}
	public void setConcertName(String concertName) {
		this.concertName = concertName;
	}
	public String getConcertBookingId() {
		return concertBookingId;
	}
	public void setConcertBookingId(String concertBookingId) {
		this.concertBookingId = concertBookingId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
