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
	public void testCreateUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userWarith = new User("warith@java.net", "password", "Warith", "Lawal");
		userWarith.addRole(roleAdmin);
		
		User savedUser = repo.save(userWarith);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userSekk = new User("sekk@java.net", "password", "Sekk", "Hoop");
		
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userSekk.addRole(roleEditor);
		userSekk.addRole(roleAssistant);
		
		User savedUser = repo.save(userSekk);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		
		listUsers.forEach(user -> 
				System.out.println(user));
	}
	
	@Test
	public void testFindUserById() {
		User user = repo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User user = repo.findById(1).get();
		user.setEnabled(true);
		
		user.setEmail("warith@newmail.java.co");
		
		repo.save(user);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user = repo.findById(2).get();
		Role editor = new Role(3);
		Role salesPerson = new Role(2);
		
		user.getRoles().remove(salesPerson);
		user.addRole(editor);
		
		repo.save(user);
		
	}
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "sekk@java.net";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Integer id = 1;
		Long count = repo.countById(id);
		assertThat(count).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Integer id = 5;
		repo.updateUserEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Integer id = 6;
		repo.updateUserEnabledStatus(id, true);
	}
}
