package com.digitalride.digitalride.account.testData;

import com.digitalride.digitalride.account.model.entity.Account;
import java.util.UUID;

public final class AccountTestData {
  public static final UUID DEFAULT_ACCOUNT_UUID = UUID.randomUUID();
  public static final String DEFAULT_ACCOUNT_UUID_STRING = DEFAULT_ACCOUNT_UUID.toString();
  public static final String DEFAULT_ACCOUNT_EMAIL = "email@domail.com";
  public static final String DEFAULT_ACCOUNT_NAME = "Fulano de Tal";
  public static final String DEFAULT_ACCOUNT_CPF = "07427491009";
  public static final String DEFAULT_ACCOUNT_CAR_PLATE = "ABC1023";
  public static final String DEFAULT_ACCOUNT_IS_PASSENGER = "false";
  public static final String DEFAULT_ACCOUNT_IS_DRIVER = "true";

  public static final String ACCOUNT_TEMPLATE_INPUT = """
      { "email": "%s", "name": "%s", "cpf": "%s", "carPlate": "%s", "isPassenger": "%s", "isDriver": "%s"}""";

  public static final String ACCOUNT_INPUT = ACCOUNT_TEMPLATE_INPUT.formatted(
      DEFAULT_ACCOUNT_EMAIL, DEFAULT_ACCOUNT_NAME, DEFAULT_ACCOUNT_CPF, DEFAULT_ACCOUNT_CAR_PLATE,
      DEFAULT_ACCOUNT_IS_PASSENGER, DEFAULT_ACCOUNT_IS_DRIVER);

  public static Account createAccount() {
    var account = createNewAccount();
    account.setId(UUID.randomUUID());
    return account;
  }

  public static Account createNewAccount() {
    return Account.builder()
        .email(DEFAULT_ACCOUNT_EMAIL)
        .name(DEFAULT_ACCOUNT_NAME)
        .cpf(DEFAULT_ACCOUNT_CPF)
        .carPlate(DEFAULT_ACCOUNT_CAR_PLATE)
        .isPassenger(false)
        .isDriver(true)
        .build();
  }
}
