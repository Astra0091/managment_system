package org.learn.simpl.serviceImpl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.Optional;

import org.learn.simpl.entity.DatabaseSequence;
import org.learn.simpl.entity.Students;
import org.learn.simpl.repository.StudentsRepository;
import org.learn.simpl.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements StudentsService {

@Autowired (required = false)
private StudentsRepository repository;

private MongoOperations mongoOperations;

/*
 * public StudentsServiceImpl(StudentsRepository repository) { super();
 * this.repository= repository; }
 */

@Override
public List<Students> getAllStudents() {
	//repository.findAll();
	return repository.findAll();
}

@Override
public Optional<Students>  getStudent(int id) {
	 repository.findById(id);
	return repository.findById(id);
}


@Override
public void createStudent(Students students) {
	repository.save(students);
}


public long generateSequence(String seqName) {
	  DatabaseSequence count = mongoOperations.findAndModify(
	  query(where("_id").is(seqName)),
	  new Update().inc(seqName, 1), 
	  options().returnNew(true).upsert(true),
	  DatabaseSequence.class);


return count.getSeq();
}
//OR Second method
public long getNextSequenceId(String key) {

Query query = new Query(Criteria.where("_id").is(key));

  Update update = new Update();
update.inc("seq", 1);

FindAndModifyOptions options = new FindAndModifyOptions();
options.returnNew(true);

DatabaseSequence seqId = 
		mongoOperations.findAndModify(query, update, options, DatabaseSequence.class);

return seqId.getSeq();

}





}
