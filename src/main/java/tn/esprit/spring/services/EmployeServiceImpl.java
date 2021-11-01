package tn.esprit.spring.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
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
		Departement dep = deptRepoistory.findById(depId).orElseGet(Departement::new);

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		

        try {
        	LOGGER.trace("************In ajouter contract**********");
        	LOGGER.debug(null, contrat.getClass(), "messageajoutcontract {}");
        	contratRepoistory.save(contrat);

        	
        	
        	} catch (Exception e) {LOGGER.error("Erreur : contrat non ajouté");}
        
        LOGGER.trace("************In fin contract**********");
    	LOGGER.debug(null, contrat.getReference(), "messageajoutcontract {}");
        return contrat.getReference();
	}

	public boolean affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElseGet(Employe::new);
  boolean b=false;

  try {
  	LOGGER.trace("************In affecter contract a employe**********");
  	LOGGER.debug(employeManagedEntity.getNom(), "messageaffectercontract {}");
  	
  	contratManagedEntity.setEmploye(employeManagedEntity);
	contratRepoistory.save(contratManagedEntity);

  	
  	
  	} catch (Exception e) {LOGGER.error("Erreur : employe non affecté");}
		
		 if(contratRepoistory.findById(contratId).isPresent())
		    {b=true;}
         
		 	LOGGER.trace("************In fin affecter contract a employe**********");
		  	LOGGER.debug(contratManagedEntity.getEmploye().getNom(), "messageaffectercontract {}");
		  	
        	 return b;
        		 
		
	}


	public boolean deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		boolean b=true;
	     try {
	        	LOGGER.trace("**************In supprimer contrat**********");
	        	LOGGER.debug(null, contratManagedEntity.getReference(), "contract a supprimer{}");
	        	contratRepoistory.delete(contratManagedEntity);

	        	
	        	} catch (Exception e) {LOGGER.error("Erreur : contrat supprimé");}
		
	
		 if(contratRepoistory.findById(contratId).isPresent())
         {b=false;}
		 LOGGER.trace("**************In fin supprimer contrat**********");
     	LOGGER.debug(null, contratManagedEntity.getReference(), "contract  supprimé{}");
        	 return b;
        		 

	}

	public int getNombreEmployeJPQL() {
		int total=0;
		try {
        	LOGGER.trace("**************In nombre total d'employes**********");
        	LOGGER.debug(null, 0, "messageprendreemploye {}");
        	total=employeRepository.countemp();

        	
        	} catch (Exception e) {LOGGER.error("Erreur : employes non existants");}
		
		LOGGER.trace("**************In nombre total d'employes**********");
    	LOGGER.debug(null, total, "messageprendreemploye {}");
		return employeRepository.countemp();
	
	}
	
	
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	
	public boolean deleteAllContratJPQL() {
		boolean b=true;
		try {
        	LOGGER.trace("**************In suppresion des employes**********");
        	LOGGER.debug(null, contratRepoistory.count(), "message nb total contract {}");
        	employeRepository.deleteAllContratJPQL();
            b=false;
        	
        	} catch (Exception e) {LOGGER.error("Erreur : contract non supprimé");}
		
		LOGGER.trace("**************In fin suppresion des employes**********");
    	LOGGER.debug(null, contratRepoistory.count(), "message le nb de contract restant {}");
    
         if(contratRepoistory.count()==0)
         {b=true;}
         return b;
        		 
	}
	

	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	


}
