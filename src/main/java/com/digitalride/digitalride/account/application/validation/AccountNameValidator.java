package com.digitalride.digitalride.account.application.validation;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class AccountNameValidator {

  public void validate(String name) {
    var namePattern = Pattern.compile(".*[a-zA-Z].*");
    var nameMatcher = namePattern.matcher(name);
    if (!nameMatcher.matches()) {
      throw new RuntimeException("Name is invalid.");
    }
  }
}
