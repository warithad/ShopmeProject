package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@Rollback(false)
public class UserRepsitoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userWarith = new User("warith@java.net", "password", "Warith", "Lawal");
		userWarith.addRole(roleAdmin);
		
		User savedUser = repo.save(userWarith);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
}
