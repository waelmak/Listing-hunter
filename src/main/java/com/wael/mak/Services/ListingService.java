package com.wael.mak.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wael.mak.Models.Listing;
import com.wael.mak.Repositories.ListingRepository;

@Service
public class ListingService {

	@Autowired
	private ListingRepository listingRepository;

	public List<Listing> allListings() {
		return listingRepository.findAll();
	}

	public Listing createListing(Listing listing) {
		return listingRepository.save(listing);
	}

	public Listing findListingById(Long id) {
		Optional<Listing> optionalListing = listingRepository.findById(id);
		return (optionalListing.isPresent()) ? optionalListing.get() : null;
	}

	// Update Listing
	public Listing updateListing(Listing l) {
		return listingRepository.save(l);
	}

	// Delete Listing
	public void deleteListing(Long id) {
		listingRepository.deleteById(id);
	}
}