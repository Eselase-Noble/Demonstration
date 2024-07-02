package org.nobleson.demonstration.logics;

import org.nobleson.demonstration.models.Student;

import java.util.List;

public interface StudentService {
   List<Student> getAllStudents();
   Student getStudentById(int id);
   Student addStudent(Student student);
   Student updateStudent(Student student);
   void deleteStudent(int id);
}
