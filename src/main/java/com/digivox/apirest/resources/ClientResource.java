package com.digivox.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivox.apirest.models.Client;
import com.digivox.apirest.repository.ClientRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientResource {

	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public List<Client> clients(){
		return clientRepository.findAll();
	}
	
	@GetMapping("/client/{id}")
	public Client client(@PathVariable(value="id") long id) {
		return clientRepository.findById(id);
	}
	
	@PostMapping("/client")
	public Client save(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@DeleteMapping("/client/{id}")
	public void delete(@PathVariable(value="id") long id) {
		Client client = clientRepository.findById(id);
		clientRepository.delete(client);
	}	
	
	@PutMapping("/client")
	public Client update(@RequestBody Client client) {
		return clientRepository.save(client);
	}
}
