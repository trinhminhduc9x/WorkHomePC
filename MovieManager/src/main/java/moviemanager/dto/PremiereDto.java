package moviemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import moviemanager.model.Movie;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PremiereDto implements Validator {


    private Integer idPremiere;


    @NotBlank
    private String dayPremiere;

    @NotBlank
    @Pattern(regexp = "[0-9]{2}",
            message = "số vé ko vượt quá 100 và có dạng XX")
    private String numberPremiere;


    private Movie movie;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
