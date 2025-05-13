package tn.OperationsMaintenance.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity


@Data
@DiscriminatorValue("Technicien")

public class Technicien extends User {


    private String competences; 
    private Boolean disponibilite;
	
    @OneToMany(mappedBy = "technicien", cascade = CascadeType.REMOVE, orphanRemoval = true)
	//@JsonManagedReference("technicien-interventions")

	private List<Intervention> interventions=new ArrayList<>();
}
	