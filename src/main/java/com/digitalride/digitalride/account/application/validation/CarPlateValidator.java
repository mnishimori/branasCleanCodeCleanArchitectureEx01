package com.digitalride.digitalride.account.application.validation;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class CarPlateValidator {

  public void validate(String carPlate) {
    var carPlatePattern = Pattern.compile("^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$");
    var carPlateMatcher = carPlatePattern.matcher(carPlate);
    if (!carPlateMatcher.matches()) {
      throw new RuntimeException("Car plate is invalid.");
    }
  }
}
