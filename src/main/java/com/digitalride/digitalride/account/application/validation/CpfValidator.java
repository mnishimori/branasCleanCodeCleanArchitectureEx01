package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_CPF_INVALID;

import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class CpfValidator {

  public void validate(String rawCpf) {
    if (rawCpf == null || rawCpf.isEmpty()) {
      throwCpfInvalidException(rawCpf);
    }
    String cpf = removeNonDigits(rawCpf);
    if (isInvalidLength(cpf)) {
      throwCpfInvalidException(cpf);
    }

    if (hasAllDigitsEqual(cpf)) {
      throwCpfInvalidException(cpf);
    }

    int digit1 = calculateDigit(cpf, 10);
    int digit2 = calculateDigit(cpf, 11);

    if (!extractDigit(cpf).equals(String.valueOf(digit1) + digit2)) {
      throwCpfInvalidException(cpf);
    };
  }

  private void throwCpfInvalidException(String cpf) {
    throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "CPF",
        ACCOUNT_CPF_INVALID.formatted(cpf)));
  }

  private static String removeNonDigits(String cpf) {
    return cpf.replaceAll("\\D", "");
  }

  private static boolean isInvalidLength(String cpf) {
    final int CPF_LENGTH = 11;
    return cpf.length() != CPF_LENGTH;
  }

  private static boolean hasAllDigitsEqual(String cpf) {
    char firstCpfDigit = cpf.charAt(0);
    return cpf.chars().allMatch(digit -> digit == firstCpfDigit);
  }

  private static int calculateDigit(String cpf, int factor) {
    int total = 0;
    for (char digit : cpf.toCharArray()) {
      if (factor > 1) {
        total += Character.getNumericValue(digit) * factor--;
      }
    }

    int rest = total % 11;
    return (rest < 2) ? 0 : 11 - rest;
  }

  private static String extractDigit(String cpf) {
    return cpf.substring(9);
  }
}
