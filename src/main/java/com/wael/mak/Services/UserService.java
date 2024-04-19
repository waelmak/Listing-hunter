package com.wael.mak.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.wael.mak.Models.LoginUser;
import com.wael.mak.Models.User;
import com.wael.mak.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// TO-DO: Write register and login methods!
	public User register(User newUser, BindingResult result) {

		// TO-DO - Reject values or register if no errors:

		// Reject if email is taken (present in database)
		Optional<User> userLookUp = userRepository.findByEmail(newUser.getEmail());
		if (userLookUp.isPresent()) {
			result.rejectValue("email", "Unique", "Account with this email already exists.");
		}

		// Reject if password doesn't match confirmation
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		}

		// Return null if result has errors
		if (result.hasErrors()) {
			return null;
		}

		// Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);

		newUser = userRepository.save(newUser);
		System.out.println("New user created with ID: " + newUser.getId());

		return newUser;
	}

	public User login(LoginUser newLogin, BindingResult result) {

		// TO-DO - Reject values:

		// Find user in the DB by email
		// Reject if NOT present
		Optional<User> userLookUp = userRepository.findByEmail(newLogin.getEmail());
		if (!userLookUp.isPresent()) {
			result.rejectValue("email", "MissingAccount", "No account found.");
			return null;
		}
		// User exists, retrieve user from DB
		User user = userLookUp.get();

		// Reject if BCrypt password match fails
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password!");
		}

		// Return null if result has errors
		if (result.hasErrors()) {
			return null;
		}
		// Otherwise, return the user object
		return user;
	}

	public User findByEmail(String email) {
		Optional<User> result = userRepository.findByEmail(email);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	public User findById(Long id) {
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}