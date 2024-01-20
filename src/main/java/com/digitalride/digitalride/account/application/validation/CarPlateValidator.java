package com.digitalride.digitalride.account.application.validation;

import static com.digitalride.digitalride.account.model.message.AccountMessages.ACCOUNT_CAR_PLATE_INVALID;

import com.digitalride.digitalride.shared.presentation.exception.ValidatorException;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class CarPlateValidator {

  public void validate(String carPlate) {
    var carPlatePattern = Pattern.compile("^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$");
    var carPlateMatcher = carPlatePattern.matcher(carPlate);
    if (!carPlateMatcher.matches()) {
      throw new ValidatorException(new FieldError(this.getClass().getSimpleName(), "car plate",
          ACCOUNT_CAR_PLATE_INVALID.formatted(carPlate)));
    }
  }
}
