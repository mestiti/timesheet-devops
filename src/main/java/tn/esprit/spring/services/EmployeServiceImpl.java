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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeServiceImpl.class); 
	
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
		

        try {
        	LOGGER.info("************In ajouter contract**********");
        	contratRepoistory.save(contrat);

        	LOGGER.info(null, contrat.getClass(), "messageajoutcontract {}");
        	
        	} catch (Exception e) {LOGGER.error("Erreur : contrat non suppprime");}
        return contrat.getReference();
	}

	public boolean affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElseGet(Employe::new);
  boolean b=false;

  try {
  	LOGGER.info("************In affecter contract a employe**********");
  	contratManagedEntity.setEmploye(employeManagedEntity);
	contratRepoistory.save(contratManagedEntity);

  	LOGGER.info(contratManagedEntity.getEmploye().getNom(), "messageaffectercontract {}");
  	
  	} catch (Exception e) {LOGGER.error("Erreur : employe non affecté");}
		
		 if(contratRepoistory.findById(contratId).isPresent())
		    {b=true;}
         
        	 return b;
        		 
		
	}


	public boolean deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		boolean b=true;
	     try {
	        	LOGGER.info("**************In supprimer contrat**********");
	        	contratRepoistory.delete(contratManagedEntity);

	        	LOGGER.info(null, contratManagedEntity.getReference(), "messagesupprimer contract {}");
	        	
	        	} catch (Exception e) {LOGGER.error("Erreur : contrat supprimé");}
		
	
		 if(contratRepoistory.findById(contratId).isPresent())
         {b=false;}
         
        	 return b;
        		 

	}

	public int getNombreEmployeJPQL() {
		
		try {
        	LOGGER.info("**************In nombre total d'employes**********");
        	int total=employeRepository.countemp();

        	LOGGER.info(null, total, "messageprendreemploye {}");
        	
        	} catch (Exception e) {LOGGER.error("Erreur : employes non existants");}
		
		return employeRepository.countemp();
	
	}
	
	
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	
	public boolean deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
		boolean b=false;
         if(contratRepoistory.count()==0)
         {b=true;}
         return b;
        		 
	}
	

	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	


}
