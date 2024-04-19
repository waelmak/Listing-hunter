package com.wael.mak.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.wael.mak.Models.Listing;
import com.wael.mak.Models.Note;
import com.wael.mak.Models.User;
import com.wael.mak.Services.ListingService;
import com.wael.mak.Services.NoteService;
import com.wael.mak.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ListingController {

	// Add once service is implemented:
	@Autowired
	private UserService userService;
	@Autowired
	private ListingService listingService;
	@Autowired
	private NoteService noteService;

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		model.addAttribute("listings", listingService.allListings());
		model.addAttribute("user", userService.findById((Long) session.getAttribute("userId")));
		model.addAttribute("notes", noteService.allNotes());
//		int[] notesPerListing = new int[] {};
//		int oc = 0;
//		for (Note note : noteService.allNotes()) {
//			for (Listing listing : listingService.allListings()) {
//				if (note.getListing().getId().equals(listing.getId())) {
//					oc++;
//				}
//			}
//		}
		return "home.jsp";
	}

	@GetMapping("/listings/new")
	public String addListing(@ModelAttribute("listing") Listing listing, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = userService.findById((Long) session.getAttribute("userId"));
		model.addAttribute("user", user);
		return "addListing.jsp";
	}

	@PostMapping("/listings")
	public String createListing(@Valid @ModelAttribute("listing") Listing listing, BindingResult result) {
		if (result.hasErrors()) {
			return "addListing.jsp";
		}

		listingService.createListing(listing);
		return "redirect:/home";
	}

	@GetMapping("/listings/{id}")
	public String listingPage(Model model, @PathVariable("id") Long id, @ModelAttribute("note") Note note,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Listing listing = listingService.findListingById(id);
		model.addAttribute("listing", listing);
		model.addAttribute("user", userService.findById((Long) session.getAttribute("userId")));

		List<Note> notesByListing = new ArrayList<>();
		for (Note note_listing : noteService.allNotes()) {
			if (note_listing.getListing().getId().equals(id)) {
				notesByListing.add(note_listing);
			}
		}
		model.addAttribute("notes", notesByListing);
		return "listing.jsp";

	}

	// Edit Display Route
	@GetMapping("/listings/{id}/edit")
	public String editListing(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		Listing listing = listingService.findListingById(id);
		if (listing.getUser().getId().equals(userId)) {
			model.addAttribute("listing", listing);
		}
		return "editListing.jsp";
	}

	// Edit Action Route
	@PutMapping("/update/{id}")
	public String updateListing(@Valid @ModelAttribute("listing") Listing listing, BindingResult result, Model model,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "editListing.jsp";
		}

		if (listing.getUser().getId().equals(userId)) {
			listingService.updateListing(listing);
		}
		return "redirect:/home";

	}

	// Delete Listing Route
	@GetMapping("/listings/{id}/delete")
	public String deleteListing(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		listingService.deleteListing(id);
		return "redirect:/home";
	}

}