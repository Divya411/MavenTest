package com.slokam.healthcare.PatientRepo;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.Utility.StringUtils;
import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;

@RestController
public class PatientCriteriaRepo {
	private static final Class Patient = null;
	@Autowired
	private EntityManager em;
	
	public List<Patient> patientFullSearch(PatientSearchPojo searchpojo)
	{
	CriteriaBuilder cb=	em.getCriteriaBuilder();
	 CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
	 Root<Patient> root =cq.from(Patient.class);
	 
	 List<Predicate> predicateList = new ArrayList<>();
	 
	 
	 if( searchpojo != null)
	 {
	 if( StringUtils.blackCheck(searchpojo.getName()))
	 {
		 predicateList.add(cb.like(root.get("name"),searchpojo.getName()+ "%")); 
	 }
	 
	 if(searchpojo.getPhone() != null )
	 {
		 predicateList.add(cb.equal(root.get("phone"),searchpojo.getPhone()));
	 }
		 
	 if(searchpojo.getFromdate() != null)
	 {
		 predicateList.add(cb.greaterThanOrEqualTo(root.get("regDate"), searchpojo.getFromdate())) ;
	 }
	 
	 if(searchpojo.getTodate() != null)
	 {
		 predicateList.add(cb.lessThanOrEqualTo(root.get("regDate"), searchpojo.getTodate())); 
	 }
	 
	 if(searchpojo.getStartingAge() != null)
	 {
		 predicateList.add(cb.ge(root.get("age"), searchpojo.getStartingAge())) ;
	 }
	 
	 if(searchpojo.getEndingAge() != null)
	 {
		 predicateList.add(cb.le(root.get("age"), searchpojo.getEndingAge())); 
	 }}
	 
	 
	 
	 cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
	 TypedQuery<Patient> tq = em.createQuery(cq);
	 return tq.getResultList();
	}
	
	//--------------------------------------------------------
	
	public List<Patient> findBooksByAuthorNameAndTitle(String name,String email)
	{
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
	List<Predicate> predicates =new ArrayList<Predicate>();
     Root<Patient> root =	cq.from(Patient);	
     
     if(name !=null && name.trim().length()>0) {
     	Predicate p = cb.equal(root.get("name"), name);
     	predicates.add(p);
     }
     if(email !=null && email.trim().length()>0) {
     	Predicate p = cb.like(root.get("email"), "%" + email + "%");
     	predicates.add(p);
     }
     	 cq.where(predicates.toArray(new Predicate[predicates.size()]));

	        TypedQuery<Patient> query = em.createQuery(cq);
	        return query.getResultList();
     
	}
	

}
