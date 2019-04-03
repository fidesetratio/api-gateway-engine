package com.application.repo;

import org.springframework.data.repository.CrudRepository;

import com.app.manager.model.Roles;

public interface RoleRepository extends CrudRepository<Roles,Long>{

}
