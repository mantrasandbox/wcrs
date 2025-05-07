package com.wcrs.employee.model;

import com.wcrs.employee.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_seq")
    @SequenceGenerator(sequenceName = "emp_seq",name = "emp_seq", allocationSize = 1)
    @Column(name = "emp_id")
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "employee")
    private List<Phone> phone;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    //Implement for createdBy after user-management implementation

    //Implement a minio object store for storage in future improvements
    //private blob passportPhoto;

    public String getFullName(){
        return firstName +" " + lastName;
    }

    public int getAge(){
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }
}
