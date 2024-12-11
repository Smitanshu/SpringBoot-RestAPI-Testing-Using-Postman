package com.Smitanshu.SpringBootRestAPI.SpringBootRestAPIProject.repository;

import com.Smitanshu.SpringBootRestAPI.SpringBootRestAPIProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {


}
