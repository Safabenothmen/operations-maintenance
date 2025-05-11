package tn.OperationsMaintenance.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.OperationsMaintenance.entity.Equipement;
import tn.OperationsMaintenance.entity.Equipement.Etat;
import tn.OperationsMaintenance.entity.Panne;
import tn.OperationsMaintenance.repository.EquipementRepository;
import tn.OperationsMaintenance.repository.PanneRepository;

@Service
public class PanneService {
private final PanneRepository panneRepository;
private final EquipementRepository equipementRepository;
public PanneService(PanneRepository panneRepository, EquipementRepository equipementRepository) {
    this.panneRepository = panneRepository;
    this.equipementRepository = equipementRepository;
}

//create
public Panne ajouterPanne(int equipementId, Panne panne) {
    Optional<Equipement> equipements = equipementRepository.findById(equipementId);
    if (equipements.isPresent()) {
        Equipement equipement = equipements.get();

        // Mettre à jour l'état
        equipement.setEtat(Etat.en_panne);
        equipementRepository.save(equipement);

        panne.setEquipement(equipement);
        panne.setDate_sign(new Date());

        return panneRepository.save(panne);
    } else {
        throw new RuntimeException("Équipement non trouvé");
    }
}

//modifer 
public Panne modifierPanne(int id, Panne nouvellePanne) {
    return panneRepository.findById(id)
            .map(panne -> {  // Si l'objet est présent, on l'update
                panne.setDescription(nouvellePanne.getDescription());
                panne.setCategorie(nouvellePanne.getCategorie());
                panne.setDate_sign(nouvellePanne.getDate_sign());
                panne.setEquipement(nouvellePanne.getEquipement());
                return panneRepository.save(panne);  // On sauvegarde et retourne l'entité modifiée
            })
            .orElseThrow(() -> new RuntimeException("Panne non trouvée avec l'ID : " + id)); 
}


//supprimer
public void supprimerPanne(int id) {
	panneRepository.deleteById(id);
}

//consulter  tous les pannes
public List<Panne> getAllPannes() {
    return panneRepository.findAll();
}

//consulter panne par id
public Optional<Panne> getPanneById(int id) {
	return panneRepository.findById(id);
}

}
