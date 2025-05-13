package tn.OperationsMaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.entity.Intervention.Statut;

public interface InterventionRepository extends JpaRepository<Intervention, Integer> {
	List<Intervention> findByTechnicienId(int technicienId);

    long countByStatut(Intervention.Statut statut);
}    

