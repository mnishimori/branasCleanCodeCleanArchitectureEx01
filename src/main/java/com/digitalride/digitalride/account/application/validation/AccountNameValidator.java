package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_NAME_INVALID;

import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class AccountNameValidator {

  public void validate(String name) {
    var namePattern = Pattern.compile(".*[a-zA-Z].*");
    var nameMatcher = namePattern.matcher(name);
    if (!nameMatcher.matches()) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "name",
          ACCOUNT_NAME_INVALID.formatted(name)));
    }
  }
}
