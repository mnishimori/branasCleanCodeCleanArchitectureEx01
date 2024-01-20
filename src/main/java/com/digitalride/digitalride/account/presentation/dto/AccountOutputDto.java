package com.digitalride.digitalride.account.presentation.dto;

import com.digitalride.digitalride.account.model.entity.Account;

public record AccountOutputDto(
    String id,
    String email,
    String name,
    String cpf,
    String carPlate,
    String isPassenger,
    String isDriver) {

  public AccountOutputDto(Account account) {
    this(account.getId() != null ? account.getId().toString() : null,
        account.getEmail(),
        account.getName(),
        account.getCpf(),
        account.getCarPlate(),
        account.getIsPassenger() != null ? account.getIsPassenger().toString() : "false",
        account.getIsDriver() != null ? account.getIsDriver().toString() : "false");
  }

  public static AccountOutputDto from(Account accountSaved) {
    return new AccountOutputDto(accountSaved);
  }
}
