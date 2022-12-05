package catHouse.common;

public class NameValidator {

    public static void validateName(String textForValidation, String exceptionMessage) {
        if (textForValidation == null || textForValidation.trim().isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }

    }

  }
