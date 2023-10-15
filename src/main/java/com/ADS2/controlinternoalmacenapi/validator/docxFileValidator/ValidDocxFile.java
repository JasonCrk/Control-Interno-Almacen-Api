package com.ADS2.controlinternoalmacenapi.validator.docxFileValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DocxFileValidator.class)
@Documented
public @interface ValidDocxFile {
    String message() default "El archivo debe ser un documento .docx (Word)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
