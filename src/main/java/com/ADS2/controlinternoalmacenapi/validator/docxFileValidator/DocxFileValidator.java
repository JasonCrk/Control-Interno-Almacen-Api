package com.ADS2.controlinternoalmacenapi.validator.docxFileValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class DocxFileValidator implements ConstraintValidator<ValidDocxFile, MultipartFile> {

    @Override
    public void initialize(ValidDocxFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) return false;

        String fileName = multipartFile.getOriginalFilename();
        return fileName != null && fileName.endsWith(".docx");
    }
}
