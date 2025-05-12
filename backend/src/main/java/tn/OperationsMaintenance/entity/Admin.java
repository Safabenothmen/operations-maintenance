package tn.OperationsMaintenance.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("Admin")

public class Admin extends User {

}
