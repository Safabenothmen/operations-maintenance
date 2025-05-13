package tn.OperationsMaintenance.entity;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Employe")

public class Employe extends User {
	private Date DateEmbauche;

}
