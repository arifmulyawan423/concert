package com.edts.concert.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edts.concert.entity.Concert;
import com.edts.concert.entity.ConcertBooking;
import com.edts.concert.repository.ConcertBookingRepository;
import com.edts.concert.repository.ConcertRepository;
import com.edts.concert.request.ConcertBookingRequest;
import com.edts.concert.response.ConcertBookingResponse;
import com.edts.concert.response.ConcertSearchingListResponse;
import com.edts.concert.response.ConcertSearchingResponse;

@Service
public class ConcertService {

	@Autowired ConcertBookingRepository concertBookingRepository;
	@Autowired ConcertRepository concertRepository;
	
	public ResponseEntity<ConcertSearchingListResponse> searchConcert(String concertName) {
		ConcertSearchingListResponse response = new ConcertSearchingListResponse();
		List<ConcertSearchingResponse> listConcert = new ArrayList<ConcertSearchingResponse>();
		ResponseEntity<ConcertSearchingListResponse> result = ResponseEntity.ok(response);
		
		List<Concert> listConcertSearch = concertRepository.searchByNameAndAvailable("%" + concertName + "%");
		
		listConcertSearch.forEach((concert) -> {
			ConcertSearchingResponse concertSearch = new ConcertSearchingResponse();
			
			concertSearch.setConcertName(concert.getConcertName());
			concertSearch.setEndTime(concert.getEndTime().toLocalDateTime());
			concertSearch.setId(concert.getId().toString());
			concertSearch.setStartTime(concert.getStartTime().toLocalDateTime());
			concertSearch.setStatus(concert.getStatus());
			concertSearch.setStock(concert.getStock());
			
			listConcert.add(concertSearch);
		});
		
		response.setListConcert(listConcert);
		
		return result;
	}
	
	public synchronized ResponseEntity<ConcertBookingResponse> bookConcert(ConcertBookingRequest request) {
		ConcertBookingResponse response = new ConcertBookingResponse();
		ResponseEntity<ConcertBookingResponse> result = ResponseEntity.ok(response);
		
		Optional<Concert> optConcert = concertRepository.findById(UUID.fromString(request.getConcertId()));
		
		if (optConcert.isEmpty()) {
			return new ResponseEntity<ConcertBookingResponse>(HttpStatus.BAD_REQUEST);
		}
		
		if (optConcert.get().getStock() < 1 || !optConcert.get().getStatus().equalsIgnoreCase("available")) {
			return new ResponseEntity<ConcertBookingResponse>(HttpStatus.BAD_REQUEST);
		}
		
		//validate concert within specific hour
		if (optConcert.get().getStartTime().after(new Date()) || optConcert.get().getEndTime().before(new Date())) {
			return new ResponseEntity<ConcertBookingResponse>(HttpStatus.BAD_REQUEST);
		}
		
		ConcertBooking concertBooking = new ConcertBooking();
		
		UUID id = UUID.randomUUID();
		concertBooking.setConcertId(UUID.fromString(request.getConcertId()));
		concertBooking.setEmail(request.getEmail());
		concertBooking.setId(id);
		
		concertBookingRepository.save(concertBooking);
		concertRepository.updateConcertStock(UUID.fromString(request.getConcertId()));
		
		response.setConcertBookingId(id.toString());
		response.setConcertId(request.getConcertId());
		response.setConcertName(optConcert.get().getConcertName());
		response.setEmail(request.getEmail());
		response.setMessage("Sucessfully Booked");
		response.setStatus("OK");
		
		return result;
	}
}
