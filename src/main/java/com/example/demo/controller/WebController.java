package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDAO;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;

@Slf4j
@Controller
public class WebController {
	
	@Autowired
	AccessToken accessToken;
	
    @Autowired
    private StudentDAO studentDAO;
    
	@PostConstruct
	// add students for demonstration
    public void addStudents() {

        Student student1 = new Student();
        student1.setId(101);
        student1.setAddress("Texas");
        student1.setName("Alice");
        student1.setGrade("B");
        studentDAO.save(student1);

        Student student2 = new Student();
        student2.setId(102);
        student2.setAddress("NY");
        student2.setName("Rohan");
        student2.setGrade("A");
        studentDAO.save(student2);
    }
	
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public @ResponseBody Iterable<Student> getStudents() {
    	log.info("getStudents: Retreiving students from db by user : {}",accessToken.getPreferredUsername());
        Iterable<Student> students = studentDAO.findAll();
           return students;
    }
    

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public @ResponseBody Student getStudentById(@PathVariable("id") long id) {
    	log.info("getStudentById: Retreiving student with id {} from db by user : {} ",id,accessToken.getPreferredUsername());
    	Optional<Student> student = studentDAO.findById(id);
        return student.get();
    }
    
   
    
}
