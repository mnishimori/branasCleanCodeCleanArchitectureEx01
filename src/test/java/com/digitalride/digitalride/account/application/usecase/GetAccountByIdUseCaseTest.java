package com.digitalride.digitalride.account.application.usecase;

import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_UUID;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_UUID_STRING;
import static com.digitalride.digitalride.account.testData.AccountTestData.createAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.digitalride.digitalride.account.model.service.AccountService;
import com.digitalride.digitalride.shared.presentation.exception.NoResultException;
import com.digitalride.digitalride.shared.validator.UuidValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAccountByIdUseCaseTest {

  @Mock
  private AccountService accountService;
  @Mock
  private UuidValidator uuidValidator;
  @InjectMocks
  private GetAccountByIdUseCase getAccountByIdUseCase;

  @Test
  void shouldFindAccountByIdWhenAccountExists() {
    var account = createAccount();
    when(accountService.findByIdRequired(account.getId())).thenReturn(account);

    var accountFound = getAccountByIdUseCase.execute(account.getId().toString());

    assertThat(accountFound).isNotNull();
    assertThat(accountFound).usingRecursiveComparison().isEqualTo(account);
    verify(uuidValidator).validate(account.getId().toString());
  }

  @Test
  void shouldThrowExceptionWhenAccountDoesNotExist() {
    when(accountService.findByIdRequired(DEFAULT_ACCOUNT_UUID)).thenCallRealMethod();

    assertThrows(NoResultException.class,
        () -> getAccountByIdUseCase.execute(DEFAULT_ACCOUNT_UUID_STRING));
  }
}