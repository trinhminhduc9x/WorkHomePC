package com.furama.dto;

import com.furama.model.facility.FacilityType;
import com.furama.model.facility.RentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDto implements Validator {
    private Integer id;

    @NotBlank(message = "Fill in the blank")
    @Pattern(regexp = "^([A-Z][a-z0-9]+)(\\s[A-Z0-9][a-z0-9]+)*$",
            message = "Please input right format!")
    private String name;

    @Min(value = 1, message = "Price must be more than 0")
    private int area;

    @Min(value = 1, message = "Price must be more than 0")
    private double cost;

    @Min(value = 2, message = "Minimum: 2 people")
    @Max(value = 30, message = "Maximum: 30 people")
    private int maxPeople;

    private RentType rentType;

    private FacilityType facilityType;

    private String standardRoom;
    private String descriptionOtherConvenience;
    private double poolArea;

    private int numberOfFloors;

    private String facilityFree;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacilityDto facilityDto = (FacilityDto) target;
        if (facilityDto.numberOfFloors<0){
            errors.rejectValue("numberOfFloors",
                    "numberOfFloors.err",
                    "Number of floors must be positive");
        }else if ((facilityDto.numberOfFloors==0)&&(facilityDto.facilityType.getId()!=3)){
            errors.rejectValue("numberOfFloors",
                    "numberOfFloors.err",
                    "Number of floors must be more than 1");
        }
    }
}
