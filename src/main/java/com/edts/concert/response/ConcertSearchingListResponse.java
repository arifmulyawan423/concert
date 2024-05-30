package com.edts.concert.response;

import java.util.List;

public class ConcertSearchingListResponse {
	List<ConcertSearchingResponse> listConcert;

	public List<ConcertSearchingResponse> getListConcert() {
		return listConcert;
	}

	public void setListConcert(List<ConcertSearchingResponse> listConcert) {
		this.listConcert = listConcert;
	}
}
