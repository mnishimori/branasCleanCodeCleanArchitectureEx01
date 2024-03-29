package com.digitalride.digitalride.account.application.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.digitalride.digitalride.account.testData.AccountTestData;
import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarPlateValidatorTest {

  @Spy
  private CarPlateValidator carPlateValidator;

  @ParameterizedTest
  @ValueSource(strings = {AccountTestData.DEFAULT_ACCOUNT_CAR_PLATE, "ABC1A12"})
  void shouldValidateCarPlate(String carPlate) {
    assertDoesNotThrow(() -> carPlateValidator.validate(carPlate));
  }

  @ParameterizedTest
  @ValueSource(strings = {"email@domain.com", "@", "1234", "AB-1234", "AB1234"})
  void shouldThrowsExceptionWhenCarPlateIsInvalid(String carPlate) {
    assertThrows(ValidatorException.class, () -> carPlateValidator.validate(carPlate));
  }
}