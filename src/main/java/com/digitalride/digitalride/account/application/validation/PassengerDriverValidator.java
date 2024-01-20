package com.digitalride.digitalride.account.application.validation;

import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class PassengerDriverValidator {

  public void validate(Account account) {
    if (account.getIsPassenger().equals(account.getIsDriver())) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "",
          "Choose driver or passenger."));
    }
  }
}
