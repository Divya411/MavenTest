package com.slokam.healthcare.IPatservice;

import java.util.List;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;

public interface IPatientService {

	
	public void patientRegistration(Patient patient);
	public List<Patient> criteriaTest(String name,String email);
	public List<Patient> Patientsearch(PatientSearchPojo serchpojo);
	public List<Patient> getAllPatients();
	
	public Patient getById(Integer id);
	
	
}
