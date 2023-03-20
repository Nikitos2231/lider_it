package test.example.leader_it.validators;

import org.springframework.stereotype.Component;
import test.example.leader_it.annotations.StringDatePattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Component
public class StringDateValidator implements ConstraintValidator<StringDatePattern, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", s)) {
            try {
                LocalDate.parse(s.replaceAll("-", ""), DateTimeFormatter.BASIC_ISO_DATE);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
