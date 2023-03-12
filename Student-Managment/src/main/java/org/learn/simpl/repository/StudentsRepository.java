package org.learn.simpl.repository;

import org.learn.simpl.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository	
public interface StudentsRepository extends MongoRepository<Students, Integer> {

}

