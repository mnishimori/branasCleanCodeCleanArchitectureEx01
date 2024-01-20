package com.digitalride.digitalride.account.application.usecase;

import static com.digitalride.digitalride.account.testData.AccountTestData.createAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.digitalride.digitalride.account.application.validation.AccountEmailAlreadyExistsValidator;
import com.digitalride.digitalride.account.application.validation.AccountNameValidator;
import com.digitalride.digitalride.account.application.validation.CarPlateValidator;
import com.digitalride.digitalride.account.application.validation.CpfValidator;
import com.digitalride.digitalride.account.application.validation.EmailValidator;
import com.digitalride.digitalride.account.application.validation.PassengerDriverValidator;
import com.digitalride.digitalride.account.model.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {
  @Mock
  private AccountService accountService;
  @Mock
  private AccountNameValidator accountNameValidator;
  @Mock
  private EmailValidator emailValidator;
  @Mock
  private AccountEmailAlreadyExistsValidator accountEmailAlreadyExistsValidator;
  @Mock
  private CpfValidator cpfValidator;
  @Mock
  private CarPlateValidator carPlateValidator;
  @Mock
  private PassengerDriverValidator passengerDriverValidator;
  @InjectMocks
  private CreateAccountUseCase createAccountUseCase;

  @Test
  void shouldCreateAnNewAccount() {
    var accountToSave = createAccount();
    when(accountService.save(accountToSave)).then(returnsFirstArg());

    var accountSaved = createAccountUseCase.execute(accountToSave);

    assertThat(accountSaved).isNotNull();
    assertThat(accountSaved.getId()).isNotNull();
    assertThat(accountSaved).usingRecursiveComparison().isEqualTo(accountToSave);
    verify(accountNameValidator).validate(accountToSave.getName());
    verify(emailValidator).validate(accountToSave.getEmail());
    verify(accountEmailAlreadyExistsValidator).validate(accountToSave.getEmail());
    verify(cpfValidator).validate(accountToSave.getCpf());
    verify(carPlateValidator).validate(accountToSave.getCarPlate());
    verify(passengerDriverValidator).validate(accountToSave);
  }
}