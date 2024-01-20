package com.digitalride.digitalride.shared.validator;

import static com.digitalride.digitalride.shared.model.message.UuidMessage.UUID_INVALID;

import com.digitalride.digitalride.shared.presentation.IsUUID;
import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class UuidValidator {

  public void validate(String uuid) {
    if (!IsUUID.isUUID().matches(uuid)) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "UUID",
          UUID_INVALID.formatted(uuid)));
    }
  }
}
