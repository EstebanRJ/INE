package com.libreria;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LibrosRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LibrosRepository repo;
	
	@Test
	public void testCreateUser() {
		Libros libros = new Libros();
		libros.setAutor("Mario Benedetti");
		libros.setCantidad(10);
		libros.setNombre("La tregua");
		libros.setSucursal("Norte");
		
		Libros savedUser = repo.save(libros);
		
		Libros existUser = entityManager.find(Libros.class, savedUser.getId());
		
		assertThat(libros.getNombre()).isEqualTo(existUser.getNombre());
		
	}
	
	@Test
	public void testFindByNombre() {
		String nombre="La tregua";
		Libros libro= repo.findByNombre(nombre);
		
		assertThat(libro.getNombre()).isEqualTo(nombre);
	}
}
