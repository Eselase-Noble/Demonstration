package org.nobleson.demonstration.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    //Declaring the various attributes of the Student
    @Id
    private Long studentID;
    private String firstName;
    private String lastName;
    private String email;

//    public Student(String firstName){
//        this.firstName = firstName;
//    }
//    public Student(){}
//    public Student(String firstName, String lastName, String email){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//    public Student(Long studentID, String firstName, String lastName, String email) {
//        this.studentID = studentID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//
//    public Long getStudentID() {
//        return this.studentID;
//    }
//
//    public void setStudentID(Long studentID) {
//        this.studentID = studentID;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
