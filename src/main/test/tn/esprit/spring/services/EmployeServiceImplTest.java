package tn.esprit.spring.services;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.*;
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
			  Employe e=iemployeservice.mettreAjourEmailByEmployeId("tttttttttt@es.tn", 1);
			  assertEquals(e.getEmail(), "tttttttttt@es.tn");
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
			//System.out.println("id du nv employe  "+id);
			assertEquals(c.getTypeContrat(), "CDI");
	    } catch (Exception e){
	        assertNull(e);
	    }
		}

	@Test
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
//yasmine

//ons++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//@Test
//public int testajouterEmploye(Employe employe) {
//	
//	employeRepository.save(employe);
//	return employe.getId();
//}
//
//@Test
//public boolean testaffecterEmployeADepartement(int employeId, int depId) {
//	
//
//	boolean affected = false;
//	
//	Optional<Departement> depF = deptRepoistory.findById(depId);
//	Optional<Employe> empF = employeRepository.findById(employeId);
//	if (depF.isPresent() && empF.isPresent()) {
//		Departement depManagedEntity = depF.get();
//
//		Employe employeManagedEntity = empF.get();
//
//
//		if (depManagedEntity.getEmployes() == null) {
//
//			List<Employe> employes = new ArrayList<>();
//			employes.add(employeManagedEntity);
//
//			depManagedEntity.setEmployes(employes);
//
//		} else {
//
//			depManagedEntity.getEmployes().add(employeManagedEntity);
//
//		}
//
//		affected = true;
//		
//
//	}
//
//	
//
//	return affected;
//
//}
//
//@Test
//public String testgetEmployePrenomById(int employeId) {
//	
//
//	Optional<Employe> empF = employeRepository.findById(employeId);
//
//	if (empF.isPresent()) {
//		Employe employeManagedEntity = empF.get();
//		return employeManagedEntity.getPrenom();
//	}
//
//	return "there is no user prenom matches with your input";
//}
//
//@Test
//public boolean testdeleteEmployeById(int employeId) {
//	boolean deleted = false;
//
//
//	Optional<Employe> empF = employeRepository.findById(employeId);
//	if (empF.isPresent()) {
//		Employe employe = empF.get();
//		
//		for (Departement dep : employe.getDepartements()) {
//			dep.getEmployes().remove(employe);
//		}
//
//		employeRepository.delete(employe);
//		
//		deleted = true;
//		
//
//	}
//	
//
//	return deleted;
//}
//
//@Test
//public List<String> testgetAllEmployeNamesJPQL() {
//	
//	return employeRepository.employeNames();
//}
//
//@Test
//public boolean testmettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
//	
//
//	boolean updated = false;
//
//	employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
//	updated = true;
//	return updated;
//}
//
//@Test
//public float testgetSalaireByEmployeIdJPQL(int employeId) {
//	
//
//	return employeRepository.getSalaireByEmployeIdJPQL(employeId);
//}
//
//@Test
//public List<Employe> testgetAllEmployes() {
//	
//
//	return (List<Employe>) employeRepository.findAll();
//}
//
//@Test
//public List<Timesheet> testgetTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
//		Date dateFin) {
//	
//
//	return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
//}

}
