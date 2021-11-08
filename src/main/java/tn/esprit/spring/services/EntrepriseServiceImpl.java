package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			LOGGER.info("+++++++++++++In ajouter entreprise +++++++++++++");
			LOGGER.info("messageajoutentreprise {}", entreprise.getClass());
			entrepriseRepoistory.save(entreprise);

		} catch (Exception e) {
			LOGGER.error("Erreur : entreprise non ajouté");
		}
		LOGGER.info("+++++++++++++ entreprise est ajouté +++++++++++++");
		LOGGER.info("messageajoutentreprise {}", entreprise.getId());
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try {
			LOGGER.info("+++++++++++++In ajouter Departement +++++++++++++");
			LOGGER.info("messageajoutdepartement {}", dep.getClass());
		} catch (Exception e) {
			LOGGER.error("Erreur : Departement non ajouté");
		}
		LOGGER.info("+++++++++++++ Departement est ajouté +++++++++++++");
		LOGGER.info("messageajoutdepartement {}", dep.getId());
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElseGet(Entreprise::new);
		Departement depManagedEntity = deptRepoistory.findById(depId).orElseGet(Departement::new);
		try {
			LOGGER.info("+++++++++++++In affecter departement a entreprise+++++++++++++");
			LOGGER.info("messageaffecterdepartement {}", entrepriseManagedEntity.getName());
			
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
		} catch (Exception e) {
			LOGGER.error("Erreur : departement non affecté");
		}
		LOGGER.info("+++++++++++++In fin affecter departement a entreprise+++++++++++++");
		LOGGER.info("messageaffecterdepartement {}", depManagedEntity.getEntreprise().getName());
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		LOGGER.info("+++++++++++++In get All Departements Names by Entreprise+++++++++++++");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElseGet(Entreprise::new);
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		LOGGER.info("+++++++++++++Fin get All Departements Names by Entreprise+++++++++++++");
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		LOGGER.info("+++++++++++++In removing Entreprise+++++++++++++");
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElseGet(Entreprise::new));	
		LOGGER.info("+++++++++++++Fin removing Entreprise +++++++++++++");
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		LOGGER.info("+++++++++++++In removing Departement+++++++++++++");
		deptRepoistory.delete(deptRepoistory.findById(depId).orElseGet(Departement::new));
		LOGGER.info("+++++++++++++Fin removing Departement+++++++++++++");
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		LOGGER.info("+++++++++++++Getting Entreprise+++++++++++++");
		return entrepriseRepoistory.findById(entrepriseId).orElseGet(Entreprise::new);
	}

}
