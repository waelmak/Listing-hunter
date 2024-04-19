package com.wael.mak.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wael.mak.Models.LoginUser;
import com.wael.mak.Models.User;
import com.wael.mak.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	// Add once service is implemented:
	@Autowired
	private UserService users;

	@GetMapping("/")
	public String index(Model model) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {

		// Call a register method in the service
		// to do some extra validations and create a new user!
		users.register(newUser, result);

		if (result.hasErrors()) {
			// Be sure to send in the empty LoginUser before
			// re-rendering the page.
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		// Add once service is implemented:
		User user = users.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}

		// No errors!
		// Store their ID from the DB in session,
		// in other words, log them in.
		session.setAttribute("userId", user.getId());

		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			session.setAttribute("userId", null);
		}
		session.invalidate();
		return "redirect:/";
	}
}