package org.learn.simpl.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.learn.simpl.entity.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
	@Autowired
	private MongoOperations mongoOperations;
	
	/*
	 * @Autowired public SequenceGeneratorService(MongoOperations mongoOperations) {
	 * this.mongoOperations = mongoOperations; }
	 */
	  public long generateSequence(String seqName) {
		  /*
				  DatabaseSequence count = mongoOperations.findAndModify(
				  query(where("_id").is(seqName)),
				  new Update().inc("seq", 1), 
				  options().returnNew(true).upsert(true),
				  DatabaseSequence.class);
				  */
		  
		  DatabaseSequence counter = mongoOperations.findAndModify(
				  query(where("_id").is(seqName)),
	                new Update().inc("seq",1), 
	                options().returnNew(true).upsert(true),
	                DatabaseSequence.class);
		  
		  System.out.println("  generateSequence count  >>> "+counter.getSeq());

	        return !Objects.isNull(counter) ? counter.getSeq() : 1;
		  
		  
		  
		 // return (int)count.getSeq();
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
			System.out.println("  getNextSequenceId seqId  >>> "+seqId.getSeq());
			return seqId.getSeq();

		  }
}
