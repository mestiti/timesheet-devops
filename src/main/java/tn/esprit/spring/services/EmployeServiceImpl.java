package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;


@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	

	
	public Employe mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).orElseGet(Employe::new);
		employe.setEmail(email);
		employeRepository.save(employe);
		if(employe.getEmail().equals(email))
		{
			return employe;
		}
		 
		return employe;

	}


	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public boolean affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElseGet(Employe::new);
  boolean b=false;
		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		 if(contratRepoistory.findById(contratId).isPresent())
		    {b=true;}
         
        	 return b;
        		 
		
	}


	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	


}
