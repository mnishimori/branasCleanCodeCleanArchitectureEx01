package com.digitalride.digitalride.account.model.service;

import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_EMAIL;
import static com.digitalride.digitalride.account.testData.AccountTestData.DEFAULT_ACCOUNT_UUID;
import static com.digitalride.digitalride.account.testData.AccountTestData.createAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

import com.digitalride.digitalride.account.infrastructure.repository.AccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;
  @InjectMocks
  private AccountService accountService;

  @Test
  void shouldSaveAccount() {
    var accountToSave = createAccount();
    when(accountRepository.save(accountToSave)).then(returnsFirstArg());

    var accountSaved = accountService.save(accountToSave);

    assertThat(accountSaved).isNotNull();
    assertThat(accountSaved.getId()).isNotNull();
    assertThat(accountSaved).usingRecursiveComparison().isEqualTo(accountToSave);
  }

  @Test
  void shouldFindAccountByEmailWhenAccountExists() {
    var account = createAccount();
    when(accountRepository.findByEmail(account.getEmail())).thenReturn(Optional.of(account));

    var accountFound = accountService.findByEmail(account.getEmail()).orElse(null);

    assertThat(accountFound).isNotNull();
    assertThat(accountFound).usingRecursiveComparison().isEqualTo(account);
  }

  @Test
  void shouldNotFindAccountByEmailWhenAccountDoesNotExist() {
    when(accountRepository.findByEmail(DEFAULT_ACCOUNT_EMAIL)).thenReturn(Optional.empty());

    var accountFound = accountService.findByEmail(DEFAULT_ACCOUNT_EMAIL).orElse(null);

    assertThat(accountFound).isNull();
  }

  @Test
  void shouldFindAccountByIdWhenAccountExists() {
    var account = createAccount();
    when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

    var accountFound = accountService.findById(account.getId()).orElse(null);

    assertThat(accountFound).isNotNull();
    assertThat(accountFound).usingRecursiveComparison().isEqualTo(account);
  }

  @Test
  void shouldNotFindAccountByIdWhenAccountDoesNotExist() {
    when(accountRepository.findById(DEFAULT_ACCOUNT_UUID)).thenReturn(Optional.empty());

    var accountFound = accountService.findById(DEFAULT_ACCOUNT_UUID).orElse(null);

    assertThat(accountFound).isNull();
  }
}
