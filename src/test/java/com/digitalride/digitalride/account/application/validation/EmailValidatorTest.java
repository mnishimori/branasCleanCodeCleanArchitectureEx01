package com.digitalride.digitalride.account.application.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.digitalride.digitalride.account.testData.AccountTestData;
import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
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
  @ValueSource(strings = {"email.domain.com", "@", "1234"})
  void shouldThrowsExceptionWhenEmailIsNullOrEmpty(String email) {
    assertThrows(ValidatorException.class, () -> emailValidator.validate(email));
  }
}