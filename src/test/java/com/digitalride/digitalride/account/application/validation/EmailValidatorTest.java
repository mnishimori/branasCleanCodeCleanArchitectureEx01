package com.digitalride.digitalride.account.application.validation;

import static org.junit.jupiter.api.Assertions.*;

import com.digitalride.digitalride.account.testData.AccountTestData;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailValidatorTest {

  @Spy
  private EmailValidator emailValidator;

  @ParameterizedTest
  @ValueSource(strings = {AccountTestData.DEFAULT_ACCOUNT_EMAIL})
  void shouldValidateEmail(String email) {
    assertDoesNotThrow(() -> emailValidator.validate(email));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void shouldThrowsExceptionWhenEmailIsNullOrEmpty(String email) {
    assertThrows(RuntimeException.class, () -> emailValidator.validate(email));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "email.domain.com", "@", "1234"})
  void shouldThrowsExceptionWhenEmailIsInvalid(String email) {
    assertThrows(RuntimeException.class, () -> emailValidator.validate(email));
  }
}