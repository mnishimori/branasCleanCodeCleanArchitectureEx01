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
class CpfValidatorTest {

  @Spy
  private CpfValidator cpfValidator;

  @ParameterizedTest
  @ValueSource(strings = {AccountTestData.DEFAULT_ACCOUNT_CPF})
  void shouldValidateCpf(String cpf) {
    assertDoesNotThrow(() -> cpfValidator.validate(cpf));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"abc", "12345678901", "1234", "11111111111", "22222222222",
      "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888",
      "99999999999", "00000000000" })
  void shouldThrowsExceptionWhenCpfIsNullOrEmpty(String cpf) {
    assertThrows(ValidatorException.class, () -> cpfValidator.validate(cpf));
  }
}