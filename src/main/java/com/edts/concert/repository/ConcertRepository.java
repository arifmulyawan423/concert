package com.edts.concert.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edts.concert.entity.Concert;

import jakarta.transaction.Transactional;

public interface ConcertRepository extends JpaRepository<Concert, UUID>{
	@Transactional
	@Modifying
	@Query("update Concert c set c.stock = c.stock + 1 where c.id = :id and c.stock > 0")
	public void updateConcertStock(@Param("id") UUID id);
	
	@Query("select c from Concert c where c.concertName LIKE :name and c.stock > 0 and c.status = 'available'")
	public List<Concert> searchByNameAndAvailable(@Param("name") String name);
}
