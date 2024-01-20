package com.digitalride.digitalride.account.infrastructure.repository;

import com.digitalride.digitalride.account.model.entity.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByEmail(String email);
}
