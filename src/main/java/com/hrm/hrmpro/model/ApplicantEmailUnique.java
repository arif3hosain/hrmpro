package com.hrm.hrmpro.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.hrm.hrmpro.service.ApplicantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the email value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ApplicantEmailUnique.ApplicantEmailUniqueValidator.class
)
public @interface ApplicantEmailUnique {

    String message() default "{Exists.applicant.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ApplicantEmailUniqueValidator implements ConstraintValidator<ApplicantEmailUnique, String> {

        private final ApplicantService applicantService;
        private final HttpServletRequest request;

        public ApplicantEmailUniqueValidator(final ApplicantService applicantService,
                final HttpServletRequest request) {
            this.applicantService = applicantService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(applicantService.get(Long.parseLong(currentId)).getEmail())) {
                // value hasn't changed
                return true;
            }
            return !applicantService.emailExists(value);
        }

    }

}
