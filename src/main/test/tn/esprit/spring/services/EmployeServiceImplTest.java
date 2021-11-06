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
	
/*	@Test
	public void testmettreAjourEmailByEmployeId()
	{
		  try {
			  Employe e=iemployeservice.mettreAjourEmailByEmployeId("tttttttttt@es.tn", 1);
			  assertEquals(e.getEmail(), "tttttttttt@es.tn");
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}
*/
	@Test
	public void testajouterContrat()
	{
		Contrat c=new Contrat(new Date(), "CDI",55);
		try {
			int id=iemployeservice.ajouterContrat(c);
			//System.out.println("id du nv employe  "+id);
			assertEquals(c.getTypeContrat(), "CDI");
	    } catch (Exception e){
	        assertNull(e);
	    }
		}

	/*@Test
	public void testaffecterContratAEmploye()
	{
		
		  try {
				boolean b=iemployeservice.affecterContratAEmploye(22, 4);
				System.out.println("affectation est  "+b);
				assertEquals(b, true);
		    } catch (Exception e){
		        assertNull(e);
		    }
	}
	
	@Test
	public void testdeleteContratById()
	{
		
		try {
			boolean b=iemployeservice.deleteContratById(13);
			
			System.out.println("etat est  "+b);
			assertEquals(b, false);
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}
	
	@Test
	public void testgetNombreEmployeJPQL()
	{
		
		try {
			int nb=iemployeservice.getNombreEmployeJPQL();
			
			System.out.println("le nb d'emplyes  "+nb);
			assertEquals(nb, 5);
		    } catch (Exception e){
		        assertNull(e);
		    }
		}
	
	@Test
	public void testgetAllEmployeByEntreprise()
	{
		Entreprise em=new Entreprise("it","test");
		
		try {
			ientrepriseService.ajouterEntreprise(em);
			List<Employe> elist;
			elist=iemployeservice.getAllEmployeByEntreprise(em);
			System.out.println("la liste des employes  "+elist);
			assertEquals(elist, 7);
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}
	
	
@Test
public void testdeleteAllContratJPQL()
{
	try {
		boolean b=iemployeservice.deleteAllContratJPQL();
		System.out.println("etat est  "+b);
		assertEquals(b, true);
	    } catch (Exception e){
	        assertNull(e);
	    }


}
/*
@Test
public void testgetSalaireMoyenByDepartementId()
{
	try {
		double d=iemployeservice.getSalaireMoyenByDepartementId(1);
		System.out.println("le salaire moyen des employens du departement 1"+d);
		assertEquals(d, 7);
	    } catch (Exception e){
	        assertNull(e);
	    }
	
}*/
	
	/*@Test
	public void testdesaffecterEmployeDuDepartement()
	{
		try {
			boolean b=iemployeservice.desaffecterEmployeDuDepartement(1,1);
			assertEquals(b, true);
		    } catch (Exception e){
		        assertNull(e);
		    }
		
	}*/
}
