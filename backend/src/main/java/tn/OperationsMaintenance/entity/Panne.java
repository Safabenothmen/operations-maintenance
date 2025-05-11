package tn.OperationsMaintenance.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Panne {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
private int id; 
	private String description;
	String categorie;
	Date date_sign;
	
	
	@ManyToOne
	@JsonIgnoreProperties("pannes")  
	@JoinColumn(name="idEquipement")
	private Equipement equipement;
	
}
