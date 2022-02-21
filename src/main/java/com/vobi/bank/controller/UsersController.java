package com.vobi.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;
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

	@PutMapping
	public UsersDTO update(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		Users user = usersMapper.usersDTOtoUsers(usersDTO);
		user = usersService.update(user);
		usersDTO = usersMapper.usersToUsersDTO(user);

		return usersDTO;
	}
}
