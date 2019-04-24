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

import com.digivox.apirest.dto.RentDTO;
import com.digivox.apirest.models.Rent;
import com.digivox.apirest.services.RentService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RentResource {
	
	@Autowired
	RentService rentService;
		
	@GetMapping("/rent")
	public List<Rent> getAll() {
		return rentService.findAll();
	}

	@GetMapping("/rent/thisweek")
	public List<Rent> getAllThisWeek() {
		return rentService.findAllThisWeek();
	}

	@GetMapping("/rent/thisweekdevolution")
	public List<Rent> getAllThisWeekDevolluition() {
		return rentService.findAllThisWeekDevolution();
	}
	
	@GetMapping("/rent/{id}")
	public Rent get(@PathVariable(value="id") long id) {
		return rentService.findById(id);
	}
	
	@PostMapping("/rent")
	public String save(@RequestBody RentDTO rentDTO) {
		return rentService.save(rentDTO); 
	}
	
	@DeleteMapping("/rent/{id}")
	public void delete(@PathVariable(value="id") long id) {
		rentService.delete(id);
	}
	
	@PutMapping("/rent")
	public String update(@RequestBody RentDTO rentDTO) {
		return rentService.save(rentDTO);
	}
	
	@PutMapping("/rent/status/{id}")
	public void changeStatusBooking(@PathVariable(value="id") long id) {
		rentService.changeStatusRent(id);
	}
	
}
