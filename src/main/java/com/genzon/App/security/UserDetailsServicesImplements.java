package com.genzon.App.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genzon.App.model.UserModel;
import com.genzon.App.repository.UserRepository;

@Service
public class UserDetailsServicesImplements implements UserDetailsService {

	private @Autowired UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> user = repository.findByEmail(username);
		
		if(user.isPresent()) {
			return new UserDetailsImplements(user.get());
		}
		else {
			throw new UsernameNotFoundException("Email not found!");
		}
		
	}

}
