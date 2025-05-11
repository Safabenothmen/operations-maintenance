package tn.OperationsMaintenance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.OperationsMaintenance.entity.Equipement;
import tn.OperationsMaintenance.entity.Equipement.Etat;
import tn.OperationsMaintenance.repository.EquipementRepository;

@Service
public class EquipementService {
	private EquipementRepository equipementRepository;

	public EquipementService(EquipementRepository equipementRepository) {
		this.equipementRepository = equipementRepository;
	}
	
	
	//create 
	public Equipement ajouterEquipement(Equipement equipement)
	{
		
		if (equipement.getNom() != null && equipementRepository.findByNom(equipement.getNom()) != null) 
		{
			throw new RuntimeException("un equipement avec ce nom déjà existe");

		}
        equipement.setEtat(Etat.Fonctionnel);
        
        return equipementRepository.save(equipement);
		
	}
	
	
	//getalll
public List<Equipement> getAllEq(){
	return equipementRepository.findAll();
}	 
}
