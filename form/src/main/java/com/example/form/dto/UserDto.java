package com.example.form.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

public class UserDto implements Validator {
    private Integer id;
    @NotBlank(message = "Please enter !")
    @Size(min = 5, max = 45)
    private String firstName;
    @NotBlank(message = "Please enter !")
    @Size(min = 5, max = 45)
    private String lastName;
    @Pattern(regexp = "(091|090)[0-9]{6}", message = "ex:091XXXXXX or 090XXXXXX")
    private String phoneNumber;
    @NotNull
    private String age;
    @Email(message = "Please enter email")
    private String email;

    public UserDto() {
    }

    public UserDto(Integer id, String firstName, String lastName, String phoneNumber, String age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public UserDto(String firstName, String lastName, String phoneNumber, String age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        LocalDate now = LocalDate.now();
        if (userDto.getAge() != null && !userDto.getAge().equals("")) {
            LocalDate age = LocalDate.parse(userDto.getAge());
            int space = Period.between(age, now).getYears();
            if (space < 18) {
                errors.rejectValue("age", "date", " Age must be over 18");
            }
        } else {
            errors.rejectValue("age", "date", "Please enter Age !");
        }
    }
}
