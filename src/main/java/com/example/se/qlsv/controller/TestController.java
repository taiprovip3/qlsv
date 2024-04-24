package com.example.se.qlsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private StudentService service;

	@GetMapping("/hello")
	public String helloWorld() {
		Student studentById = service.getStudentById(1);
		return "Hello World!";
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('STUDENT')")
	public String studentAccess() {
		return "Student content.";
	}
	
	@GetMapping("/teacher")
	@PreAuthorize("hasRole('TEACHER')")
	public String teacherAccess() {
		return "Teacher content.";
	}
	
	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER')")
	public String managerAccess() {
		return "Manager content.";
	}
}