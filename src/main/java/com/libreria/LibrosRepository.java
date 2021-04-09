package com.libreria;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibrosRepository extends JpaRepository<Libros, Long> {
	@Query("SELECT l FROM Libros l")
	public Libros findByNombre(String nombre);
	
}