package com.cubrelechos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cubrelechos.entities.User;

@Repository
public interface RepositoryUser extends  JpaRepository< User, Long > {



}
