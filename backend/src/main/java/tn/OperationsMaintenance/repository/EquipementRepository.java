package tn.OperationsMaintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.OperationsMaintenance.entity.Equipement;
import java.util.List;
import java.util.Optional;


public interface EquipementRepository extends JpaRepository<Equipement, Integer> {
Equipement findByNom(String nom);
Optional<Equipement> findById(int id);}
