package tn.OperationsMaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.OperationsMaintenance.entity.Intervention;

public interface InterventionRepository extends JpaRepository<Intervention, Integer> {

}
