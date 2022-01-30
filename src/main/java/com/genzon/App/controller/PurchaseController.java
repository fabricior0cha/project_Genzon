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


import com.genzon.App.model.PurchaseModel;
import com.genzon.App.repository.PurchaseRepository;

@RestController
@RequestMapping("/api/v1/purchase")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PurchaseController {

	private @Autowired PurchaseRepository repository;
	
	@GetMapping
	public ResponseEntity<List<PurchaseModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseModel> getById(@Valid @PathVariable Long id){
		
		return repository.findById(id).map(resp -> ResponseEntity.ok().body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				});
		
	}
	
	@PostMapping
	public ResponseEntity<PurchaseModel> post (@Valid @RequestBody PurchaseModel purchase){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(purchase));
	}
	
	@PutMapping
	public ResponseEntity<PurchaseModel> put (@Valid @RequestBody PurchaseModel purchase){
		return repository.findById(purchase.getId()).map(resp -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(purchase)))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				});
	}
	
	@DeleteMapping("/{id}")
	public void delete(@Valid @PathVariable Long id) {
		repository.deleteById(id);
	}
}
	

