package com.digitalride.digitalride.account.application.validation;

import com.digitalride.digitalride.account.model.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class PassengerDriverValidator {

  public void validate(Account account) {
    if (account.getIsPassenger().equals(account.getIsDriver())) {
      throw new RuntimeException("Choose passenger or driver");
    }
  }
}
