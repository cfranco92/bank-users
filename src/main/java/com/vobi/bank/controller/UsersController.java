package com.vobi.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.mapper.UsersMapper;
import com.vobi.bank.service.UsersService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

	@Autowired
	UsersService usersService;

	@Autowired
	UsersMapper usersMapper;

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws Exception {
		usersService.deleteById(id);
	}
}
