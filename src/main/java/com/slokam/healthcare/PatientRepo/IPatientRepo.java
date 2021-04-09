package com.slokam.healthcare.PatientRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.entity.Patient;

@RestController
public interface IPatientRepo extends JpaRepository<Patient, Integer> {

}
