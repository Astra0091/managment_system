package org.learn.simpl;

import org.learn.simpl.entity.Students;
import org.learn.simpl.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class StudentManagmentApplication implements CommandLineRunner{
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagmentApplication.class, args);
	}
     @Autowired
	private StudentsRepository repository;
	@Override
	public void run(String... args) throws Exception {
		
		 Students students = new Students(5," Ashok ","Anand Raja","aajder@gmail.com","343334497"); 
		 Students students1 = new Students(6,"Raja Ashok","Raghu Raja","ashok@gmail.com","843433433417");
		  
		 //repository.save(students); 
		// repository.save(students1);
		 		
	}

	
	
}
