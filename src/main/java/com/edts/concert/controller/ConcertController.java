package com.edts.concert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edts.concert.request.ConcertBookingRequest;
import com.edts.concert.response.ConcertBookingResponse;
import com.edts.concert.response.ConcertSearchingListResponse;
import com.edts.concert.service.ConcertService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Search and Booking Concert", description = "Search and Booking Concert APIs")
@RestController
@RequestMapping("concert")
public class ConcertController {

	@Autowired ConcertService concertService;
	
	@GetMapping("/search")
	public ResponseEntity<ConcertSearchingListResponse> search(@RequestParam("search_value") String searchValue) {
		return concertService.searchConcert(searchValue);
	}
	
	@PostMapping("/book")
	public ResponseEntity<ConcertBookingResponse> book(@RequestBody ConcertBookingRequest request) {
		return concertService.bookConcert(request);
	}
}
