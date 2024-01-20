package com.digitalride.digitalride.shared.presentation.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class DuplicatedException extends RuntimeException {

  private final FieldError fieldError;

  public DuplicatedException(FieldError fieldError) {
    super(fieldError.getDefaultMessage());
    this.fieldError = fieldError;
  }
}
