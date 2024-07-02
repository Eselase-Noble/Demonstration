package org.nobleson.demonstration.logics;


import lombok.RequiredArgsConstructor;
import org.nobleson.demonstration.models.Student;
import org.nobleson.demonstration.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is responsible handling all the students logics
 */

@Service
@RequiredArgsConstructor
public class StudentLogic implements StudentService{
    private final StudentRepository studentRepository;


    //Select * from Student;
    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    //Insert into Student (studentid, first_name, last_name, email) values(?,?,?,?);
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * update student set firstName = {value} where studentID = {value1};
     * @param student
     * @return
     */
    public Student updateStudent(Student student) {
        if (!studentRepository.existsById(student.getStudentID())) {
            return null;
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {

    }

    /**
     * Delete from student where studentID = {value};
     * @param studentID
     * @return
     */
    public void deleteStudent(Long studentID) {

        Student student = studentRepository.findById(studentID).get();

        studentRepository.delete(student);
    }
}
