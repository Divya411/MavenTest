package com.slokam.healthcare.servie.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.Option;
import com.slokam.healthcare.IPatservice.IPatientService;
import com.slokam.healthcare.PatientRepo.IPatientRepo;
import com.slokam.healthcare.PatientRepo.PatientCriteriaRepo;
import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
@Service
public class IPatientServiceImpl implements IPatientService{
    @Autowired
	private IPatientRepo patientRepo;
    @Autowired
    private PatientCriteriaRepo criteriarep;


	@Override
	public void patientRegistration(Patient patient) {
		
		
		patient.setDate(new Date());
		patientRepo.save(patient);	
	}
	@Override
	public List<Patient> criteriaTest(String name, String emial) {
		
		return criteriarep.findBooksByAuthorNameAndTitle(name, emial);
	}
	@Override
	public List<Patient> Patientsearch(PatientSearchPojo serchpojo) {
		
		return criteriarep.patientFullSearch(serchpojo);
	}
	@Override
	public List<Patient> getAllPatients() {
	List<Patient> list =patientRepo.findAll();
		return list;
	}
	@Override
	public Patient getById(Integer id) {
		
		Optional<Patient> pat = patientRepo.findById(id);
		if(pat.isPresent())
		{
		return	pat.get();
		}
		return null;
	}

}
