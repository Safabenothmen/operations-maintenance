package tn.OperationsMaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.OperationsMaintenance.entity.Panne;

public interface PanneRepository extends JpaRepository<Panne,Integer> {

	@Query("SELECT COUNT(p) FROM Panne p WHERE p.id NOT IN (SELECT i.equipement.id FROM Intervention i WHERE i.statut = 'Terminee')")
	long countPannesEnAttente();

}
