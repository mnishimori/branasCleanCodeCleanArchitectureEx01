package com.digitalride.digitalride.account.model.service;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_ID_NOT_FOUND;

import com.digitalride.digitalride.account.infrastructure.repository.AccountRepository;
import com.digitalride.digitalride.account.model.entity.Account;
import com.digitalride.digitalride.shared.presentation.exception.NoResultException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Optional<Account> findByEmail(String email) {
    return accountRepository.findByEmail(email);
  }

  public Optional<Account> findById(UUID uuid) {
    return accountRepository.findById(uuid);
  }

  public Account findByIdRequired(UUID uuid) {
    return findById(uuid).orElseThrow(
        () -> new NoResultException(new FieldError(this.getClass().getSimpleName(), "User",
            ACCOUNT_ID_NOT_FOUND.formatted(uuid))));
  }
}
