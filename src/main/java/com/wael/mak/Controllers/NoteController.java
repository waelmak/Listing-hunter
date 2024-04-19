package com.wael.mak.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wael.mak.Models.Note;
import com.wael.mak.Services.NoteService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping("/listings/{id}/addNote")
	public String addNote(@Valid @ModelAttribute("note") Note note, @PathVariable("id") Long id, Model model,
			HttpSession session, BindingResult result) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "redirect:/home";
		}
		noteService.createNote(note);
		return "redirect:/listings/{id}";
	}

	// Update Note route
	@PutMapping("/edit/{idNote}")
	public String editNote(Model model, @PathVariable("idNote") Long idNote, @Valid @ModelAttribute("note") Note note,
			BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "redirect:/home";
		}

		Note n = noteService.findNoteById(idNote);
		Long id = n.getListing().getId();
		if (n != null && n.getUser().getId().equals(userId)) {
			noteService.updateNote(n);
		}
		return "redirect:/listings/" + id;

	}

	// Delete Note Route
	@GetMapping("/delete")
	public String deleteNote(@RequestParam("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}

		Note n = noteService.findNoteById(id);
		Long idl = n.getListing().getId();
		if (n != null && n.getUser().getId().equals(userId)) {
			noteService.deleteNoteById(id);
		}
		return "redirect:/listings/" + idl;
	}
}
