package com.wcrs.employee.model;


import com.wcrs.employee.enums.CountryCode;
import com.wcrs.employee.enums.PhoneCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "employee")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(nullable = false, unique = true,length = 9)
    private String number;

    @Column(nullable = false)
    private PhoneCategory phoneCategory;

    private CountryCode countryCode;
}
