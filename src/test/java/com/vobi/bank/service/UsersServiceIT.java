package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;
import com.vobi.bank.repository.UserTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersServiceIT {

	@Autowired
	UsersService usersService;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(usersService);
		assertNotNull(userTypeRepository);
	}

	@Test
	@Order(2)
	void debeCrearUnUsuario() throws Exception {
		// Arrange
		Integer idUserType = 3;
		String idUser = "cfrancobedoya@gmail.com";

		Users user = null;
		UserType userType = userTypeRepository.findById(idUserType).get();

		user = new Users();
		user.setUserEmail(idUser);
		user.setName("Cristian Franco");
		user.setToken(null);
		user.setEnable("Y");
		user.setUserType(userType);

		// Act

		user = usersService.save(user);

		// Assert

		assertNotNull(user, "El usuario es nulo no se pudo grabar");
	}

	@Test
	@Order(3)
	void debeModificarUnUsuario() throws Exception {
		// Arrange
		String idUser = "cfrancobedoya@gmail.com";
		Users user = null;

		user = usersService.findById(idUser).get();
		user.setEnable("N");

		// Act

		user = usersService.update(user);

		// Assert

		assertNotNull(user, "El user es nulo no se pudo modificar");
	}

	@Test
	@Order(4)
	void debeBorrarUnUser() throws Exception {
		// Arrange

		String idUser = "cfrancobedoya@gmail.com";
		Users user = null;
		Optional<Users> userOptional = null;

		assertTrue(usersService.findById(idUser).isPresent(), "No encontro el user");

		user = usersService.findById(idUser).get();

		// Act
		usersService.delete(user);
		userOptional = usersService.findById(idUser);

		// Assert

		assertFalse(userOptional.isPresent(), "No pudo borrar el user");
	}

	@Test
	@Order(5)
	void debeConsultarTodosLosUsers() {
		// Arrange
		List<Users> users = null;

		// Act

		users = usersService.findAll();

		users.forEach(user -> log.info(user.getName()));

		// Assert

		assertFalse(users.isEmpty(), "No consulto Users");
	}

}
