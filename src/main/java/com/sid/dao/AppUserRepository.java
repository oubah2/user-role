package com.sid.dao;

import com.sid.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUserName(String userName);
}
