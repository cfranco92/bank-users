package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping
	public UsersDTO save(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		Users user = usersMapper.usersDTOtoUsers(usersDTO);
		user = usersService.save(user);
		usersDTO = usersMapper.usersToUsersDTO(user);

		return usersDTO;
	}

	@GetMapping("/{id}")
	public UsersDTO findById(@PathVariable("id") String id) throws Exception {

		Users user = null;
		UsersDTO usersDTO = null;
		if (usersService.findById(id).isPresent() == true)
			user = usersService.findById(id).get();

		usersDTO = usersMapper.usersToUsersDTO(user);

		return usersDTO;
	}

	@GetMapping()
	public List<UsersDTO> findAll() throws Exception {

		List<Users> users = usersService.findAll();
		List<UsersDTO> usersDTOs = usersMapper.usersListToUsersDTOList(users);

		return usersDTOs;
	}
}
