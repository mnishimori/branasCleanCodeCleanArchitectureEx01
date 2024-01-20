package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.testData.AccountTestData.createAccount;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.digitalride.digitalride.account.model.service.AccountService;
import com.digitalride.digitalride.shared.presentation.exception.DuplicatedException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountEmailAlreadyExistsValidatorTest {

  @Mock
  private AccountService accountService;
  @InjectMocks
  private AccountEmailAlreadyExistsValidator accountEmailAlreadyExistsValidator;

  @Test
  void shouldValidateWhenEmailDoesNotExist() {
    var account = createAccount();
    when(accountService.findByEmail(account.getEmail())).thenReturn(Optional.empty());

    assertDoesNotThrow(() -> accountEmailAlreadyExistsValidator.validate(account.getEmail()));
  }

  @Test
  void shouldThrowsExceptionWhenEmailAlreadyExists() {
    var account = createAccount();
    when(accountService.findByEmail(account.getEmail())).thenReturn(Optional.of(account));

    assertThrows(DuplicatedException.class, () -> accountEmailAlreadyExistsValidator.validate(account.getEmail()));
  }
}
