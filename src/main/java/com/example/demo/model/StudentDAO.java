package com.example.demo.model;


import org.springframework.data.repository.CrudRepository;

public interface StudentDAO extends CrudRepository<Student, Long> {

}