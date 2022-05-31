package com.ahmed.vetements.service;

import java.util.List;

import com.ahmed.vetements.entities.Role;

public interface RoleService {

	List <Role> findAll();
	Role findByRole(String role);



}
