package test.example.leader_it.validators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import test.example.leader_it.annotations.SportTypeChecker;
import test.example.leader_it.models.SportType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Component
public class SportTypeValidator implements ConstraintValidator<SportTypeChecker, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && (!s.trim().isEmpty() && Arrays.stream(SportType.values()).anyMatch(e -> e.name().equals(s)));
    }

}
