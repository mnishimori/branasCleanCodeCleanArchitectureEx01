package com.digitalride.digitalride.account.presentation.dto;

import com.digitalride.digitalride.account.model.entity.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AccountInputDto(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String name,
    @NotBlank
    @CPF
    String cpf,
    String carPlate,
    @NotNull
    Boolean isPassenger,
    @NotNull
    Boolean isDriver) {

  public static Account to(AccountInputDto accountInputDto) {
    return Account.builder()
        .email(accountInputDto.email)
        .name(accountInputDto.name)
        .cpf(accountInputDto.cpf)
        .carPlate(accountInputDto.carPlate)
        .isPassenger(accountInputDto.isPassenger)
        .isDriver(accountInputDto.isDriver)
        .build();
  }
}
