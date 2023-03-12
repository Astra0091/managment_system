package org.learn.simpl.controller;

import java.util.List;

import org.learn.simpl.entity.Students;
import org.learn.simpl.repository.StudentsRepository;
import org.learn.simpl.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "org.learn.simpl")
public class StudentsController {

	@Autowired(required = true)
	private StudentsRepository repository;
	@Autowired
	public SequenceGeneratorService sequencegenerator;
	/*
	 * @Autowired(required = false) private StudentsService service;
	 */
	/*
	 * public StudentsController(StudentsService studentsService) { super();
	 * this.studentsService=studentsService; }
	 */

	@GetMapping("/students")
	public String getAllStudentsList(Model model) {
		try {
			List<Students> allStudents = repository.findAll();
			model.addAttribute("students", allStudents);
			if (!allStudents.isEmpty()) {
				//allStudents.forEach(student -> System.out.println("getAllStudentsList >> " + student.toString()));
			} else {
				System.out.println("Empty Student>>>>>>>>>>");
			}
			return "students";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudent(Model model) {
		Students students = new Students();
		model.addAttribute("student", students);
		return "create_student";

	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Students student) {

			long generateSequence = sequencegenerator.generateSequence(Students.SEQUENCE_NAME);
			System.out.println("Before Save generateSequence---->>>>>"+generateSequence);
			
			student.setId(generateSequence);
			
			if(student.getId()==0) {
				System.out.println("****************");
				student.setId((int) sequencegenerator.getNextSequenceId(Students.SEQUENCE_NAME));

			}
			try {
                	repository.save(student);
			System.out.println("After Save ---***->>>>>"+student);
		}catch(Exception e) {
		e.getStackTrace();
		}
		return "redirect:/api/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable ("id") int id, Model model) {
		model.addAttribute("student", repository.findById(id).get());
		System.out.println("edit Student object >>>  " + repository.findById(id).get());

		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable("id") int id, 
			@ModelAttribute("student") Students student, 
			Model model) {
		// GetStudent from DB by ID
		// Optional<Students> findById = repository.findById(id).get();
		//student.setId(id);
		
		Students students = repository.findById(id).get();
		System.out.println("Before update Student>>>>  "+students.getFirstName());
		if (students != null) {
			//students.setId(student.getId());
			students.setFirstName(student.getFirstName());
			students.setLasttName(student.getLasttName());
			students.setEmaiId(student.getEmaiId());
			students.setMobileNo(student.getMobileNo());
			
			// Save updated Student Obj
			Students save = repository.save(students);
			
			System.out.println(" After update Student>>>>>>>>>>>>>>"+save);
		}
		return "redirect:/api/students";
	}

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable("id") Integer id) {
		// GetStudent from DB by ID

		try {
			
			Students std = repository.findById(id).get();
			System.out.println("delete Student Name:  "+std.getFirstName());
			
			if (std != null) {				
			// Save delete Student Obj
			   repository.delete(std);
				//repository.deleteAll();
			}
		}catch(Exception e) {
			System.out.println("Exception  deleteStudent>>>>  "+e.getMessage());
		}
		
		
		return "redirect:/api/students";
	}

}
