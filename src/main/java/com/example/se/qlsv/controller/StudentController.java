package com.example.se.qlsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/findById/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
		Student student = service.getStudentById(1);
		System.out.println("student=" + student);
		return ResponseEntity.ok(student);
	}
}
