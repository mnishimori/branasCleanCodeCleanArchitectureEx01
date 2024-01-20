package com.digitalride.digitalride.account.model.service;

import com.digitalride.digitalride.account.infrastructure.repository.AccountRepository;
import com.digitalride.digitalride.account.model.entity.Account;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
}
