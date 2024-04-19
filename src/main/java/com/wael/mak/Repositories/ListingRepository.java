package com.wael.mak.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wael.mak.Models.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Long> {
	List<Listing> findAll();
}