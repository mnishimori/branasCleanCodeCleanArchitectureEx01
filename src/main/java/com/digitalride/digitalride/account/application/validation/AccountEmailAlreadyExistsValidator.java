package com.digitalride.digitalride.account.application.validation;

import com.digitalride.digitalride.account.model.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountEmailAlreadyExistsValidator {

  private final AccountService accountService;

  public AccountEmailAlreadyExistsValidator(AccountService accountService) {
    this.accountService = accountService;
  }

  public void validate(String email) {
    var accountFound = accountService.findByEmail(email);
    if (accountFound.isPresent()) {
      throw new RuntimeException("email already exists! " + email);
    }
  }
}
