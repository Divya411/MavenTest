package com.slokam.healthcare.controller;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.platform.engine.reporting.ReportEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.IPatservice.IPatientService;
import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;

@RestController
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	private IPatientService patientservie;
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient)
	{
		patientservie.patientRegistration(patient);
		return new ResponseEntity<Patient>(patient,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/criteriaTest/{name}/{email}")
	public ResponseEntity<List<Patient>> searchPatient(@PathVariable  String name,@PathVariable String email)
	{
		List<Patient> patient =patientservie.criteriaTest(name, email);
		
		return new ResponseEntity<List<Patient>>(patient,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<Patient>> searchALLPatients(@RequestBody PatientSearchPojo searchpojo)
	{
	List<Patient> patinet=	patientservie.Patientsearch(searchpojo);
	return ResponseEntity.ok(patinet);
	}
	
	@GetMapping("/allByFilter")
	public ResponseEntity<List<Patient>> getAllPatientDetailsFiletr()
	{
	List<Patient> list= patientservie.getAllPatients();
    List<Patient> list1= list.stream().filter(predi -> nullCheck(predi)).collect(Collectors.toList());
	
	return ResponseEntity.ok(list1);
	
	}
	
	public boolean nullCheck(Patient patient)
	{
		boolean result = false;
		if( Objects.nonNull(patient) && patient.getId()!=null && patient.getId()%2==0)
       {
	   result = true;
       }
		
		return false;
		
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<Patient> getPatietById( @PathVariable Integer id)
	{
	Patient list= patientservie.getById(id);
	return ResponseEntity.ok(list);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatientDetails()
	{
	List<Patient> list= patientservie.getAllPatients();
	return ResponseEntity.ok(list);
	
	}
	
	//------------byAge--------
	@GetMapping("/byAge")
	public ResponseEntity<List<Patient>> getByAge()
	{
	List<Patient> list=	patientservie.getAllPatients().stream().filter(predi -> Objects.nonNull(predi) && predi.getAge() %2==0).collect(Collectors.toList());	
	return ResponseEntity.ok(list);
	}
	
	//-----------getOnlyNames--------
	
	@GetMapping("/getOnlyNames")
	public ResponseEntity<List<String>> getOnlyNames()
	{
	List<String> patinet=	patientservie.getAllPatients().stream().map(fun -> fun.getName()).collect(Collectors.toList());
	return ResponseEntity.ok(patinet);
	
	}
	
	
	
	
	
	

}
