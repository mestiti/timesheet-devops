package tn.esprit.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;

import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;



@Controller
public class IControllerEmployeImpl  {
	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;

	
    
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);
		
	}




	
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		iemployeservice.desaffecterEmployeDuDepartement(employeId, depId);
	}

	
	public int ajouterContrat(Contrat contrat) {
		iemployeservice.ajouterContrat(contrat);
		return contrat.getReference();
	}
	
	public void affecterContratAEmploye(int contratId, int employeId)
	{
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}

	

	

	public void deleteContratById(int contratId) {
		iemployeservice.deleteContratById(contratId);
	}

	
	public int getNombreEmployeJPQL() {
		
		return iemployeservice.getNombreEmployeJPQL();
	}



	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return iemployeservice.getAllEmployeByEntreprise(entreprise);
	}


	


	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();
		
	}

	
	

	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		return iemployeservice.getSalaireMoyenByDepartementId(departementId);
	}

	
	




	

	
	

	
	

	
	
    
	
	
	
}
