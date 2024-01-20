package com.digitalride.digitalride.account.presentation.api;

import com.digitalride.digitalride.account.application.usecase.CreateAccountUseCase;
import com.digitalride.digitalride.account.presentation.dto.AccountInputDto;
import com.digitalride.digitalride.account.presentation.dto.AccountOutputDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountApi {
  private final CreateAccountUseCase createAccountUseCase;

  public AccountApi(CreateAccountUseCase createAccountUseCase) {
    this.createAccountUseCase = createAccountUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountOutputDto postAccount(@RequestBody @Valid AccountInputDto accountInputDto) {
    var account = AccountInputDto.to(accountInputDto);
    var accountSaved = createAccountUseCase.execute(account);
    return AccountOutputDto.from(accountSaved);
  }
}
