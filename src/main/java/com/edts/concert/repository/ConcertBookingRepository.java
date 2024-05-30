package com.edts.concert.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edts.concert.entity.ConcertBooking;

public interface ConcertBookingRepository extends JpaRepository<ConcertBooking, UUID>{
	
}
