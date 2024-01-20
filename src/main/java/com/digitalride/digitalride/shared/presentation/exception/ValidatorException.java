package com.digitalride.digitalride.shared.presentation.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ValidatorException extends RuntimeException {

  private final FieldError fieldError;

  public ValidatorException(FieldError fieldError) {
    super(fieldError.getDefaultMessage());
    this.fieldError = fieldError;
  }
}
