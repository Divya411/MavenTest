package com.slokam.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.IPatservice.IVistingService;
import com.slokam.healthcare.PatientRepo.IVistingReposi;

@RestController
@RequestMapping("visting")
public class VistingContoller {
	
	@Autowired
	private IVistingService vistigservice;
	
	@GetMapping("/byid/{id}")
	public ResponseEntity<List<Object[]>> getVistingDetails(@PathVariable Integer id)
	{
	return  ResponseEntity.ok(vistigservice.getVisitingDetails(id));
		
	}

}
