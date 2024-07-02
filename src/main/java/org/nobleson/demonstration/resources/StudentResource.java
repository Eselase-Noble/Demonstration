package org.nobleson.demonstration.resources;


import lombok.RequiredArgsConstructor;
import org.nobleson.demonstration.logics.StudentLogic;
import org.nobleson.demonstration.models.Student;
import org.nobleson.demonstration.repositories.StudentRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for handing the APIs for the student entity
 */

@Controller
@RequestMapping("/domonstration/api/student")
@RequiredArgsConstructor

public class StudentResource {
    private final StudentLogic studentLogic;
private final StudentRepository studentRepository;

    //GET API for getting all the students
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        //studentRepository.findAll();
        return new ResponseEntity<>(studentLogic.getAllStudents(), HttpStatus.OK);
    }

    //POST API for adding student
    @PostMapping("/add")
    public ResponseEntity<Student>  addStudent(@RequestBody Student student) {

        Student newStudent = studentLogic.addStudent(student);
        return new ResponseEntity(newStudent, HttpStatus.CREATED);
    }

    //PUT API for updating student's details
    @PutMapping("/update")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student updatedStudent = studentLogic.updateStudent(student);
        return new ResponseEntity(updatedStudent, HttpStatus.OK);
    }

    //delete from student where studentid = studentID;
    @DeleteMapping("/delete/{studentID}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentID") Long studentID) {

        studentLogic.deleteStudent(studentID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
