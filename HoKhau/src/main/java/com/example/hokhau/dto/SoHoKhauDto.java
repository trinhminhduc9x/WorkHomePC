package com.example.hokhau.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoHoKhauDto implements Validator {


    private Integer id;



    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^[A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]+" +
            "( [A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]*)+$",
            message = "Input right format!")
    private String maHoKhau;


    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^[A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]+" +
            "( [A-ZĐ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâ]*)+$",
            message = "Input right format!")
    private String name;


    @NotBlank(message = "Fill in blank")
    @Pattern(regexp = "^[0-9]{3}$",
            message = "nhập đúng số người theo dạng xxx")
    private String numberPeople;

    private String dayStar;


    @NotBlank(message = "Fill in blank")
    private  String address;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SoHoKhauDto soHoKhauDto = (SoHoKhauDto) target;

        LocalDate dayStar = null;
        if (soHoKhauDto.dayStar!=null){
            try {
                dayStar = LocalDate.parse(soHoKhauDto.dayStar);
            }catch (Exception e){
                errors.rejectValue("dayStar", "create.dayStar",
                        "Fill in blank");
                return;
            }
            LocalDate now = LocalDate.now();
            int age = dayStar.until(now).getYears();
            if (age  < 0){
                errors.rejectValue("dayStar", "create.dayStar",
                        "nhập đúng ngày");
            }
        }
    }
}
