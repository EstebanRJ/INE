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
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		Usuarios user = new Usuarios();
		user.setEmail("estebanreyes@gmail.com");
		user.setPassword("hola123");
		user.setFirstName("Esteban");
		user.setLastName("Reyes");
		
		Usuarios savedUser = repo.save(user);
		
		Usuarios existUser = entityManager.find(Usuarios.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "estebanreyes@gmail.net";
		Usuarios user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
