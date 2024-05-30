package com.edts.concert.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "concert_bookings")
public class ConcertBooking {
	@Id
	@Column(name = "id")
	UUID id;
	
	@Column(name = "concert_id")
	UUID concertId;
	
	@Column(name = "created_at")
	@CreationTimestamp
	Timestamp createdAt;

	@Column(name = "email")
	String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getConcertId() {
		return concertId;
	}

	public void setConcertId(UUID concertId) {
		this.concertId = concertId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
