package com.libreria;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Usuarios, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public Usuarios findByEmail(String email);
	
}
