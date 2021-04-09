package com.slokam.healthcare.servie.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.IPatservice.IVistingService;
import com.slokam.healthcare.PatientRepo.IVistingReposi;

@Service
public class IvistingImpl implements IVistingService{
    @Autowired
	private IVistingReposi vistingrepo;
	
	@Override
	public List<Object[]> getVisitingDetails(Integer id) {
	return 	vistingrepo.getVisitingDetails(id);
	}

}
