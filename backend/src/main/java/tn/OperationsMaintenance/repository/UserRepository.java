package tn.OperationsMaintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.OperationsMaintenance.entity.User;

@Repository 
public interface UserRepository extends JpaRepository <User,Integer>{
    List<User> findByRole(User.Role role);


}
