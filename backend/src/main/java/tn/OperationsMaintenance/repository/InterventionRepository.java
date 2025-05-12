package tn.OperationsMaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.OperationsMaintenance.entity.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Integer> {
	List<Intervention> findByTechnicienId(int technicienId);
}    

