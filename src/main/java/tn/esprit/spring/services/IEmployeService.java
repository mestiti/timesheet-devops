package tn.esprit.spring.services;



import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;




public interface IEmployeService {
	
	
	public Employe mettreAjourEmailByEmployeId(String email, int employeId);//yas+

	public boolean desaffecterEmployeDuDepartement(int employeId, int depId);//yas
	public int ajouterContrat(Contrat contrat);//yas+
	public boolean affecterContratAEmploye(int contratId, int employeId);//yas+
	
	public boolean deleteContratById(int contratId);//yas+
	public int getNombreEmployeJPQL();//yas+
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);//yas+
	
	public boolean deleteAllContratJPQL();//yas+
	
	public Double getSalaireMoyenByDepartementId(int departementId);//+yas
	
	
	
	

	
}
