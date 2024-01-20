package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_EMAIL_DUPLICATED;

import com.digitalride.digitalride.account.model.service.AccountService;
import com.digitalride.digitalride.shared.presentation.exception.DuplicatedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class AccountEmailAlreadyExistsValidator {

  private final AccountService accountService;

  public AccountEmailAlreadyExistsValidator(AccountService accountService) {
    this.accountService = accountService;
  }

  public void validate(String email) {
    var accountFound = accountService.findByEmail(email);
    if (accountFound.isPresent()) {
      throw new DuplicatedException(new FieldError(this.getClass().getSimpleName(), "email",
          ACCOUNT_EMAIL_DUPLICATED.formatted(email)));
    }
  }
}
