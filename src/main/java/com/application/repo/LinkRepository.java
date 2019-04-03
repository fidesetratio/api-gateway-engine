package com.application.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.manager.model.Link;

public interface LinkRepository  extends CrudRepository<Link,Long>{
	public Link findByPath(String path);
	public List<Link> findByActiveTrue();
}
