package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;


public interface IEmployeService {
	
	//yasmine
	public Employe mettreAjourEmailByEmployeId(String email, int employeId);
	
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	public boolean affecterContratAEmploye(int contratId, int employeId);
	
	public boolean deleteContratById(int contratId);
	public int getNombreEmployeJPQL();
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);
	
	public boolean deleteAllContratJPQL();
	
	public Double getSalaireMoyenByDepartementId(int departementId);
//fin yasmine
	
	
	

	
}
