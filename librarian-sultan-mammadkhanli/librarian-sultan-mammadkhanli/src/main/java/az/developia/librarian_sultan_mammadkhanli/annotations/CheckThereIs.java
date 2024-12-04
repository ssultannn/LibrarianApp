package az.developia.librarian_sultan_mammadkhanli.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CheckThereIsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckThereIs {
    String value() default "";
    String message() default "This username already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
