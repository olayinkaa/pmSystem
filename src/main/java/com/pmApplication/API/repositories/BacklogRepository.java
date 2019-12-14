package com.pmApplication.API.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmApplication.API.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

	Backlog findByProjectIdentifier(String Identifier);
}
