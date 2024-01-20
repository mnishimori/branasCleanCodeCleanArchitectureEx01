package com.digitalride.digitalride.account.application.validation;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

  public void validate(String emailAddress) {
    var emailPattern = Pattern.compile("^(.+)@(.+)$");
    var emailMatcher = emailPattern.matcher(emailAddress);
    if (!emailMatcher.matches()) {
      throw new RuntimeException("Email is invalid.");
    }
  }
}
