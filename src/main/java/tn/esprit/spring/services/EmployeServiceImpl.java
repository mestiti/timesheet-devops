package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import tn.esprit.spring.repository.TimesheetRepository;

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
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeServiceImpl.class); 
	
	///debut yasmine
	public Employe mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).orElseGet(Employe::new);
		 try {
	        	LOGGER.info("************In mettreAjourEmailByEmployeId contract**********");
	        	LOGGER.info("email initial {}", employe.getEmail());
	        	
	        	employe.setEmail(email);
	    		employeRepository.save(employe);
	        	
	        	
	        	} catch (Exception e) {LOGGER.error("Erreur : contrat non ajouté");}
	        
		 LOGGER.info("************In fin contract**********");
	    	LOGGER.info("email modifié {}", employe.getEmail());
		
		if(employe.getEmail().equals(email))
		{
			return employe;
		}
		 
		return employe;

	}


	@Transactional
	public boolean desaffecterEmployeDuDepartement(int employeId, int depId)
	{boolean b=false;
		Departement dep = deptRepoistory.findById(depId).orElseGet(Departement::new);

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				b=true;
				break;
			}
		}
	return b;}

	public int ajouterContrat(Contrat contrat) {
		

        try {
        	LOGGER.trace("************In ajouter contract**********");
        	LOGGER.debug("messageajoutcontract {}", contrat.getClass());
        	contratRepoistory.save(contrat);

        	
        	
        	} catch (Exception e) {LOGGER.error("Erreur : contrat non ajouté");}
        
        LOGGER.trace("************In fin contract**********");
    	LOGGER.debug("messageajoutcontract {}", contrat.getReference());
        return contrat.getReference();
	}

	public boolean affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElseGet(Employe::new);
  boolean b=false;

  try {
  	LOGGER.trace("************In affecter contract a employe**********");
  	LOGGER.debug("messageaffectercontract {}",employeManagedEntity.getNom());
  	
  	contratManagedEntity.setEmploye(employeManagedEntity);
	contratRepoistory.save(contratManagedEntity);

  	
  	
  	} catch (Exception e) {LOGGER.error("Erreur : employe non affecté");}
		
		 if(contratRepoistory.findById(contratId).isPresent())
		    {b=true;}
         
		 	LOGGER.trace("************In fin affecter contract a employe**********");
		  	LOGGER.debug("messageaffectercontract {}",contratManagedEntity.getEmploye().getNom());
		  	
        	 return b;
        		 
		
	}


	public boolean deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElseGet(Contrat::new);
		boolean b=true;
	     try {
	        	LOGGER.trace("**************In supprimer contrat**********");
	        	LOGGER.debug("contract a supprimer{}", contratManagedEntity.getReference());
	        	contratRepoistory.delete(contratManagedEntity);

	        	
	        	} catch (Exception e) {LOGGER.error("Erreur : contrat supprimé");}
		
	
		 if(contratRepoistory.findById(contratId).isPresent())
         {b=false;}
		 LOGGER.trace("**************In fin supprimer contrat**********");
     	LOGGER.debug("contract a supprimer{}", contratManagedEntity.getReference());
        	 return b;
        		 

	}

	public int getNombreEmployeJPQL() {
		int total=0;
		try {
        	LOGGER.trace("**************In nombre total d'employes**********");
        	LOGGER.debug("nob intial est {}", 0);
        	total=employeRepository.countemp();

        	
        	} catch (Exception e) {LOGGER.error("Erreur : employes non existants");}
		
		LOGGER.trace("**************In nombre total d'employes**********");
    	LOGGER.debug("messageprendreemploye {}", total);
		return employeRepository.countemp();
	
	}
	
	
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	
	public boolean deleteAllContratJPQL() {
		boolean b=true;
		try {
        	LOGGER.info("**************In nombre delete all contract**********");
        	LOGGER.info("message le nb de contract {}", contratRepoistory.count());
        	employeRepository.deleteAllContratJPQL();
    		 b=false;

        	
        	} catch (Exception e) {LOGGER.error("Erreur : contracts non existants");}
		
		LOGGER.info("**************In fin nombre delete all contract**********");
    	LOGGER.info("message le nb de contract {}", contratRepoistory.count());
		
         if(contratRepoistory.count()==0)
         {b=true;}
         return b;
        		 
	}
	

	public Double getSalaireMoyenByDepartementId(int departementId) {
		
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	///fin yasmine
	
	
	///debut ons

	public int ajouterEmploye(Employe employe) {
		
		employeRepository.save(employe);
		
		return employe.getId();
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional<Departement> depF = deptRepoistory.findById(depId);
		Optional<Employe> empF = employeRepository.findById(employeId);
		if(depF.isPresent() && empF.isPresent()) {
		Departement depManagedEntity = depF.get();
		Employe employeManagedEntity = empF.get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}}


	public String getEmployePrenomById(int employeId) {
		Optional<Employe> empF = employeRepository.findById(employeId);
		if(empF.isPresent()) {
			Employe employeManagedEntity = empF.get();
			return employeManagedEntity.getPrenom();
		}
		
		return "he has no family name";
	}


	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> empF = employeRepository.findById(employeId);
		if(empF.isPresent()) {
		Employe employe = empF.get();
		
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}}


	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();
	}


	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}


	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}


	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}
	
	
	///fin ons


}
