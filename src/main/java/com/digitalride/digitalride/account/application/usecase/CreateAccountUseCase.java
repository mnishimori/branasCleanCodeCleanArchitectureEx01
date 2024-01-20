package com.digitalride.digitalride.account.application.usecase;

import com.digitalride.digitalride.account.application.validation.AccountEmailAlreadyExistsValidator;
import com.digitalride.digitalride.account.application.validation.AccountNameValidator;
import com.digitalride.digitalride.account.application.validation.CarPlateValidator;
import com.digitalride.digitalride.account.application.validation.CpfValidator;
import com.digitalride.digitalride.account.application.validation.EmailValidator;
import com.digitalride.digitalride.account.application.validation.PassengerDriverValidator;
import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.account.model.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateAccountUseCase {
  private final AccountService accountService;
  private final AccountNameValidator accountNameValidator;
  private final EmailValidator emailValidator;
  private final CpfValidator cpfValidator;
  private final CarPlateValidator carPlateValidator;
  private final PassengerDriverValidator passengerDriverValidator;
  private final AccountEmailAlreadyExistsValidator accountEmailAlreadyExistsValidator;

  public CreateAccountUseCase(AccountService accountService,
      AccountEmailAlreadyExistsValidator accountEmailAlreadyExistsValidator, AccountNameValidator accountNameValidator,
      EmailValidator emailValidator, CpfValidator cpfValidator, CarPlateValidator carPlateValidator,
      PassengerDriverValidator passengerDriverValidator) {
    this.accountService = accountService;
    this.accountNameValidator = accountNameValidator;
    this.emailValidator = emailValidator;
    this.accountEmailAlreadyExistsValidator = accountEmailAlreadyExistsValidator;
    this.cpfValidator = cpfValidator;
    this.carPlateValidator = carPlateValidator;
    this.passengerDriverValidator = passengerDriverValidator;
  }

  @Transactional
  public Account execute(Account account) {
    validate(account);
    return accountService.save(account);
  }

  private void validate(Account account) {
    accountNameValidator.validate(account.getName());
    emailValidator.validate(account.getEmail());
    accountEmailAlreadyExistsValidator.validate(account.getEmail());
    cpfValidator.validate(account.getCpf());
    carPlateValidator.validate(account.getCarPlate());
    passengerDriverValidator.validate(account);
  }
}
