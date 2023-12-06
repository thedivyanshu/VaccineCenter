package com.vaccineCenter.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vaccineCenter.pojo.User;
public interface UserRepository extends CrudRepository<User, Integer> {


	public User findByName(String name);

	@Query("SELECT r FROM User r WHERE r.email = :email AND r.password = :password")
	public boolean checkLoginRepo(@Param("email")String email, String password);

	User findByEmailAndPassword(String email, String password);

	List<User> findByEmail(String email);

}

