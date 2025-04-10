package com.wcrs.employee.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_seq")
    @SequenceGenerator(sequenceName = "emp_seq",name = "emp_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String NIN;
    //Implement a minio object store for storage in future improvements
    //private blob passportPhoto;

    public String getFullName(){
        return firstName +" " + lastName;
    }

    public int getAge(){
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }
}
