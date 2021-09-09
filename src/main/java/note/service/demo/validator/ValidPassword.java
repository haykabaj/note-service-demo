package note.service.demo.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must be equal to or greater than 8 characters and less than 16 characters and contains minimum 1 special, 1 uppercase and 1 digit.";

}
