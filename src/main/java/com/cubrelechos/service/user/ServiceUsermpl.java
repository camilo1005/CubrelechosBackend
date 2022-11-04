package com.cubrelechos.service.user;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cubrelechos.entities.User;
import com.cubrelechos.repositories.RepositoryUser;

@Service
public class ServiceUsermpl implements ServiceUser {
	
	@Autowired
	private RepositoryUser user;

	@Override
	public Iterable<User> findAll() {

		return user.findAll();
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return user.findAll(pageable);
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return user.findById(id);
	}

	@Override
	public User save(User users) {

		return user.save(users);
	}

	@Override
	public void deletById(Long id) {
		user.deleteById(id);
		
	}




	
	
	

}
