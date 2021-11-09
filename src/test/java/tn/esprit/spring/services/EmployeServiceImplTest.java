package tn.esprit.spring.services;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService iemployeservice;

	@Autowired
	IEntrepriseService ientrepriseService;

	@Test
	public void testajouterEmploye() {

		Employe e = new Employe ("ooons", "Ons","ons@esprit.tn", true, Role.INGENIEUR); 
	
		int employeAdded = iemployeservice.ajouterEmploye(e); 
		Assert.assertEquals(e.getId(), employeAdded);
		
	}
	
	@Test
	public void testgetEmployeById() {
		Employe employeRetrieved = iemployeservice.getEmployeById("2"); 
		Assert.assertEquals(2, employeRetrieved.getId());
	}
	
	@Test
	public void testmettreAjourEmailByEmployeIdJPQL() {
		boolean employeUpdatedMail = iemployeservice.mettreAjourEmailByEmployeIdJPQL("emailupdated@gmail.com", 2) ;
		Assert.assertEquals(true, employeUpdatedMail);
	}
	
	@Test
	public void testdeleteEmployeById() {
		iemployeservice.deleteEmployeById(44);
		Assert.assertNull(iemployeservice.getEmployeById("44"));
	}
	
	@Test
	public void testgetAllEmployes() {

		List<Employe> listEmployes = iemployeservice.getAllEmployes(); 
		// if there are 2 users in DB : expected value 1
		Assert.assertEquals(1, listEmployes.size());
	}	
}
