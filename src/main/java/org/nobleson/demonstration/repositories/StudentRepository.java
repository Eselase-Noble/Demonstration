package org.nobleson.demonstration.repositories;

import org.nobleson.demonstration.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByFirstNameAndEmail(String firstName, String email);
    Student findByLastName(String lastName);

    Student deleteByStudentID(Long studentID);
    Optional<Student> findByEmail(String email);
}
