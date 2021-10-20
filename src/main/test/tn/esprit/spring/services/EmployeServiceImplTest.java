package tn.esprit.spring.services;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService iemployeservice;
	
	@Autowired
	IEntrepriseService ientrepriseService;
	
	@Test
	public void testmettreAjourEmailByEmployeId()
	{
		  try {
			  Employe e=iemployeservice.mettreAjourEmailByEmployeId("mestiri@esprit.tn", 1);
				
				System.out.println("la nv adresse mail employe modifié "+e.getEmail());
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}

	@Test
	public void testajouterContrat()
	{
		Contrat c=new Contrat(new Date(), "CDI",55);
		try {
			int id=iemployeservice.ajouterContrat(c);
			System.out.println("id du nv employe  "+id);
	    } catch (Exception e){
	        assertNull(e);
	    }
		}

	@Test
	public void testaffecterContratAEmploye()
	{
		
		  try {
				boolean b=iemployeservice.affecterContratAEmploye(22, 1);
				System.out.println("affectation est  "+b);
		    } catch (Exception e){
		        assertNull(e);
		    }
	}
	
	@Test
	public void testdeleteContratById()
	{
		
		try {
			boolean b=iemployeservice.deleteContratById(12);
			
			System.out.println("etat est  "+b);
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}
	
}
