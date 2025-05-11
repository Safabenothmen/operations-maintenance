package tn.OperationsMaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.OperationsMaintenance.entity.Panne;

public interface PanneRepository extends JpaRepository<Panne,Integer> {

}
