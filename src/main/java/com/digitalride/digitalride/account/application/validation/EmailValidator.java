package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_EMAIL_INVALID;

import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class EmailValidator {

  public void validate(String emailAddress) {
    if (emailAddress == null || emailAddress.isEmpty()) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "email",
          ACCOUNT_EMAIL_INVALID.formatted(emailAddress)));
    }
    var emailPattern = Pattern.compile("^(.+)@(.+)$");
    var emailMatcher = emailPattern.matcher(emailAddress);
    if (!emailMatcher.matches()) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "email",
          ACCOUNT_EMAIL_INVALID.formatted(emailAddress)));
    }
  }
}
