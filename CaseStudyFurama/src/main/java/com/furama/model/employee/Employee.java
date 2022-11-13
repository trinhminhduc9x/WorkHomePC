package com.furama.model.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.furama.model.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String birthday;
    private String idCard;
    private double salary;
    private String phoneNumber;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "position", referencedColumnName = "id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "education", referencedColumnName = "id")
    private Education education;

    @ManyToOne
    @JoinColumn(name = "division", referencedColumnName = "id")
    private Division division;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    @ManyToOne
    @JoinColumn(name = "user_name",referencedColumnName = "username")
    private User user;
}
