package tn.OperationsMaintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.OperationsMaintenance.entity.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien,Integer> {

	List<Technicien> findByDisponibiliteTrue();


}
