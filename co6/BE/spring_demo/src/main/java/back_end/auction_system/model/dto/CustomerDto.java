package case_study.furama_resort.model.dto;

import case_study.furama_resort.model.customer.CustomerType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomerDto implements Validator {
    private int id;

    @Pattern(regexp = "^\\p{Lu}\\p{Ll}+(\\s\\p{Lu}\\p{Ll}+)*$", message = "Tên chưa đúng định dạng! (Vd: Hoàng Giang)")
    private String name;

    private String dateOfBirth;
    private Integer gender = 0;

    @Pattern(regexp = "^[1-9]((\\d{8})|(\\d{11}))$", message = "CMND gồm 9 hoặc 12 số")
    private String idCard;

    @Pattern(regexp = "^(090\\d{6})$", message = "Số điện thoại gồm 9 số bắt đầu là 090 (Vd: 090123456)")
    private String phoneNumber;

    @NotBlank(message = "Email không được rỗng")
    private String email;

    @NotBlank(message = "Địa chỉ không được rỗng")
    private String address;
    private int status = 1;

    private CustomerType customerType;

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(customerDto.getDateOfBirth(), fmt);
        } catch (DateTimeParseException e) {
            errors.rejectValue("dateOfBirth", "", "Ngày sinh không đúng định dạng DD/mm/YYYY hoặc không đúng");
        }
    }


    public CustomerDto() {
    }

    public CustomerDto(String name, String dateOfBirth, int gender, String idCard, String phoneNumber, String email, String address, int status) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public void dateFormat() {
        DateTimeFormatter fmtyyyymmdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter fmtddmmyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(this.dateOfBirth, fmtyyyymmdd);
        this.dateOfBirth = date.format(fmtddmmyyyy);
    }

    public CustomerDto(int id, String name, String dateOfBirth, int gender, String idCard, String phoneNumber, String email, String address, int status, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.status = status;
        this.customerType = customerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


}
