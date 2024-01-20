package com.digitalride.digitalride.account.application.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountNameValidatorTest {

  @Spy
  private AccountNameValidator accountNameValidator;

  @ParameterizedTest
  @ValueSource(strings = {"Fulano de Tal", "Ciclano", "Beltrano"})
  void shouldValidateNameWhenNameHasLowerAndUpperCharacters(String name) {
    assertDoesNotThrow(() -> accountNameValidator.validate(name));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"1", "123@", "@"})
  void shouldThrowsExceptionWhenNameIsNullOrEmpty(String name) {
    assertThrows(RuntimeException.class, () -> accountNameValidator.validate(name));
  }
}