package org.learn.simpl.service;

import java.util.List;
import java.util.Optional;

import org.learn.simpl.entity.Students;


public interface StudentsService {
   List<Students>  getAllStudents();
   Optional<Students> getStudent(int Id);
  void createStudent(Students students);
   
}
