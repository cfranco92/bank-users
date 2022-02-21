package com.vobi.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;

public interface UsersMapper {
	@Mapping(source = "documentType.dotyId", target = "dotyId")
	public UsersDTO usersToUsersDTO(Users users);

	@Mapping(target = "userType.ustyId", source = "ustyId")
	public Users usersDTOtoUsers(UsersDTO userDTO);

	public List<UsersDTO> usersListToUsersDTOList(List<Users> users);

	public List<Users> userDTOListToUsersList(List<UsersDTO> customerDTOs);
}
