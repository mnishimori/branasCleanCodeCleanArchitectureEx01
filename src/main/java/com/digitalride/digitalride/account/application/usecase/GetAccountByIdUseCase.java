package com.digitalride.digitalride.account.application.usecase;

import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.account.model.service.AccountService;
import com.digitalride.digitalride.shared.validator.UuidValidator;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GetAccountByIdUseCase {

  private final AccountService accountService;
  private final UuidValidator uuidValidator;

  public GetAccountByIdUseCase(AccountService accountService, UuidValidator uuidValidator) {
    this.accountService = accountService;
    this.uuidValidator = uuidValidator;
  }

  public Account execute(String uuid) {
    uuidValidator.validate(uuid);
    return accountService.findByIdRequired(UUID.fromString(uuid));
  }
}
