package com.jnr23.projectfullstack.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jnr23.projectfullstack.entities.User;
import com.jnr23.projectfullstack.exceptions.ResourceNotFoundException;
import com.jnr23.projectfullstack.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} 
	}
	
	public User update(Long id, User obj) {
		try {
		User user = repository.getOne(id);
		updateUser(user, obj);
		return repository.save(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public void updateUser(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setCpf(obj.getCpf());
	}
	
}
