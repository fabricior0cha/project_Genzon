package com.genzon.App.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.genzon.App.model.UserModel;
import com.genzon.App.model.dtos.UserCredentialsDTO;
import com.genzon.App.model.dtos.UserLoginDTO;
import com.genzon.App.model.dtos.UserRegisterDTO;
import com.genzon.App.repository.UserRepository;
import com.genzon.App.services.UserServices;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

	private @Autowired UserRepository repository;
	private @Autowired UserServices services;

	@GetMapping
	public ResponseEntity<List<UserModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getById(@Valid @PathVariable Long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok().body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});

	}

	@PostMapping("/signup")
	public ResponseEntity<UserModel> save(@Valid @RequestBody UserRegisterDTO newUser) {
		return services.registerUser(newUser);
	}

	@PutMapping("/login")
	public ResponseEntity<UserCredentialsDTO> getCredentials(@Valid @RequestBody UserLoginDTO user) {
		return services.login(user);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
