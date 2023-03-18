package test.example.leader_it.annotations;

import test.example.leader_it.validators.SportTypeValidator;
import test.example.leader_it.validators.StringDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.PARAMETER, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = SportTypeValidator.class)
@Documented
public @interface SportTypeChecker {

    String message() default "{invalid value}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
