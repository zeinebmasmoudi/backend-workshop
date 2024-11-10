package com.ensi.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.ensi.entities.Provider;
import com.ensi.repositories.ProviderRepository;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:4200", "http://localhost:4200"})

@RequestMapping({"/providers"})
public class ProviderRestController {
	@Autowired  // injection de dependances
	private ProviderRepository providerRepository;
	
	@GetMapping("/list")
    public List<Provider> getAllProvider() {
        return (List<Provider>) providerRepository.findAll();
    }
	
	@PostMapping("/add")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
    }
	
	@PutMapping("/{providerId}")
    public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest) {
        return providerRepository.findById(providerId).map(provider -> {
        	provider.setName(providerRequest.getName());
        	provider.setEmail(providerRequest.getEmail());
        	provider.setAddress(providerRequest.getAddress());
            return providerRepository.save(provider);
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + " not found"));
    }


    @DeleteMapping("/{providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long providerId) {
        return providerRepository.findById(providerId).map(provider -> {
        	providerRepository.delete(provider);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + " not found"));
    }

}
