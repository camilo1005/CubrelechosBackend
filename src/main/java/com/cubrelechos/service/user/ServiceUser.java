package com.cubrelechos.service.user;




import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cubrelechos.entities.User;

public interface ServiceUser {
	
	public Iterable<User> findAll();
	
	public Page<User>findAll(Pageable pageable);
	
	public Optional<User>findById(Long  id);
	
	public User save(User users);
	
	public void deletById(Long  id );

}
