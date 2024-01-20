package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.testData.AccountTestData.createAccount;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PassengerDriverValidatorTest {

  @Spy
  private PassengerDriverValidator passengerDriverValidator;

  @Test
  void shouldValidatePassengerDriver() {
    var account = createAccount();
    assertDoesNotThrow(() -> passengerDriverValidator.validate(account));
  }

  @Test
  void shouldThrowExceptionWhenPassengerDriverIsInvalid() {
    var account = createAccount();
    account.setIsPassenger(true);
    account.setIsDriver(true);
    assertThrows(ValidatorException.class, () -> passengerDriverValidator.validate(account));
  }
}