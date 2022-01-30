package com.genzon.App.services;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.genzon.App.model.UserModel;
import com.genzon.App.model.dtos.UserCredentialsDTO;
import com.genzon.App.model.dtos.UserLoginDTO;
import com.genzon.App.model.dtos.UserRegisterDTO;
import com.genzon.App.repository.UserRepository;

@Service
public class UserServices {

	private @Autowired UserRepository repository;
	
	
	private static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(password);
	}
	
	private static String generatorToken (String email, String password) {
		String structure = email + ":" + password;
		byte[] structure64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return new String(structure64);
	}
	
	private static String generatorTokenBasic (String email, String password) {
		String structure = email + ":" + password;
		byte[] structure64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structure64);
	}
	
	public ResponseEntity<UserModel> registerUser(@Valid UserRegisterDTO newUser){
		Optional<UserModel> optional = repository.findByEmail(newUser.getEmail());
		
		if(optional.isEmpty()) {
			UserModel user = new UserModel();
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPassword(encryptPassword(newUser.getPassword()));
			user.setDocument(newUser.getDocument());
			user.setToken(generatorTokenBasic(newUser.getEmail(), newUser.getPassword()));
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
		}
	}
	
	
	public ResponseEntity<UserCredentialsDTO> login (@Valid UserLoginDTO user){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<UserModel> optional = repository.findByEmail(user.getEmail());
		
		
		
		if(optional.isPresent()) {
			if(encoder.matches(user.getPassword(), optional.get().getPassword())) {
				UserCredentialsDTO userLogin = new UserCredentialsDTO();
				userLogin.setId(optional.get().getId());;
				userLogin.setEmail(optional.get().getEmail());
				userLogin.setTokenBasic(generatorTokenBasic(user.getEmail(), user.getPassword()));
				userLogin.setToken(generatorToken(user.getEmail(), user.getPassword()));
				
				return ResponseEntity.ok(userLogin);
			}
			
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password incorret");
			}
		
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email incorret");
		}
		
		
		
		
		
	}
	
	
}
