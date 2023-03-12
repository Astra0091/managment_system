/*
 * package org.learn.simpl.controller;
 * 
 * import org.learn.simpl.entity.Students; import
 * org.learn.simpl.repository.StudentsRepository; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @RestController
 * 
 * @EnableMongoRepositories
 * 
 * @RequestMapping("/repo") public class StudentsRepoController {
 * 
 * @Autowired StudentsRepository studentsRepository;
 * 
 * @PostMapping("/addStudent") public ResponseEntity<Students>
 * addStudent(@RequestBody Students students) { System.out.println("Students : "
 * + students); try { studentsRepository.save(students); return new
 * ResponseEntity<>(HttpStatus.CREATED); } catch (Exception e) { return new
 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
 * 
 * }
 * 
 * }
 */