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

import com.genzon.App.model.ProductModel;
import com.genzon.App.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProductController {

	private @Autowired ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ProductModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getById(@Valid @PathVariable Long id){
		
		return repository.findById(id).map(resp -> ResponseEntity.ok().body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				});
		
	}
	
	@GetMapping("/name/{product}")
	public ResponseEntity<List<ProductModel>> getByProduct(@Valid @PathVariable String product){
		return ResponseEntity.ok().body(repository.findAllByProductContainingIgnoreCase(product));
		
	}
	
	@PostMapping
	public ResponseEntity<ProductModel> post (@Valid @RequestBody ProductModel product){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
	}
	
	@PutMapping
	public ResponseEntity<ProductModel> put (@Valid @RequestBody ProductModel product){
		return repository.findById(product.getId()).map(resp -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(product)))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				});
	}
	
	@DeleteMapping("/{id}")
	public void delete(@Valid @PathVariable Long id) {
		repository.deleteById(id);
	}
}
