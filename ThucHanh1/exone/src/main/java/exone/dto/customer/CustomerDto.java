package exone.dto.customer;

import exone.model.customer.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Validator {
    private Integer id;

    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^[A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]+" +
            "( [A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]*)+$",
            message = "Input right format!")
    private String name;

    private String birthday;

    private int gender;

    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^\\d{9}$", message = "Input right format: 9 characters (0-9)")
    private String identifyCard;

    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^((090)|(091)|(\\(84\\)\\+9([01])))[0-9]{7}$",
            message = "Input Vietnamese phone number")
    private String phoneNumber;

    @NotBlank(message = "Fill in blank")
    @Email(message = "Input right format: example123@example.com")
    private String email;

    private CustomerType customerType;

    @NotBlank(message = "Fill in blank")
    private String address;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;

        LocalDate birthday = null;
        if (customerDto.birthday!=null){
            try {
                birthday = LocalDate.parse(customerDto.birthday);
            }catch (Exception e){
                errors.rejectValue("birthday", "create.birthday",
                        "Fill in blank");
                return;
            }
            LocalDate now = LocalDate.now();
            int age = birthday.until(now).getYears();
            if (age < 18){
                errors.rejectValue("birthday", "create.birthday",
                        "Must be more than 18 years old");
            }
        }
    }
}
