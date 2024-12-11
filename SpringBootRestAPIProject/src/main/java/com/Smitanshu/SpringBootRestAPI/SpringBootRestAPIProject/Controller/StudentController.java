package com.Smitanshu.SpringBootRestAPI.SpringBootRestAPIProject.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.Smitanshu.SpringBootRestAPI.SpringBootRestAPIProject.entity.Student;
import com.Smitanshu.SpringBootRestAPI.SpringBootRestAPIProject.repository.StudentRepository; // Ensure this import exists

@RestController
public class StudentController {

    @Autowired
    StudentRepository repo;

    // Get all the students data
    // localhost:8080/students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        List<Student> students = repo.findAll(); // This is the correct usage
        return students;
    }

    //localhost:8080/students/1
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        return student;
    }

    @PostMapping("students/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        repo.save(student);
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudents(@PathVariable int id) {
        Student student = repo.findById(id).get();
        student.setName("Poonam");
        student.setPercentage(75);
        repo.save(student);
        return student;
    }

    @DeleteMapping("/student/delete/{id}")
    public void removeStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        repo.delete(student);
    }



    // Update or Add student by ID
    @PutMapping("student/add/byid/{id}")
    public ResponseEntity<Student> addOrUpdateStudentById(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> existingStudent = repo.findById(id);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setName(student.getName());
            updatedStudent.setPercentage(student.getPercentage());
            updatedStudent.setBranch(student.getBranch());
            repo.save(updatedStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            student.setRollNo(id);
            repo.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
    }

}
